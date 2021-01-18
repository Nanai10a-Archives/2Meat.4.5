package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.commands.CommandListener
import net.nanai10a.twomeat.cli.controllers.IdController
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.presenters.id.get.DiscordIdGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.get.DiscordUserGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.save.DiscordUserSaveEventTransmissioner
import net.nanai10a.twomeat.cli.utils.Env
import net.nanai10a.twomeat.cli.utils.ServiceProvider

fun main() {
    val env = Env()

    val userGetTransmissioner = DiscordUserGetEventTransmissioner()
    val userSaveTransmissioner = DiscordUserSaveEventTransmissioner()
    val idGetTransmissioner = DiscordIdGetEventTransmissioner()

    val jda = JDABuilder.createLight(env.discordToken).build()
    jda.awaitReady()

    val service =
        ServiceProvider(env)
            .productionDI(
                jda,
                userGetTransmissioner,
                userSaveTransmissioner,
                idGetTransmissioner
            )

    val commandListener = CommandListener()

    service.create(UserController::class.java)
    service.create(IdController::class.java)

    jda.addEventListener(object : ListenerAdapter() {
        override fun onMessageReceived(event: MessageReceivedEvent) =
            commandListener.onCommand(event.message.contentRaw)
    })
}
