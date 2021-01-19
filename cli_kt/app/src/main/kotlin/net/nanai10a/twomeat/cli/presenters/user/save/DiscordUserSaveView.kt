package net.nanai10a.twomeat.cli.presenters.user.save

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.presenters.DiscordMessage
import net.nanai10a.twomeat.cli.presenters.IDiscordView
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordUserSaveView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) :
    IDiscordView<DiscordUserSaveViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordUserSaveViewModel) {
        val embed = EmbedBuilder()
            .addField("User:save - Result", "Success!", false).build()

        this.send(destinationStore.store[sessionData]!!, DiscordMessage(embed = embed))
    }
}
