package org.gooteam.balance;

import org.gooteam.BalanceRepository;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalanceTicker implements GooMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(BalanceTicker.class);

    private final BalanceRepository repo;

    private final BalanceComputer computer;

    public BalanceTicker(BalanceRepository repo) {
        this.repo = repo;
        this.computer = new BalanceComputer();
    }

    @Override
    public void onMessage(@NotNull GooMessage message) {
        logger.info("running balance ticker functionality!!!");
        if (!message.getEvent().getAuthor().isBot()) {
            String userId = message.getEvent().getAuthor().getId();
            double balanceDelta = computer.computeMessageWeight(message.getContent());
            repo.changeBalance(
                    message.getEvent().getAuthor().getId(),
                    balanceDelta
            );


            logger.info("uptick balance for ID {}: {} cm3", userId, balanceDelta);
        } else {
            logger.warn("ignored message from bot user");
        }
    }
}
