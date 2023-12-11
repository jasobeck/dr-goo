package org.gooteam;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;

public class FortuneTell implements GooMessageListener {
    @Override
    public void onMessage(@NotNull GooMessage message) {
        final MessageReceivedEvent event = message.getEvent();

        String FlavorMessage;
        String ResultMessage;

        String[] FlavorString = {
                "*Dr. Goo makes something spin around!*",
                "*Dr. Goo ogles your breasts!*",
                "*Dr. Goo consults Reddit!*",
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
                "*Unable to read the stars at this time!*",
                "`ERR_GOD_NOT_RESOLVED`",
                "*Future obscured by mass death, try again!",
        };

        double FortuneFailChance = Math.random();

        if(FortuneFailChance < 0.3) {
            int FailureRoll = (int) Math.floor(Math.random() * 3);
            ResultMessage = FailureString[FailureRoll];
        }
        else{
            int FortuneRoll = (int) Math.floor(Math.random() * 7);
            ResultMessage = FortuneString[FortuneRoll];
        }
        int FlavorRoll = (int) Math.floor(Math.random() * 3);
        FlavorMessage = FlavorString[FlavorRoll];

        TextChannel ThisChannel = event.getChannel().asTextChannel();
        ThisChannel.sendMessage(FlavorMessage + "\n\nYour fortune: " + ResultMessage).queue();

    }
}
