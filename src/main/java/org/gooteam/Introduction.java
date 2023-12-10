package org.gooteam;

import kotlin.Unit;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;
public class Introduction implements GooMessageListener{

    @Override
    public Unit onMessage(GooMessage message) {
        final MessageReceivedEvent event = message.event;
        if (event.getAuthor().isBot()) return Unit.INSTANCE;
        User author = event.getAuthor();
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        String nickname = member.getNickname();
        Role role = event.getGuild().getPublicRole();
        TextChannel textChannel = event.getGuild().getTextChannelsByName(String.valueOf(channel), true).get(0);
        textChannel.sendMessage("hi oomphie").queue();
        return Unit.INSTANCE;
    }
}
