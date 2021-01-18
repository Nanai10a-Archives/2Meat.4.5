package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.SessionData

data class DiscordUserSaveEvent(val sessionData: SessionData, val model: DiscordUserSaveViewModel)
