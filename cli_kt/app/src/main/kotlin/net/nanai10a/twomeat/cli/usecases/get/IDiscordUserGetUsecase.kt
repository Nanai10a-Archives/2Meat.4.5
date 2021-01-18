package net.nanai10a.twomeat.cli.db.usecases.get

interface IDiscordUserGetUsecase {
    fun handle(input: DiscordUserGetInputData): DiscordUserGetOutputData
}
