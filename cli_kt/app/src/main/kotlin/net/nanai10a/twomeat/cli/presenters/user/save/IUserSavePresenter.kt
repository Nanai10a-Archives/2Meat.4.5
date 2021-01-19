package net.nanai10a.twomeat.cli.presenters.user.save

import net.nanai10a.twomeat.cli.presenters.IPresenter
import net.nanai10a.twomeat.cli.usecases.user.save.UserSaveOutputData

interface IUserSavePresenter : IPresenter<UserSaveOutputData> {
    override fun complete(output: UserSaveOutputData)
}
