package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.UserGetOutputData

interface IUserGetPresenter {
    fun complete(output: UserGetOutputData)
}
