package org.gooteam

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

// wrapper for the bot itself
class Doc(
    token: String
) : ListenerAdapter() {

    private lateinit var bot: JDA

    init {
        val builder = JDABuilder.createDefault(token)
        builder.addEventListeners(this)
        builder.setActivity(Activity.customStatus("gooning"))

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)

        try {
            bot = builder.build()

            bot.awaitReady()
            print("Connected!")
        } catch (e: InvalidTokenException) {
            print("Error encountered: $e")
        }
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        println("msg: ${event.message.contentStripped}")
    }
}