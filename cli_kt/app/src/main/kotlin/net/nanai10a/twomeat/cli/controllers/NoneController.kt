package net.nanai10a.twomeat.cli.controllers

import net.nanai10a.twomeat.cli.presenters.DiscordMessage
import net.nanai10a.twomeat.cli.presenters.none.DiscordNonePresenter
import net.nanai10a.twomeat.cli.presenters.none.NoneOutputData
import net.nanai10a.twomeat.cli.usecases.SessionData

class NoneController(
    private val presenter: DiscordNonePresenter
    // FIXME: これ, CommandごとにUsecaseを定義してInteractorで動作を実装すべきだと思うんですよ, Entitiesに依存しないUsecaseって意味わからないけど
    //        そう考えるとCommandごとにUsecaseが発生して動作を定義しなければなりません(Useのcasesだから当たり前なんですが...<-
) {
    fun send(sessionData: SessionData, args: List<String>) {
        val message = DiscordMessage(TODO())

        presenter.complete(NoneOutputData(sessionData, message))
    }
}
