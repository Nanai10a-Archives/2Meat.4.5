package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.UserGetOutputData

class DiscordUserGetPresenter(private val transmissioner: DiscordUserGetEventTransmissioner) : IUserGetPresenter {
    override fun complete(output: UserGetOutputData) {
        TODO("Not yet implemented")
    }
}
