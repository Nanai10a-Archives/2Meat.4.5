package net.nanai10a.twomeat.cli.presenters

class DiscordUserGetEventTransmissioner : EventTransmissioner<DiscordUserGetEvent> {
    override val receivers: List<EventReceiver<DiscordUserGetEvent>> = listOf()
}

class DiscordUserGetEventReceiver : EventReceiver<DiscordUserGetEvent> {
    override fun onReceive(event: DiscordUserGetEvent) {
        TODO("Not yet implemented")
    }
}
