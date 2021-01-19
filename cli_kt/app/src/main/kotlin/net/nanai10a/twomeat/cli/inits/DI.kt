package net.nanai10a.twomeat.cli.inits

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.controllers.IdController
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.gateways.id.IIdRepository
import net.nanai10a.twomeat.cli.gateways.id.RedisIdRepository
import net.nanai10a.twomeat.cli.gateways.user.IUserRepository
import net.nanai10a.twomeat.cli.gateways.user.RedisUserRepository
import net.nanai10a.twomeat.cli.presenters.id.get.*
import net.nanai10a.twomeat.cli.presenters.user.get.*
import net.nanai10a.twomeat.cli.presenters.user.save.*
import net.nanai10a.twomeat.cli.usecases.id.get.IIdGetUsecase
import net.nanai10a.twomeat.cli.usecases.id.get.IdGetInteractor
import net.nanai10a.twomeat.cli.usecases.user.get.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.user.get.UserGetInteractor
import net.nanai10a.twomeat.cli.usecases.user.save.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.user.save.UserSaveInteractor
import net.nanai10a.twomeat.cli.utils.ServiceProvider
import redis.clients.jedis.Jedis

fun ServiceProvider.discordViewDI(
    jda: JDA,
    destinationStore: DiscordViewDestinationStore
): ServiceProvider {
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

fun ServiceProvider.transmissionerDI(
    userGetTransmissioner: DiscordUserGetEventTransmissioner = DiscordUserGetEventTransmissioner(),
    userSaveTransmissioner: DiscordUserSaveEventTransmissioner = DiscordUserSaveEventTransmissioner(),
    idGetTransmissioner: DiscordIdGetEventTransmissioner = DiscordIdGetEventTransmissioner()
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

fun ServiceProvider.subscribeReceiver(): ServiceProvider {
    create(DiscordUserGetEventTransmissioner::class.java).addReceivers(
        DiscordUserGetEventReceiver(
            create(DiscordUserGetView::class.java)
        )
    )

    create(DiscordUserSaveEventTransmissioner::class.java).addReceivers(
        DiscordUserSaveEventReceiver(
            create(DiscordUserSaveView::class.java)
        )
    )

    create(DiscordIdGetEventTransmissioner::class.java).addReceivers(
        DiscordIdGetEventReceiver(
            create(DiscordIdGetView::class.java)
        )
    )

    return this
}

fun ServiceProvider.repositoryDI(
    jedis: Jedis = Jedis(this.env.redisIp, this.env.redisPort)
): ServiceProvider {
    register(IUserRepository::class.java) { RedisUserRepository(jedis) }
    register(IIdRepository::class.java) { RedisIdRepository(jedis) }

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
            create(IUserSaveUsecase::class.java)
        )
    }

    register(IdController::class.java) {
        IdController(
            create(IIdGetUsecase::class.java)
        )
    }

    return this
}

fun ServiceProvider.productionDI(jda: JDA, destinationStore: DiscordViewDestinationStore): ServiceProvider {
    // 依存なし
    discordViewDI(jda, destinationStore)
    // 依存なし
    transmissionerDI()
    // Transmissionerが無いと動作しない.
    subscribeReceiver()
    // 依存なし
    repositoryDI()
    // Transceiverに依存
    presenterDI()
    // Repository, Presenterに依存
    interactorDI()
    // Usecase(Interactor)に依存
    controllerDI()


    create(DiscordUserGetView::class.java)
    create(DiscordUserSaveView::class.java)
    create(DiscordIdGetView::class.java)

    return this
}
