package net.nanai10a.twomeat.cli.presenters.none

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.presenters.DiscordMessage
import net.nanai10a.twomeat.cli.presenters.IDiscordView
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordNoneView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) : IDiscordView<DiscordNoneViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordNoneViewModel) =
        this.send(destinationStore.store[sessionData]!!, DiscordMessage(charSequence = model.rawString))
}
