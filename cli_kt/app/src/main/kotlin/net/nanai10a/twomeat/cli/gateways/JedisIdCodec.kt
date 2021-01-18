package net.nanai10a.twomeat.cli.gateways

import java.util.*

class JedisIdCodec : JedisCodec<String, UUID> {
    override fun decodeKey(bytes: ByteArray): String {
        return bytes.toString()
    }

    override fun decodeValue(bytes: ByteArray): UUID {
        return UUID.fromString(bytes.decodeToString())
    }

    override fun encodeKey(key: String): ByteArray {
        return key.encodeToByteArray()
    }

    override fun encodeValue(value: UUID): ByteArray {
        return value.toString().encodeToByteArray()
    }
}
