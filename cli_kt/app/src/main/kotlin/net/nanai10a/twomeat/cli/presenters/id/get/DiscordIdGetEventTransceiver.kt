package net.nanai10a.twomeat.cli.presenters.id.get

import net.nanai10a.twomeat.cli.presenters.EventReceiver
import net.nanai10a.twomeat.cli.presenters.EventTransmissioner

class DiscordIdGetEventTransmissioner : EventTransmissioner<DiscordIdGetEvent> {
    override val receivers: MutableList<EventReceiver<DiscordIdGetEvent>> = mutableListOf()
}

class DiscordIdGetEventReceiver(private val view: DiscordIdGetView) : EventReceiver<DiscordIdGetEvent> {
    override fun onReceive(event: DiscordIdGetEvent) = this.view.invoke(event.sessionData, event.model)
}
