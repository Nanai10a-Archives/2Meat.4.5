package net.nanai10a.twomeat.cli.usecases.id.get

import net.nanai10a.twomeat.cli.usecases.SessionData

data class IdGetInputData(val sessionData: SessionData, val discordId: String)
