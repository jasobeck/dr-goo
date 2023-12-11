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
        String ResultMessage;

        String[] FlavorString = {
                "*Dr Goo farts!*",
                "*Dr Goo shids!*",
                "*Dr Goo sharts!*",
        };

        String[] FortuneString = {
                "IMMINENT DEMISE",
                "DISASTER",
                "BAD LUCK",
                "NO LUCK",
                "GOOD LUCK",
                "GREAT FORTUNE!",
                "PERFECT ALIGNMENT",
        };

        String[] FailureString = {
                "Fortune telling failed! Fuck!",
                "Epic Fail!",
                "EPIC FAIL!!!",
        };

        double FortuneFailChance = Math.random();

        if(FortuneFailChance < 0.3) {
            int FailureRoll = (int) Math.round(Math.random() * 2);
            ResultMessage = FailureString[FailureRoll];
        }
        else{
            int FortuneRoll = (int) Math.round(Math.random() * 6);
            ResultMessage = FortuneString[FortuneRoll];
        }
        int FlavorRoll = (int) Math.round(Math.random() * 2);
        FlavorMessage = FlavorString[FlavorRoll];

        TextChannel ThisChannel = event.getChannel().asTextChannel();
        ThisChannel.sendMessage(FlavorMessage + " Your fortune:" + ResultMessage).queue();

    }
}
