package net.nanai10a.twomeat.cli.gateways

import net.nanai10a.twomeat.cli.entities.User
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
