package net.nanai10a.twomeat.cli

import net.nanai10a.twomeat.cli.inits.interactorDI
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.utils.DIService
import net.nanai10a.twomeat.cli.utils.Env

fun main() {
    val env = Env()

    val service = DIService(env).productionDI().interactorDI()

}
