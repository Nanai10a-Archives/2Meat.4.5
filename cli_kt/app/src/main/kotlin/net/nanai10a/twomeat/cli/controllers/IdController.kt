package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.usecases.SessionData
import net.nanai10a.twomeat.cli.usecases.id.get.IIdGetUsecase
import net.nanai10a.twomeat.cli.usecases.id.get.IdGetInputData

class IdController(private val idGetUsecase: IIdGetUsecase) {
    fun getId(sessionData: SessionData, discordId: String) {
        this.idGetUsecase.handle(
            IdGetInputData(
                sessionData,
                discordId
            )
        )
    }
}
