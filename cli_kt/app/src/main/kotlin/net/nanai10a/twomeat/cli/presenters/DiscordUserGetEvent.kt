package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.SessionData

data class DiscordUserGetEvent(val sessionData: SessionData, val model: DiscordUserGetViewModel)
