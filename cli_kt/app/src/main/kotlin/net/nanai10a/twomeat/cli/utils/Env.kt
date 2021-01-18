package net.nanai10a.twomeat.cli.utils

data class Env (
    val redisIp: String = getEnv("TWOMEAT_CLI_REDIS_IP"),
    val redisPort: Int = getEnv("TWOMEAT_CLI_REDIS_PORT").toInt(),
    val discordToken: String = getEnv("TWOMEAT_CLI_DISCORD_TOKEN")
)

private fun getEnv(name: String): String {
    val env = System.getenv(name)
    if (env.isNullOrBlank())
        throw Exception("require environment variable \"$name\" is not found!")

    return env
}
