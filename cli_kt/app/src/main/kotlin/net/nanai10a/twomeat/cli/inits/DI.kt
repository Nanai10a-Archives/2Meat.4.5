package net.nanai10a.twomeat.cli.inits

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.gateways.IUserRepository
import net.nanai10a.twomeat.cli.gateways.RedisUserRepository
import net.nanai10a.twomeat.cli.presenters.*
import net.nanai10a.twomeat.cli.usecases.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.UserGetInteractor
import net.nanai10a.twomeat.cli.usecases.UserSaveInteractor
import net.nanai10a.twomeat.cli.utils.ServiceProvider
import redis.clients.jedis.Jedis

fun ServiceProvider.transceiverDI(
    userGetTransmissioner: DiscordUserGetEventTransmissioner,
    userSaveTransmissioner: DiscordUserSaveEventTransmissioner
): ServiceProvider {
    register(DiscordUserGetEventTransmissioner::class.java) {
        userGetTransmissioner
    }

    register(DiscordUserSaveEventTransmissioner::class.java) {
        userSaveTransmissioner
    }

    return this
}

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

    return this
}

fun ServiceProvider.repositoryDI(): ServiceProvider {
    register(IUserRepository::class.java) { RedisUserRepository(Jedis(this.env.redisIp, this.env.redisPort)) }

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

    return this
}

fun ServiceProvider.controllerDI(): ServiceProvider {
    register(UserController::class.java) {
        UserController(
            create(IUserGetUsecase::class.java),
            create(IUserSaveUsecase::class.java)
        )
    }

    return this
}
