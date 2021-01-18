package net.nanai10a.twomeat.cli.gateways

import net.nanai10a.twomeat.cli.db.entities.User
import net.nanai10a.twomeat.cli.db.gateways.IUserRepository
import java.util.*

class RedisUserRepository : IUserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}
