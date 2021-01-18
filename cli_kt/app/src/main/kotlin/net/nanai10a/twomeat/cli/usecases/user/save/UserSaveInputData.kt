package net.nanai10a.twomeat.cli.usecases.user.save

import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.usecases.SessionData

data class UserSaveInputData(val sessionData: SessionData, val user: User)
