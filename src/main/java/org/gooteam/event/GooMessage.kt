package org.gooteam.event

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class GooMessage(
        // truncated message (ie after upstream commands are parsed)
        val content: String,

        // (reaction listening? reply listening?)
        // best flow would be to listen to the message and receive reactions as we go
        // (might want to convert this into a proper class down the line)

        // underlying message event
        val event: MessageReceivedEvent
) {
    // returns a new goomessage w prefix of content truncated if match - else, nothing
    fun truncate(prefix: String?) : GooMessage? {
        return prefix?.let {
            if (content.startsWith(it)) {
                return GooMessage(
                    content.removePrefix(it).trimStart(),
                    event
                )
            } else {
                return null
            }
        // return this if prefix is null
        } ?: this
    }
}
