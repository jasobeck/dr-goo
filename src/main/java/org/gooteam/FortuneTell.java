package org.gooteam;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class FortuneTell implements GooMessageListener {
    @Override
    public void onMessage(@NotNull GooMessage message) {
        final MessageReceivedEvent event = message.getEvent();
        String FlavorMessage;
        String FortuneMessage;

        String[] FlavorText = {
                "*Dr Goo farts!*",
                "*Dr Goo shids!*",
                "*Dr Goo sharts!*",
        };

        int FlavorRoll = (int) Math.round(Math.random() * 2);
        FlavorMessage = FlavorText[FlavorRoll];
        String[] Fortunes = {
                "IMMINENT DEMISE",
                "DISASTER",
                "BAD LUCK",
                "NO LUCK",
                "GOOD LUCK",
                "GREAT FORTUNE!",
                "PERFECT ALIGNMENT",
        };

        //double value = nextGaussian();
        int FortuneRoll = (int) Math.round(Math.random() * 6);
        FortuneMessage = Fortunes[FortuneRoll];
        TextChannel ThisChannel = event.getChannel().asTextChannel();
        ThisChannel.sendMessage(FlavorMessage + " Your fortune:" + FortuneMessage).queue();

    }
}
