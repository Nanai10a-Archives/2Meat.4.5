package net.nanai10a.twomeat.cli.presenters

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordUserGetView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) :
    IDiscordView<DiscordUserGetViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordUserGetViewModel) {
        val string = ""
        this.send(destinationStore.store[sessionData]!!, DiscordMessage(string, null, null))
    }
}
