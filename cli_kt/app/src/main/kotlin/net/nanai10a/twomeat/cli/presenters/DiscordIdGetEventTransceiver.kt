package net.nanai10a.twomeat.cli.presenters

class DiscordIdGetEventTransmissioner : EventTransmissioner<DiscordIdGetEvent> {
    override val receivers: List<EventReceiver<DiscordIdGetEvent>> = listOf()
}

class DiscordIdGetEventReceiver(private val view: DiscordIdGetView) : EventReceiver<DiscordIdGetEvent> {
    override fun onReceive(event: DiscordIdGetEvent) = this.view.invoke(event.sessionData, event.model)
}
