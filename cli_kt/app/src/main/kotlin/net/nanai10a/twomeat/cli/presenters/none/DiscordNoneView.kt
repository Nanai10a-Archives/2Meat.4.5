package net.nanai10a.twomeat.cli.presenters.none

import net.dv8tion.jda.api.JDA
import net.nanai10a.twomeat.cli.presenters.IDiscordView
import net.nanai10a.twomeat.cli.usecases.SessionData

class DiscordNoneView(override val jda: JDA) : IDiscordView<DiscordNoneViewModel> {
    override fun invoke(sessionData: SessionData, model: DiscordNoneViewModel) {
        TODO("Not yet implemented")
    }
}
