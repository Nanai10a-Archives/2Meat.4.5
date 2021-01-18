package net.nanai10a.twomeat.cli.presenters.user.save

import net.nanai10a.twomeat.cli.usecases.SessionData

data class DiscordUserSaveEvent(val sessionData: SessionData, val model: DiscordUserSaveViewModel)
