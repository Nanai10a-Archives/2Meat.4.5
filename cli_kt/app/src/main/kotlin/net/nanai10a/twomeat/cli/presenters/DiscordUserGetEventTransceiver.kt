package net.nanai10a.twomeat.cli.presenters

class DiscordUserGetEventTransmissioner : EventTransmissioner<DiscordUserGetEvent> {
    override val receivers: List<EventReceiver<DiscordUserGetEvent>> = listOf()
}

class DiscordUserGetEventReceiver(private val view: IDiscordView<DiscordUserGetViewModel>) :
    EventReceiver<DiscordUserGetEvent> {
    override fun onReceive(event: DiscordUserGetEvent) = this.view.invoke(event.sessionData, event.model)
}
