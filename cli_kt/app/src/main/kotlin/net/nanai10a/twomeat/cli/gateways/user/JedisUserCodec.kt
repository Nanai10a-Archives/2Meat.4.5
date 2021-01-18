package net.nanai10a.twomeat.cli.gateways.user

import com.google.gson.Gson
import net.nanai10a.twomeat.cli.entities.User
import net.nanai10a.twomeat.cli.gateways.JedisCodec
import java.util.*

class JedisUserCodec : JedisCodec<UUID, User> {
    private val gson = Gson()
    override fun decodeKey(bytes: ByteArray): UUID {
        return UUID.fromString(bytes.decodeToString())
    }

    override fun decodeValue(bytes: ByteArray): User {
        return this.gson.fromJson(bytes.decodeToString(), User::class.java)
    }

    override fun encodeKey(key: UUID): ByteArray {
        return key.toString().encodeToByteArray()
    }

    override fun encodeValue(value: User): ByteArray {
        return this.gson.toJson(value).encodeToByteArray()
    }
}
