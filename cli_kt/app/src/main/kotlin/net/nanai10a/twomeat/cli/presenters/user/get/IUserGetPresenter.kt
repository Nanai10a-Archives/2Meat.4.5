package net.nanai10a.twomeat.cli.presenters.user.get

import net.nanai10a.twomeat.cli.presenters.IPresenter
import net.nanai10a.twomeat.cli.usecases.user.get.UserGetOutputData

interface IUserGetPresenter : IPresenter<UserGetOutputData> {
    override fun complete(output: UserGetOutputData)
}
