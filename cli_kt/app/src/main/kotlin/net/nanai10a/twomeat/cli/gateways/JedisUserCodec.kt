package net.nanai10a.twomeat.cli.gateways

import com.google.gson.Gson
import net.nanai10a.twomeat.cli.entities.User
import java.util.*

class JedisUserCodec : JedisCodec<UUID, User> {
    private val gson = Gson()
    override fun decodeKey(bytes: ByteArray): UUID {
        return UUID.fromString(bytes.toString())
    }

    override fun decodeValue(bytes: ByteArray): User {
        return this.gson.fromJson(bytes.toString(), User::class.java)
    }

    override fun encodeKey(key: UUID): ByteArray {
        return key.toString().encodeToByteArray()
    }

    override fun encodeValue(value: User): ByteArray {
        return this.gson.toJson(value).encodeToByteArray()
    }
}
