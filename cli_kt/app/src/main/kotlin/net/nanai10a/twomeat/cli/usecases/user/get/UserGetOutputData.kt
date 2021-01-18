package net.nanai10a.twomeat.cli.usecases.user.get

import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.usecases.SessionData

data class UserGetOutputData(val sessionData: SessionData, val user: User?)
