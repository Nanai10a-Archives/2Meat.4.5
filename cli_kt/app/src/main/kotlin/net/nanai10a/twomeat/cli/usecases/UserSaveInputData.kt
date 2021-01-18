package net.nanai10a.twomeat.cli.usecases

import net.nanai10a.twomeat.cli.entities.User

data class UserSaveInputData(val sessionData: SessionData, val user: User)
