package org.gooteam;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CheckGooBalance implements GooMessageListener {

    public BalanceRepository balanceRepo;
    public CheckGooBalance(BalanceRepository balanceRepo) {
        this.balanceRepo = balanceRepo;
    }

    @Override
    //displays goo balance for user
    public void onMessage(@NotNull GooMessage message) {
        final MessageReceivedEvent event = message.getEvent();
        User author = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        NumberFormat formatter = new DecimalFormat("#0.000");
        textChannel.sendMessage("You have " + formatter.format(balanceRepo.getBalance(author.getId())) + " cm3 of goo in your pocket").queue();
    }
}
