package net.nanai10a.twomeat.cli.inits

import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.gateways.IUserRepository
import net.nanai10a.twomeat.cli.gateways.RedisUserRepository
import net.nanai10a.twomeat.cli.presenters.DiscordUserGetPresenter
import net.nanai10a.twomeat.cli.presenters.DiscordUserSavePresenter
import net.nanai10a.twomeat.cli.presenters.IUserGetPresenter
import net.nanai10a.twomeat.cli.presenters.IUserSavePresenter
import net.nanai10a.twomeat.cli.usecases.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.UserGetInteractor
import net.nanai10a.twomeat.cli.usecases.UserSaveInteractor
import net.nanai10a.twomeat.cli.utils.ServiceProvider
import redis.clients.jedis.Jedis

fun ServiceProvider.productionDI(): ServiceProvider {
    register(IUserGetPresenter::class.java) { DiscordUserGetPresenter() }
    register(IUserSavePresenter::class.java) { DiscordUserSavePresenter() }
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
