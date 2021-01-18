package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.UserSaveOutputData

interface IUserSavePresenter {
    fun complete(output: UserSaveOutputData)
}
