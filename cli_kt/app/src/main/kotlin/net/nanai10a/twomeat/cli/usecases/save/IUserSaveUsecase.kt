package net.nanai10a.twomeat.cli.db.usecases.save

interface IUserSaveUsecase {
    fun handle(input: UserSaveInputData): UserSaveOutputData
}
