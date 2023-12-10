package org.gooteam.listener.debug

import org.gooteam.event.GooMessage
import org.gooteam.listener.GooMessageListener
import org.slf4j.LoggerFactory

class DebugMessageLogger : GooMessageListener {

    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun onMessage(message: GooMessage) {
        logger.info("message from {}: {}", message.event.author.name, message.content)
    }
}