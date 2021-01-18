package net.nanai10a.twomeat.cli.presenters

import com.google.gson.Gson
import net.nanai10a.twomeat.cli.usecases.UserGetOutputData

class DiscordUserGetPresenter(private val transmissioner: DiscordUserGetEventTransmissioner) : IUserGetPresenter {
    private val gson = Gson()

    override fun complete(output: UserGetOutputData) = transmissioner.transmission(
        DiscordUserGetEvent(
            output.sessionData,
            DiscordUserGetViewModel(gson.toJson(output.user))
        )
    )
}
