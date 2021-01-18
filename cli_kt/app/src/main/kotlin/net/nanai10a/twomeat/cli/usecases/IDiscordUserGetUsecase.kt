package net.nanai10a.twomeat.cli.usecases

interface IDiscordUserGetUsecase {
    fun handle(input: DiscordUserGetInputData): DiscordUserGetOutputData
}
