package net.nanai10a.twomeat.cli.presenters.id.get

import net.nanai10a.twomeat.cli.usecases.id.get.IdGetOutputData

interface IIdGetPresenter {
    fun complete(output: IdGetOutputData)
}
