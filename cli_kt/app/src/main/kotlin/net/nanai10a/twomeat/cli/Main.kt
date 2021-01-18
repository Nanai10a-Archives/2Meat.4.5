package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.presenters.DiscordIdGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.DiscordUserGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.DiscordUserSaveEventTransmissioner
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
        ServiceProvider(env).productionDI(jda, userSaveTransmissioner, userGetTransmissioner, idGetTransmissioner)

    val controller = service.create(UserController::class.java)

    jda.addEventListener(object : ListenerAdapter() {
        override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
            TODO()
        }

        override fun onPrivateMessageReceived(event: PrivateMessageReceivedEvent) {
            when (event.message.contentRaw) {
                "get" -> {
                    TODO()
                }
                "save" -> {
                    TODO()
                }
            }
        }
    })
}
