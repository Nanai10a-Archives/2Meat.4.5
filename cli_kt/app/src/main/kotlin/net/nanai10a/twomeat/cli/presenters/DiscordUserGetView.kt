package net.nanai10a.twomeat.cli.presenters

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.MessageEmbed
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordUserGetView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) :
    IDiscordView<DiscordUserGetViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordUserGetViewModel) {
        val embed = EmbedBuilder()
            .addField("User:get - Result", model.json, false)
            .build()

        this.send(destinationStore.store[sessionData]!!, DiscordMessage(null, null, embed))
    }
}
