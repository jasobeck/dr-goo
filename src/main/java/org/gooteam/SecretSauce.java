package org.gooteam;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;

public class SecretSauce implements GooMessageListener {
    @Override
    public void onMessage(@NotNull GooMessage message) {
        final MessageReceivedEvent event = message.getEvent();
        String thehunger;
        String resultA = "*poots softly*";
        String resultB = "*cums and dies*";
        double randomNumber = Math.random();
        if(randomNumber > 0.5){
            thehunger = resultA;
        }
        else{
            thehunger = resultB;
        }
        TextChannel pissbaby = event.getChannel().asTextChannel();
        pissbaby.sendMessage(thehunger + "\n (Your conversation with Dr. Goo appears to be going nowhere.)").queue();

    }
}
