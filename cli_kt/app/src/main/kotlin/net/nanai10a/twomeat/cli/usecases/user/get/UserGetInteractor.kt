package net.nanai10a.twomeat.cli.usecases.user.get

import net.nanai10a.twomeat.cli.gateways.user.IUserRepository
import net.nanai10a.twomeat.cli.presenters.user.get.IUserGetPresenter

class UserGetInteractor(private val presenter: IUserGetPresenter, private val repository: IUserRepository) :
    IUserGetUsecase {
    override fun handle(input: UserGetInputData) {
        this.presenter.complete(UserGetOutputData(input.sessionData, this.repository.load(input.id)))
    }
}
