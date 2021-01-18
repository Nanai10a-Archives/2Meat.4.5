package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.presenters.id.get.DiscordIdGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.get.DiscordUserGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.save.DiscordUserSaveEventTransmissioner
import net.nanai10a.twomeat.cli.usecases.SessionData
import net.nanai10a.twomeat.cli.utils.Env
import net.nanai10a.twomeat.cli.utils.ServiceProvider
import java.util.*

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
            TODO("面倒くさい太郎")
        }

        override fun onPrivateMessageReceived(event: PrivateMessageReceivedEvent) {
            when (event.message.contentRaw) {
                "getId" -> controller.getId(SessionData(UUID.randomUUID()), event.author.id)
                "getUser" -> TODO("面倒くさい太郎")
                "save" -> TODO("面倒くさい太郎")
            }
        }
    })
}
