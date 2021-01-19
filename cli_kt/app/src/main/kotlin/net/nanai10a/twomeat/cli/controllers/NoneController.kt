package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.presenters.DiscordMessage
import net.nanai10a.twomeat.cli.presenters.none.DiscordNonePresenter
import net.nanai10a.twomeat.cli.presenters.none.NoneOutputData
import net.nanai10a.twomeat.cli.usecases.SessionData

class NoneController(
    private val presenter: DiscordNonePresenter
) {
    fun send(sessionData: SessionData, args: List<String>) {
        val message = DiscordMessage(TODO())

        presenter.complete(NoneOutputData(sessionData, message))
    }
}
