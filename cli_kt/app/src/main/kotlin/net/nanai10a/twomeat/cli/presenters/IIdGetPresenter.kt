package net.nanai10a.twomeat.cli.presenters

import net.nanai10a.twomeat.cli.usecases.IdGetOutputData

interface IIdGetPresenter {
    fun complete(output: IdGetOutputData)
}
