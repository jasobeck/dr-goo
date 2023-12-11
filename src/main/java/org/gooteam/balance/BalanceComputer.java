package org.gooteam.balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class BalanceComputer {

    private static final Logger logger = LoggerFactory.getLogger(BalanceComputer.class);

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

    private static final double BASE_GOO_WEIGHT = 0.01;

    public double computeMessageWeight(String message) {
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
