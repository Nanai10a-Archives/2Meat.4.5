package net.nanai10a.twomeat.cli.gateways

import com.google.gson.Gson
import net.nanai10a.twomeat.cli.db.entities.User
import net.nanai10a.twomeat.cli.db.gateways.IUserRepository
import redis.clients.jedis.Jedis
import java.util.*

class RedisUserRepository(private val jedis: Jedis) : IUserRepository {
    private val codec: JedisCodec<UUID, User> = JedisUserCodec()
    override fun save(user: User) {
        jedis.set(codec.encodeKey(user.id), codec.encodeValue(user))
    }

    override fun load(id: UUID): User? = jedis.get(codec.encodeKey(id))?.let { codec.decodeValue(it) }

    override fun delete(id: UUID) {
        jedis.del(codec.encodeKey(id))
    }
}


private class JedisUserCodec : JedisCodec<UUID, User> {
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

interface JedisCodec<K, V> {
    fun decodeKey(bytes: ByteArray): K
    fun decodeValue(bytes: ByteArray): V
    fun encodeKey(key: K): ByteArray
    fun encodeValue(value: V): ByteArray
}
