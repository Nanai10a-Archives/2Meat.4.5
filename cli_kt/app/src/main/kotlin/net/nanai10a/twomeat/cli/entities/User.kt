package net.nanai10a.twomeat.cli.entities

import net.nanai10a.twomeat.cli.entities.History
import java.util.*

data class User(val id: UUID, val name: String, val discordId: String, val cli_history: History)
