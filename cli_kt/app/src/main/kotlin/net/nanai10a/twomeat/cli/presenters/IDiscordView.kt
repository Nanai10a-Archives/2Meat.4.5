package net.nanai10a.twomeat.cli.presenters

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.nanai10a.twomeat.cli.controllers.DiscordDestination
import net.nanai10a.twomeat.cli.controllers.DiscordViewDestinationTypes
import net.nanai10a.twomeat.cli.usecases.SessionData

interface IDiscordView<M> {
    val jda: JDA
    fun invoke(sessionData: SessionData, model: M)
    fun send(destination: DiscordDestination, message: DiscordMessage) {
        message.charSequence?.let {
            return when (destination.types) {
                DiscordViewDestinationTypes.PRIVATE -> this.jda.getTextChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
                DiscordViewDestinationTypes.GUILD -> this.jda.getPrivateChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
            }
        }
        message.message?.let {
            return when (destination.types) {
                DiscordViewDestinationTypes.PRIVATE -> this.jda.getTextChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
                DiscordViewDestinationTypes.GUILD -> this.jda.getPrivateChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
            }
        }
        message.embed?.let {
            return when (destination.types) {
                DiscordViewDestinationTypes.PRIVATE -> this.jda.getTextChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
                DiscordViewDestinationTypes.GUILD -> this.jda.getPrivateChannelById(destination.channelId)!!
                    .sendMessage(it).queue()
            }
        }

        throw RuntimeException("Unreachable code point!")
    }
}

data class DiscordMessage(
    val charSequence: CharSequence? = null,
    val message: Message? = null,
    val embed: MessageEmbed? = null
)
