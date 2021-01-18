package net.nanai10a.twomeat.cli.entities

import java.util.*

data class User(val id: UUID, val name: String, val discordId: String, val cli_history: History)
