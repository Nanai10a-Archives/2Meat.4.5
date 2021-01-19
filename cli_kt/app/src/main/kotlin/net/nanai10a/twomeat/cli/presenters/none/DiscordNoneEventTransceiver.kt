package net.nanai10a.twomeat.cli.presenters.none

import net.nanai10a.twomeat.cli.presenters.EventReceiver
import net.nanai10a.twomeat.cli.presenters.EventTransmissioner

class DiscordNoneEventTransmissioner : EventTransmissioner<DiscordNoneEvent>

class DiscordNoneEventReceiver(private val view: DiscordNoneView) : EventReceiver<DiscordNoneEvent> {
    override fun onReceive(event: DiscordNoneEvent) = view.invoke(event.sessionData, event.model)
}
