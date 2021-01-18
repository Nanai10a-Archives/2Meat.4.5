package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.entities.History
import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.usecases.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.UserGetInputData
import net.nanai10a.twomeat.cli.usecases.UserSaveInputData
import java.util.*

class UserController(
    private val userGetUsecase: IUserGetUsecase,
    private val userSaveUsecase: IUserSaveUsecase
) {
    fun getUser(discordId: String) {
        this.userGetUsecase.handle(UserGetInputData(discordId))
    }

    fun saveUser(user: User) {
        this.userSaveUsecase.handle(UserSaveInputData(user))
    }

    fun saveUser(name: String, discordId: String) {
        this.userSaveUsecase.handle(UserSaveInputData(User(UUID.randomUUID(), name, discordId, History(arrayOf()))))
    }
}
