package net.nanai10a.twomeat.cli.usecases

import net.nanai10a.twomeat.cli.gateways.IUserRepository
import net.nanai10a.twomeat.cli.presenters.IUserSavePresenter

class UserSaveInteractor(private val presenter: IUserSavePresenter, private val repository: IUserRepository) :
    IUserSaveUsecase {
    override fun handle(input: UserSaveInputData) {
        repository.save(input.user)
        this.presenter.complete(UserSaveOutputData())
    }
}
