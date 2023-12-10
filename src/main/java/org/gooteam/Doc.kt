package org.gooteam

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

// wrapper for the bot itself
class Doc(
    private val token: String
) : ListenerAdapter() {

    private lateinit var bot: JDA

    init {
        val builder = JDABuilder.createDefault(token)
        builder.addEventListeners(this)
        builder.setActivity(Activity.customStatus("gooning"))
        bot = builder.build()
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        print("hello: ${event.message.contentRaw}")
    }
}