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
    public void onMessage(GooMessage message) {
        final MessageReceivedEvent event = message.getEvent();
        if (event.getAuthor().isBot()) return;
        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        String nickname = member.getNickname();
        Role role = event.getGuild().getPublicRole();
        TextChannel textChannel = event.getChannel().asTextChannel();
        textChannel.sendMessage("hi " + nickname + ". im dr goo").queue();
    }
}
