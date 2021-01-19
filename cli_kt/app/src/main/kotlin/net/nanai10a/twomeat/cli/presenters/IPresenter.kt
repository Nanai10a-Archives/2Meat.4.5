package net.nanai10a.twomeat.cli.presenters

interface IPresenter<O> {
    fun complete(output: O)
}
