package net.nanai10a.twomeat.cli

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.nanai10a.twomeat.cli.controllers.IdController
import net.nanai10a.twomeat.cli.controllers.UserController
import net.nanai10a.twomeat.cli.inits.productionDI
import net.nanai10a.twomeat.cli.presenters.id.get.DiscordIdGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.get.DiscordUserGetEventTransmissioner
import net.nanai10a.twomeat.cli.presenters.user.save.DiscordUserSaveEventTransmissioner
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
        ServiceProvider(env)
            .productionDI(
                jda,
                userGetTransmissioner,
                userSaveTransmissioner,
                idGetTransmissioner
            )

    val commandListener =
        CommandListener(
            service.create(UserController::class.java),
            service.create(IdController::class.java)
        )

    jda.addEventListener(object : ListenerAdapter() {
        override fun onMessageReceived(event: MessageReceivedEvent) =
            commandListener.onCommand(parseCommand(event.message.contentRaw))
    })
}

class CommandListener(
    private val userController: UserController,
    private val idController: IdController
) {
    fun onCommand(commands: List<String>): Unit {

    }
}

/**
 * **rules:**
 *
 * 1. \の次の文字は必ずそのまま受け取る.
 * 2. ""で囲われた文字列は結合した状態で一つの文字列として扱われる. (又, その""は無いものとして扱う.)
 * 3. スペースは区切りとして受け取る.
 * 4. その他の文字は文字列の一部として受け取る.
 *
 * ※スペース: 半角, 全角, TAB, 改行(\r & \n)
 */
fun parseCommand(rawCommand: String): List<String> {
    val commands = mutableListOf<String>()
    val charBuffer = mutableListOf<Char>()
    var isReamingDoubleQuotation = false
    var isNextCharEscape = false

    rawCommand.forEach { char ->
        // 1.
        if (isNextCharEscape) {
            charBuffer.add(char)
            return@forEach
        }

        val charStr = char.toString()

        // 1.
        if (charStr == "\\") {
            isNextCharEscape = true
            return@forEach
        }

        // 2.
        if (charStr == "\"") {
            if (isReamingDoubleQuotation) {
                isReamingDoubleQuotation = false
                return@forEach
            } else {
                isReamingDoubleQuotation = true
                return@forEach
            }
        }

        // 2.
        if (isReamingDoubleQuotation) {
            charBuffer.add(char)
            return@forEach
        }

        // 3.
        if (
            charStr == "\u0020"
            || charStr == "\u3000"
            || charStr == "\u0009"
            || charStr == "\u000d"
            || charStr == "\u000a"
        ) {
            if (charBuffer.isEmpty())
                return@forEach

            var command = ""
            charBuffer.forEach { _char ->
                command += command + _char
            }

            charBuffer.clear()

            commands.add(command)
            return@forEach
        }

        // 4.
        charBuffer.add(char)
    }

    return commands
}
