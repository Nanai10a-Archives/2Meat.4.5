package net.nanai10a.twomeat.cli.utils

data class Env (
    val redisIp: String = getEnv(""),
    val redisPort: Int = getEnv("").toInt(),
    val discordToken: String = getEnv("")
)

private fun getEnv(name: String): String {
    val env = System.getenv(name)
    if (env.isNullOrBlank())
        throw Exception("require environment variable \"$name\" is not found!")

    return env
}
