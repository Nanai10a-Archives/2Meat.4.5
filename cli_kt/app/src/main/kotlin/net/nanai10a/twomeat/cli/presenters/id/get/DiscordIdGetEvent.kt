package net.nanai10a.twomeat.cli.presenters.id.get

import net.nanai10a.twomeat.cli.usecases.SessionData

data class DiscordIdGetEvent(val sessionData: SessionData, val model: DiscordIdGetViewModel)
