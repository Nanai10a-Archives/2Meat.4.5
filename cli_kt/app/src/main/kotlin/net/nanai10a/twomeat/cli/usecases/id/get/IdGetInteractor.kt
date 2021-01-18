package net.nanai10a.twomeat.cli.usecases.id.get

import net.nanai10a.twomeat.cli.gateways.id.IIdRepository
import net.nanai10a.twomeat.cli.presenters.id.get.IIdGetPresenter

class IdGetInteractor(private val presenter: IIdGetPresenter, private val repository: IIdRepository) : IIdGetUsecase {
    override fun handle(input: IdGetInputData) =
        this.presenter.complete(IdGetOutputData(input.sessionData, this.repository.load(input.discordId)))
}
