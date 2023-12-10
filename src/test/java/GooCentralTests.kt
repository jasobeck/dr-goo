import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.gooteam.event.GooMessage
import org.gooteam.listener.GooCentral
import org.gooteam.listener.GooMessageListener
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.kotlin.*

class GooCentralTests {
    @Test
    fun `confirm goo central sends all incoming messages to null listener`() {
        val mock = mock<MessageReceivedEvent>{}
        val message = GooMessage("test message", mock)

        val instance = GooCentral()

        val mockListener = mock<GooMessageListener>{}

        instance.registerListener(mockListener, null)
        instance.onMessage(message)

        verify(mockListener).onMessage(any())
    }

    @Test
    fun `confirm goo central truncates messages if they match listener`() {
        val mock = mock<MessageReceivedEvent>{}

        // tba: truncate around spaces?
        val message = GooMessage("test 123", mock)

        val instance = GooCentral()
        val mockListener = mock<GooMessageListener>{}

        instance.registerListener(mockListener, "test")
        instance.onMessage(message)

        argumentCaptor<GooMessage>().apply {
            verify(mockListener).onMessage(capture())
            assertEquals("123", firstValue.content)
        }

    }
}