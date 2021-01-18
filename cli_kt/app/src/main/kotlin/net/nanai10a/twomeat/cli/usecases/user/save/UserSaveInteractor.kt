package net.nanai10a.twomeat.cli.usecases.user.save

import net.nanai10a.twomeat.cli.gateways.user.IUserRepository
import net.nanai10a.twomeat.cli.presenters.user.save.IUserSavePresenter

class UserSaveInteractor(private val presenter: IUserSavePresenter, private val repository: IUserRepository) :
    IUserSaveUsecase {
    override fun handle(input: UserSaveInputData) {
        repository.save(input.user)
        this.presenter.complete(UserSaveOutputData(input.sessionData))
    }
}
