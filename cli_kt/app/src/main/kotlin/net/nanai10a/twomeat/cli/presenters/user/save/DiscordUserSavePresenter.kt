package net.nanai10a.twomeat.cli.presenters.user.save

import net.nanai10a.twomeat.cli.usecases.user.save.UserSaveOutputData

class DiscordUserSavePresenter(private val transmissioner: DiscordUserSaveEventTransmissioner) : IUserSavePresenter {
    override fun complete(output: UserSaveOutputData) =
        transmissioner.transmission(DiscordUserSaveEvent(output.sessionData, DiscordUserSaveViewModel()))
}
