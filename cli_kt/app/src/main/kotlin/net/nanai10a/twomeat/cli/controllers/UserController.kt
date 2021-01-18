package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.entities.History
import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.usecases.SessionData
import net.nanai10a.twomeat.cli.usecases.id.get.IIdGetUsecase
import net.nanai10a.twomeat.cli.usecases.id.get.IdGetInputData
import net.nanai10a.twomeat.cli.usecases.user.get.IUserGetUsecase
import net.nanai10a.twomeat.cli.usecases.user.get.UserGetInputData
import net.nanai10a.twomeat.cli.usecases.user.save.IUserSaveUsecase
import net.nanai10a.twomeat.cli.usecases.user.save.UserSaveInputData
import java.util.*

class UserController(
    private val userGetUsecase: IUserGetUsecase,
    private val userSaveUsecase: IUserSaveUsecase,
    private val idGetUsecase: IIdGetUsecase
) {
    fun getUser(sessionData: SessionData, id: UUID) {
        this.userGetUsecase.handle(UserGetInputData(sessionData, id))
    }

    fun saveUser(sessionData: SessionData, user: User) {
        this.userSaveUsecase.handle(UserSaveInputData(sessionData, user))
    }

    fun saveUser(sessionData: SessionData, name: String, discordId: String) {
        this.userSaveUsecase.handle(
            UserSaveInputData(
                sessionData,
                User(UUID.randomUUID(), name, discordId, History(arrayOf()))
            )
        )
    }

    fun getId(sessionData: SessionData, discordId: String) {
        this.idGetUsecase.handle(
            IdGetInputData(
                sessionData,
                discordId
            )
        )
    }
}
