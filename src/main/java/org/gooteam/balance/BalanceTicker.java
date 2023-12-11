package org.gooteam.balance;

import org.gooteam.BalanceRepository;
import org.gooteam.event.GooMessage;
import org.gooteam.listener.GooMessageListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class BalanceTicker implements GooMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(GooMessageListener.class);

    private static final double BASE_GOO_WEIGHT = 0.01;

    private static final HashSet<String> SLIME_STRINGS = new HashSet<>(
        Arrays.asList(
            "slime",
            "slimy",
            "goop",
            "squelch",
            "bubble",
            "splat"
        )
    );

    private final BalanceRepository repo;

    public BalanceTicker(BalanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onMessage(@NotNull GooMessage message) {
        logger.info("running balance ticker functionality!!!");
        String userId = message.getEvent().getAuthor().getId();
        double balanceDelta = computeMessageWeight(message.getContent());
        repo.changeBalance(
                message.getEvent().getAuthor().getId(),
                balanceDelta
        );


        logger.info("uptick balance for ID {}: {} cm3", userId, balanceDelta);
    }

    private double computeMessageWeight(String message) {
        // remove all links from message
        String urlRemove = Pattern.compile("http\\S+").matcher(message).replaceAll("");
        logger.info("sanitized message: {}", urlRemove);
        double lengthWeight = Math.clamp(Math.log(Math.max(urlRemove.length(), 1)), 0.1, 8.0);
        double slimeWeight = parseSlime(urlRemove);

        // parse for slime words
        return BASE_GOO_WEIGHT * lengthWeight * slimeWeight;
    }

    private double parseSlime(String message) {
        double slimeWeight = 1.0;

        // splits on whitespace chars
        String[] words = message.split("\\s+");
        for (String word : words) {
            if (SLIME_STRINGS.contains(word)) {
                slimeWeight += 1.0;
            }
        }

        return 1.0 + Math.log(slimeWeight + 1.0) * 0.25;
    }
}
