package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.entities.History
import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.usecases.*
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
