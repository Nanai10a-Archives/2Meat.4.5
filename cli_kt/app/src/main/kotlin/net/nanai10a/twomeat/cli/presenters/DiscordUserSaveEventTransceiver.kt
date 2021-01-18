package net.nanai10a.twomeat.cli.presenters

class DiscordUserSaveEventTransmissioner : EventTransmissioner<DiscordUserSaveEvent> {
    override val receivers: List<EventReceiver<DiscordUserSaveEvent>> = listOf()
}

class DiscordUserSaveEventReceiver : EventReceiver<DiscordUserSaveEvent> {
    override fun onReceive(event: DiscordUserSaveEvent) {
        TODO("Not yet implemented")
    }
}
