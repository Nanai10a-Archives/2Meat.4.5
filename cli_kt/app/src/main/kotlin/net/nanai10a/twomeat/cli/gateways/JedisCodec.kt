package net.nanai10a.twomeat.cli.gateways

interface JedisCodec<K, V> {
    fun decodeKey(bytes: ByteArray): K
    fun decodeValue(bytes: ByteArray): V
    fun encodeKey(key: K): ByteArray
    fun encodeValue(value: V): ByteArray
}
