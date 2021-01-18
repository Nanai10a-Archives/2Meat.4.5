package net.nanai10a.twomeat.cli.usecases

interface IUserGetUsecase {
    fun handle(input: UserGetInputData): UserGetOutputData
}
