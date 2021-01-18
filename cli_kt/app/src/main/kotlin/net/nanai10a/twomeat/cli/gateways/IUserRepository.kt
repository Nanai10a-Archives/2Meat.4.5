package net.nanai10a.twomeat.cli.gateways

import net.nanai10a.twomeat.cli.db.entities.User
import java.util.*

interface IUserRepository {
    fun save(user: User)
    fun load(id: UUID): User?
    fun delete(id: UUID)
}
