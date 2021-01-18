package net.nanai10a.twomeat.cli.usecases.id.get

import net.nanai10a.twomeat.cli.usecases.SessionData
import java.util.*

data class IdGetOutputData(val sessionData: SessionData, val id: UUID?)
