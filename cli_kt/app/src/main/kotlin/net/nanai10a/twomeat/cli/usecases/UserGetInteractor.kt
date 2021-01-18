package net.nanai10a.twomeat.cli.usecases

import net.nanai10a.twomeat.cli.gateways.IUserRepository
import net.nanai10a.twomeat.cli.presenters.IUserGetPresenter

class UserGetInteractor(private val presenter: IUserGetPresenter, private val repository: IUserRepository) :
    IUserGetUsecase {
    override fun handle(input: UserGetInputData) {
        this.presenter.complete(UserGetOutputData(this.repository.load(input.discordId)))
    }
}
