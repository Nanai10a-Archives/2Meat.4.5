package net.nanai10a.twomeat.cli.presenters.user.save

import net.nanai10a.twomeat.cli.usecases.user.save.UserSaveOutputData

interface IUserSavePresenter {
    fun complete(output: UserSaveOutputData)
}
