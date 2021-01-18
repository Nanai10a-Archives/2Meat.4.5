package net.nanai10a.twomeat.cli.gateways

import java.util.*

interface IIdRepository {
    fun load(discordId: String): UUID?
}
