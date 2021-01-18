package net.nanai10a.twomeat.cli.inits

import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.db.gateways.IUserRepository
import net.nanai10a.twomeat.cli.gateways.RedisUserRepository
import net.nanai10a.twomeat.cli.presenters.DiscordUserGetPresenter
import net.nanai10a.twomeat.cli.presenters.DiscordUserSavePresenter
import net.nanai10a.twomeat.cli.presenters.IUserGetPresenter
import net.nanai10a.twomeat.cli.presenters.IUserSavePresenter
import net.nanai10a.twomeat.cli.usecases.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.UserGetInteractor
import net.nanai10a.twomeat.cli.usecases.UserSaveInteractor
import net.nanai10a.twomeat.cli.utils.DIService

fun DIService.productionDI(): DIService {
    register(IUserGetPresenter::class.java) { DiscordUserGetPresenter() }
    register(IUserSavePresenter::class.java) { DiscordUserSavePresenter() }
    register(IUserRepository::class.java) { RedisUserRepository() }

    return this
}

fun DIService.interactorDI(): DIService {
    register(IUserGetUsecase::class.java) {
        UserGetInteractor(
            create(IUserGetPresenter::class.java) as IUserGetPresenter,
            create(IUserRepository::class.java) as IUserRepository
        )
    }

    register(IUserSaveUsecase::class.java) {
        UserSaveInteractor(
            create(IUserSavePresenter::class.java) as IUserSavePresenter,
            create(IUserRepository::class.java) as IUserRepository
        )
    }

    return this
}

fun DIService.controllerDI(): DIService {
    register(UserController::class.java) {
        UserController(
            create(IUserGetUsecase::class.java) as IUserGetUsecase,
            create(IUserSaveUsecase::class.java) as IUserSaveUsecase
        )
    }

    return this
}
