package net.nanai10a.twomeat.cli.gateways.id

import java.util.*

interface IIdRepository {
    fun load(discordId: String): UUID?
}
