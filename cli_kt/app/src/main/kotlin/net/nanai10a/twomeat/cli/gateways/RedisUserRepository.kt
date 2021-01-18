package net.nanai10a.twomeat.cli.gateways

import com.google.gson.Gson
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.codec.RedisCodec
import net.nanai10a.twomeat.cli.db.entities.User
import net.nanai10a.twomeat.cli.db.gateways.IUserRepository
import java.nio.ByteBuffer
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.scheduleAtFixedRate

class RedisUserRepository(private val redisClient: RedisClient) : IUserRepository {
    private val timer: Timer = Timer()
    private var currentTask: TimerTask? = null

    private val codec: RedisCodec<String, User> = RedisUserCodec()
    private var connection: StatefulRedisConnection<String, User>? = null

    private fun setTimer() {
        cancelTimer()
        this.currentTask = timer.scheduleAtFixedRate(TimeUnit.MINUTES.toMillis(3), 0) {
            connection?.close()
        }
    }

    private fun cancelTimer() = this.currentTask?.cancel()

    private fun connectRedis(): StatefulRedisConnection<String, User> = redisClient.connect(this.codec)

    private fun connectionEnsure() {
        cancelTimer()
        if (this.connection == null) {
            this.connection = connectRedis()
        }
    }

    override fun save(user: User) {
        connectionEnsure()

        this.connection!!.

        this.setTimer()
    }

    override fun load(discordId: String): User {

        this.setTimer()
        return TODO()
    }

    override fun delete(id: UUID) {


        this.setTimer()
    }
}


private class RedisUserCodec : JedisCodec<String, User> {
    private val gson = Gson()
    override fun decodeKey(bytes: ByteArray): String {
        return bytes.toString()
    }

    override fun decodeValue(bytes: ByteArray): User {
        return this.gson.fromJson(bytes.toString(), User::class.java)
    }

    override fun encodeKey(key: String): ByteArray {
        return key.encodeToByteArray()
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
