package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.commands.CommandListener
import net.nanai10a.twomeat.cli.controllers.IdController
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.utils.Env
import net.nanai10a.twomeat.cli.utils.ServiceProvider

fun main() {
    val env = Env()

    val jda = JDABuilder.createLight(env.discordToken).build()
    jda.awaitReady()

    val service =
        ServiceProvider(env)
            .productionDI(jda)

    val userController = service.create(UserController::class.java)
    val idController = service.create(IdController::class.java)

    val commandListener = CommandListener()

    // add commands.

    jda.addEventListener(object : ListenerAdapter() {
        override fun onMessageReceived(event: MessageReceivedEvent) =
            commandListener.onCommand(event.message.contentRaw)
    })
}
