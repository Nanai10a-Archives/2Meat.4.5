package net.nanai10a.twomeat.cli.controllers

data class DiscordViewDestinationStore(val store: Map<String, DiscordDestination> = mapOf())

data class DiscordDestination(val channelId: String, val types: DiscordViewDestinationTypes)
