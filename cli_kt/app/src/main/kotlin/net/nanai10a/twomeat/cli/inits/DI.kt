package net.nanai10a.twomeat.cli.inits

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.gateways.IIdRepository
import net.nanai10a.twomeat.cli.gateways.IUserRepository
import net.nanai10a.twomeat.cli.gateways.RedisIdRepository
import net.nanai10a.twomeat.cli.gateways.RedisUserRepository
import net.nanai10a.twomeat.cli.presenters.*
import net.nanai10a.twomeat.cli.usecases.*
import net.nanai10a.twomeat.cli.utils.ServiceProvider
import redis.clients.jedis.Jedis

fun ServiceProvider.discordViewDI(jda: JDA, destinationStore: DiscordViewDestinationStore): ServiceProvider {
    register(
        DiscordUserGetView::class.java
    ) {
        DiscordUserGetView(
            jda,
            destinationStore
        )
    }

    register(
        DiscordUserSaveView::class.java
    ) {
        DiscordUserSaveView(
            jda,
            destinationStore
        )
    }

    return this
}

fun ServiceProvider.repositoryDI(): ServiceProvider {
    val jedis = Jedis(this.env.redisIp, this.env.redisPort)

    register(IUserRepository::class.java) { RedisUserRepository(jedis) }
    register(IIdRepository::class.java) { RedisIdRepository(jedis) }

    return this
}

fun ServiceProvider.transceiverDI(
    userGetTransmissioner: DiscordUserGetEventTransmissioner,
    userSaveTransmissioner: DiscordUserSaveEventTransmissioner,
    idGetTransmissioner: DiscordIdGetEventTransmissioner
): ServiceProvider {
    register(DiscordUserGetEventTransmissioner::class.java) {
        userGetTransmissioner
    }

    register(DiscordUserSaveEventTransmissioner::class.java) {
        userSaveTransmissioner
    }

    register(DiscordIdGetEventTransmissioner::class.java) {
        idGetTransmissioner
    }

    return this
}

fun ServiceProvider.presenterDI(): ServiceProvider {
    register(IUserGetPresenter::class.java) {
        DiscordUserGetPresenter(
            create(DiscordUserGetEventTransmissioner::class.java)
        )
    }

    register(IUserSavePresenter::class.java) {
        DiscordUserSavePresenter(
            create(DiscordUserSaveEventTransmissioner::class.java)
        )
    }

    register(IIdGetPresenter::class.java) {
        DiscordIdGetPresenter(
            create(DiscordIdGetEventTransmissioner::class.java)
        )
    }

    return this
}

fun ServiceProvider.interactorDI(): ServiceProvider {
    register(IUserGetUsecase::class.java) {
        UserGetInteractor(
            create(IUserGetPresenter::class.java),
            create(IUserRepository::class.java)
        )
    }

    register(IUserSaveUsecase::class.java) {
        UserSaveInteractor(
            create(IUserSavePresenter::class.java),
            create(IUserRepository::class.java)
        )
    }

    register(IIdGetUsecase::class.java) {
        IdGetInteractor(
            create(IIdGetPresenter::class.java),
            create(IIdRepository::class.java)
        )
    }

    return this
}

fun ServiceProvider.controllerDI(): ServiceProvider {
    register(UserController::class.java) {
        UserController(
            create(IUserGetUsecase::class.java),
            create(IUserSaveUsecase::class.java),
            create(IIdGetUsecase::class.java)
        )
    }

    return this
}

fun ServiceProvider.productionDI(
    jda: JDA,
    userSaveTransmissioner: DiscordUserSaveEventTransmissioner,
    userGetTransmissioner: DiscordUserGetEventTransmissioner,
    idGetTransmissioner: DiscordIdGetEventTransmissioner
): ServiceProvider {
    val viewDestinationStore = DiscordViewDestinationStore()

    // 依存なし
    discordViewDI(jda, viewDestinationStore)
    // 依存なし
    repositoryDI()
    // 依存なし
    transceiverDI(userGetTransmissioner, userSaveTransmissioner, idGetTransmissioner)
    // Transceiverに依存
    presenterDI()
    // Repository, Presenterに依存
    interactorDI()
    // Usecase(Interactor)に依存
    controllerDI()

    return this
}
