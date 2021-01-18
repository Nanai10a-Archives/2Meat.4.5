package net.nanai10a.twomeat.cli

import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.controllerDI
import net.nanai10a.twomeat.cli.inits.interactorDI
import net.nanai10a.twomeat.cli.inits.presenterDI
import net.nanai10a.twomeat.cli.utils.Env
import net.nanai10a.twomeat.cli.utils.ServiceProvider

fun main() {
    val env = Env()

    val service = ServiceProvider(env).presenterDI().interactorDI().controllerDI()

    val controller = service.create(UserController::class.java) as UserController


}
