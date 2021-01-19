package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.usecases.SessionData

data class DiscordViewDestinationStore(val store: MutableMap<SessionData, DiscordDestination> = mutableMapOf())

data class DiscordDestination(val channelId: String, val types: DiscordViewDestinationTypes)
