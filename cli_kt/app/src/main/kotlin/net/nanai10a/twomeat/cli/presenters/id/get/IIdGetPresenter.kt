package net.nanai10a.twomeat.cli.presenters.id.get

import net.nanai10a.twomeat.cli.presenters.IPresenter
import net.nanai10a.twomeat.cli.usecases.id.get.IdGetOutputData

interface IIdGetPresenter : IPresenter<IdGetOutputData> {
    override fun complete(output: IdGetOutputData)
}
