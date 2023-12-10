package org.gooteam.listener

import org.gooteam.event.GooMessage

interface GooMessageListener {
    /**
     * Receives message events
     * @param message - message data class (for now)
     */
    fun onMessage(message: GooMessage)

}
