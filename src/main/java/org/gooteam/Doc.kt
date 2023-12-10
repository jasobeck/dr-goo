package org.gooteam

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.gooteam.event.GooMessage
import org.gooteam.listener.GooCentral
import org.gooteam.listener.GooMessageListener
import org.slf4j.LoggerFactory

// wrapper for the bot itself

class Doc @JvmOverloads constructor(
    token: String,
    private val messageHandler: GooCentral = GooCentral()
) : ListenerAdapter() {

    private lateinit var bot: JDA
    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        val builder = JDABuilder.createDefault(token)
        builder.addEventListeners(this)
        builder.setActivity(Activity.customStatus("gooing off"))

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)

        try {
            bot = builder.build()

            bot.awaitReady()
            logger.info("Connected!")
        } catch (e: InvalidTokenException) {
            print("Error encountered: $e")
        }
    }

    /**
     * Registers a listener - wraps GooCentral instance.
     * @param prefix - prefix to listen for - if null, receives all messages.
     * @param listener - the listener which will receive messages.
     */
    fun registerMessageListener(prefix: String?, listener: GooMessageListener) {
        messageHandler.registerListener(listener, prefix)
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = GooMessage(event.message.contentDisplay, event)
        messageHandler.onMessage(message)
    }
}