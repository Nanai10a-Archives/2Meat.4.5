package net.nanai10a.twomeat.cli.presenters.none

class DiscordNonePresenter(private val transmissioner: DiscordNoneEventTransmissioner) : INonePresenter {
    override fun complete(output: NoneOutputData) =
        transmissioner.transmission(
            DiscordNoneEvent(
                output.sessionData,
                DiscordNoneViewModel(output.args)
            )
        )
}
