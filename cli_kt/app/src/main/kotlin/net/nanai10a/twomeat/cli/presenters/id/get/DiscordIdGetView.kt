package net.nanai10a.twomeat.cli.presenters.id.get

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationStore
import net.nanai10a.twomeat.cli.presenters.DiscordMessage
import net.nanai10a.twomeat.cli.presenters.IDiscordView
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordIdGetView(override val jda: JDA, private val destinationStore: DiscordViewDestinationStore) :
    IDiscordView<DiscordIdGetViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordIdGetViewModel) {
        val embed = EmbedBuilder()
            .addField("Id:get - Result", model.id, false)
            .build()

        this.send(destinationStore.store[sessionData]!!, DiscordMessage(embed = embed))
    }
}
