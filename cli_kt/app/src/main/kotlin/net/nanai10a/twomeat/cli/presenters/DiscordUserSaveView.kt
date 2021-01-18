package net.nanai10a.twomeat.cli.presenters

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordUserSaveView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) :
    IDiscordView<DiscordUserSaveViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordUserSaveViewModel) {
        TODO()
        val string = ""
        this.send(destinationStore.store[sessionData]!!, DiscordMessage(string, null, null))
    }
}
