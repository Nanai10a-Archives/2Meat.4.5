package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.UserSaveOutputData

class DiscordUserSavePresenter(private val transmissioner: DiscordUserSaveEventTransmissioner) : IUserSavePresenter {
    override fun complete(output: UserSaveOutputData) =
        transmissioner.transmission(DiscordUserSaveEvent(output.sessionData, DiscordUserSaveViewModel()))
}
