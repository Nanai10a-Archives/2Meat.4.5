package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.IdGetOutputData

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
