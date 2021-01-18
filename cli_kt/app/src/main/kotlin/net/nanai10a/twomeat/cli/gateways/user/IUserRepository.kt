package net.nanai10a.twomeat.cli.gateways.user

import net.nanai10a.twomeat.cli.entities.User
import java.util.*

interface IUserRepository {
    fun save(user: User)
    fun load(id: UUID): User?
    fun delete(id: UUID)
}
