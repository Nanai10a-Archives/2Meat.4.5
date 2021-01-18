package net.nanai10a.twomeat.cli.presenters.user.save

import net.nanai10a.twomeat.cli.presenters.EventReceiver
import net.nanai10a.twomeat.cli.presenters.EventTransmissioner
import net.nanai10a.twomeat.cli.presenters.IDiscordView

class DiscordUserSaveEventTransmissioner : EventTransmissioner<DiscordUserSaveEvent> {
    override val receivers: List<EventReceiver<DiscordUserSaveEvent>> = listOf()
}

class DiscordUserSaveEventReceiver(private val view: IDiscordView<DiscordUserSaveViewModel>) :
    EventReceiver<DiscordUserSaveEvent> {
    override fun onReceive(event: DiscordUserSaveEvent) = view.invoke(event.sessionData, event.model)
}
