package org.gooteam.balance

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BalanceComputerTests {
    private lateinit var computer: BalanceComputer

    @BeforeEach
    fun setUp() {
        computer = BalanceComputer()
    }

    @Test
    fun `balance computer awards greater weight to longer message`() {
        val messageShort = "test"
        val messageLong = "longer test message"

        val shortScore = computer.computeMessageWeight(messageShort)
        val longScore = computer.computeMessageWeight(messageLong)

        Assertions.assertTrue(longScore > shortScore)
    }

    @Test
    fun `balance computer de-emphasizes links when computing score`() {
        val messageLink = "test with link https://youtube.com"
        val messageNoLink = "test without link same length text!"

        val linkScore = computer.computeMessageWeight(messageLink)
        val noLinkScore = computer.computeMessageWeight(messageNoLink)
        Assertions.assertTrue(linkScore < noLinkScore)
    }

    @Test
    fun `computer rewards you for slime`() {
        val slimeMessage = "slime sludge squelch"
        val noSlimeMessage = "similar length msg!!!"

        val slimeScore = computer.computeMessageWeight(slimeMessage)
        val noSlimeScore = computer.computeMessageWeight(noSlimeMessage)

        Assertions.assertTrue(slimeScore > noSlimeScore)
    }
}