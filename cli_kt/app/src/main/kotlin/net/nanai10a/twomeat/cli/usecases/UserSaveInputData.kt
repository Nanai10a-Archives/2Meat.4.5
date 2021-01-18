package net.nanai10a.twomeat.cli.usecases

import net.nanai10a.twomeat.cli.entities.User
import java.util.*

class UserSaveInputData(sessionId: UUID, val user: User) : SessionData(sessionId)
