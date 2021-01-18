package net.nanai10a.twomeat.cli.usecases

import net.nanai10a.twomeat.cli.gateways.IIdRepository
import net.nanai10a.twomeat.cli.presenters.IIdGetPresenter

class IdGetInteractor(private val presenter: IIdGetPresenter, private val repository: IIdRepository) : IIdGetUsecase {
    override fun handle(input: IdGetInputData) =
        this.presenter.complete(IdGetOutputData(input.sessionData, this.repository.load(input.discordId)))
}
