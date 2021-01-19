package net.nanai10a.twomeat.cli.presenters.user.get

import com.google.gson.Gson
import net.nanai10a.twomeat.cli.usecases.user.get.UserGetOutputData

class DiscordUserGetPresenter(private val transmissioner: DiscordUserGetEventTransmissioner) : IUserGetPresenter {
    private val gson = Gson()

    override fun complete(output: UserGetOutputData) =
        transmissioner.transmission(
            DiscordUserGetEvent(
                output.sessionData,
                DiscordUserGetViewModel(gson.toJson(output.user))
            )
        )
}
