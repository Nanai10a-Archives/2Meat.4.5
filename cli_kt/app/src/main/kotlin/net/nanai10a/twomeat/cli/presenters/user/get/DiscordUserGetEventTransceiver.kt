package net.nanai10a.twomeat.cli.presenters.user.get

import net.nanai10a.twomeat.cli.presenters.EventReceiver
import net.nanai10a.twomeat.cli.presenters.EventTransmissioner
import net.nanai10a.twomeat.cli.presenters.IDiscordView

class DiscordUserGetEventTransmissioner : EventTransmissioner<DiscordUserGetEvent> {
    override val receivers: List<EventReceiver<DiscordUserGetEvent>> = listOf()
}

class DiscordUserGetEventReceiver(private val view: IDiscordView<DiscordUserGetViewModel>) :
    EventReceiver<DiscordUserGetEvent> {
    override fun onReceive(event: DiscordUserGetEvent) = this.view.invoke(event.sessionData, event.model)
}
