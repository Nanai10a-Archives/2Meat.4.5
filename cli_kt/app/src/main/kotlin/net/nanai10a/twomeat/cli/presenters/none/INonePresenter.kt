package net.nanai10a.twomeat.cli.presenters.none

import net.nanai10a.twomeat.cli.presenters.IPresenter

interface INonePresenter : IPresenter<String> {
    override fun complete(@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE") rawString: String)
}
