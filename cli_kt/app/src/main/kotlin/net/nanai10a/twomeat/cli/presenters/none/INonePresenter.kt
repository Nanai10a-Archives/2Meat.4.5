package net.nanai10a.twomeat.cli.presenters.none

import net.nanai10a.twomeat.cli.presenters.IPresenter

interface INonePresenter : IPresenter<NoneOutputData> {
    override fun complete(output: NoneOutputData)
}
