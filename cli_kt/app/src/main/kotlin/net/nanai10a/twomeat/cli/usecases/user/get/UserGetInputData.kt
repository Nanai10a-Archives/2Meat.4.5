package net.nanai10a.twomeat.cli.usecases.user.get

import net.nanai10a.twomeat.cli.usecases.SessionData
import java.util.*

data class UserGetInputData(val sessionData: SessionData, val id: UUID)
