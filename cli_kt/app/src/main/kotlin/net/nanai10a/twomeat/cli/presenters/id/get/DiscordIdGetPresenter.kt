package net.nanai10a.twomeat.cli.presenters.id.get

import net.nanai10a.twomeat.cli.usecases.id.get.IdGetOutputData

class DiscordIdGetPresenter(private val transmissioner: DiscordIdGetEventTransmissioner) : IIdGetPresenter {
    override fun complete(output: IdGetOutputData) {
        transmissioner.transmission(
            DiscordIdGetEvent(
                output.sessionData,
                DiscordIdGetViewModel(output.id.toString())
            )
        )
    }
}
