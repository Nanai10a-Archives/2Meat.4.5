package net.nanai10a.twomeat.cli.gateways.id

import net.nanai10a.twomeat.cli.gateways.JedisCodec
import redis.clients.jedis.Jedis
import java.util.*

class RedisIdRepository(private val jedis: Jedis) : IIdRepository {
    private val codec: JedisCodec<String, UUID> = JedisIdCodec()
    override fun load(discordId: String): UUID? =
        jedis.get(codec.encodeKey(discordId))?.let { codec.decodeValue(it) }
}
