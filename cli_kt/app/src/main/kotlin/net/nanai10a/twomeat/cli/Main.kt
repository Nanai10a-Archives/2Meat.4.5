package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.commands.CommandListener
import net.nanai10a.twomeat.cli.commands.impl.PingPongCommand
import net.nanai10a.twomeat.cli.controllers.*
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.usecases.SessionData
import net.nanai10a.twomeat.cli.utils.Env
import net.nanai10a.twomeat.cli.utils.ServiceProvider

fun main() {
    val env = Env()

    val jda = JDABuilder.createLight(env.discordToken).build()
    jda.awaitReady()

    val destinationStore = DiscordViewDestinationStore()

    val service =
        ServiceProvider(env)
            .productionDI(jda, destinationStore)

    val userController = service.create(UserController::class.java)
    val idController = service.create(IdController::class.java)
    val noneController = service.create(NoneController::class.java)

    val commandListener = CommandListener()

    with(commandListener) {
        addCommandFunction(PingPongCommand(noneController))
    }

    jda.addEventListener(object : ListenerAdapter() {
        override fun onMessageReceived(event: MessageReceivedEvent) {
            val types =
                if (event.isFromGuild)
                    DiscordViewDestinationTypes.GUILD
                else
                    DiscordViewDestinationTypes.PRIVATE

            val sessionData = SessionData()
            destinationStore.store[sessionData] = DiscordDestination(event.channel.id, types)
            commandListener.onCommand(sessionData, event.message.contentRaw)
        }
    })
}
