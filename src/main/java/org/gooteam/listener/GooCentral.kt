package org.gooteam.listener

import org.gooteam.event.GooMessage

/**
 * Listener which delegates incoming events to downstream listeners based on prefix.
 */
class GooCentral @JvmOverloads constructor (
    private val listenerMap: MutableMap<String?, MutableSet<GooMessageListener>> = mutableMapOf()
) : GooMessageListener {
    override fun onMessage(message: GooMessage) {
        sendMessageToListeners(null, message)

        // check precedent
        listenerMap.keys.forEach {
            it?.let { key ->
                sendMessageToListeners(key, message)
            }
        }
    }

    private fun sendMessageToListeners(key: String?, message: GooMessage) {
        message.truncate(key)?.let { truncatedMessage ->
            listenerMap[key]?.forEach {
                it.onMessage(truncatedMessage)
            }
        }
    }

    /**
     * Registers a listener which listens for a particular keyword
     * @param listener - listener in question
     * @param keyword - they keyword to watch for - if null, receives all commands
     */
    fun registerListener(listener: GooMessageListener, keyword: String? = null) {
        val keySet = listenerMap[keyword] ?: run {
            val newSet: MutableSet<GooMessageListener> = mutableSetOf()
            listenerMap[keyword] = newSet
            return@run newSet
        }

        keySet.add(listener)
    }
}