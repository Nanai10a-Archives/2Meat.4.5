package net.nanai10a.twomeat.cli.commands.impl

import net.nanai10a.twomeat.cli.commands.CommandFunction
import net.nanai10a.twomeat.cli.commands.CommandFunctionInfo
import net.nanai10a.twomeat.cli.controllers.NoneController
import net.nanai10a.twomeat.cli.usecases.SessionData

class PingPongCommand(private val controller: NoneController) : CommandFunction {
    override val info: CommandFunctionInfo = CommandFunctionInfo(1, listOf(""), false)
    override fun call(sessionData: SessionData, args: List<String>) =
        controller.send(sessionData, args)
}
