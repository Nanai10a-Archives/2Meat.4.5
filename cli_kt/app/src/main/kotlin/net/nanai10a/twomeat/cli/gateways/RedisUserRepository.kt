package net.nanai10a.twomeat.cli.gateways

import net.nanai10a.twomeat.cli.db.entities.User
import net.nanai10a.twomeat.cli.db.gateways.IUserRepository
import java.util.*

class RedisUserRepository : IUserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun load(discordId: String): User {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}


private class RedisUserCodec : RedisCodec<String, User> {
    private val gson = Gson()
    override fun decodeKey(bytes: ByteBuffer?): String {
        return bytes.toString()
    }

    override fun decodeValue(bytes: ByteBuffer?): User {
        return this.gson.fromJson(bytes.toString(), User::class.java)
    }

    override fun encodeKey(key: String?): ByteBuffer {
        return ByteBuffer.wrap(key?.encodeToByteArray())
    }

    override fun encodeValue(value: User?): ByteBuffer {
        return ByteBuffer.wrap(this.gson.toJson(value).encodeToByteArray())
    }

}
