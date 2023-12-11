package org.gooteam;

import org.gooteam.balance.BalanceTicker;
import org.gooteam.listener.GooCentral;
import org.gooteam.listener.debug.DebugMessageLogger;
import java.io.IOException;

public class Goo {
    static final String PREFIX = "hi dr goo";
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        String arg = (args.length >= 1 ? args[0] : "INVALID_TOKEN");
        System.out.println("token: " + arg);
        // this should be it for now
        Doc doc = new Doc(arg);
        BalanceRepository balanceRepo = new BalanceRepository();
        // base command registry, add event listeners for commands here
        GooCentral rootCommand = new GooCentral();



        doc.registerMessageListener(PREFIX, rootCommand);
        doc.registerMessageListener(null, new DebugMessageLogger());
        doc.registerMessageListener(null, new BalanceTicker(balanceRepo));
        doc.registerMessageListener(PREFIX, new Introduction());
        doc.registerMessageListener("hey dr goo check balance", new CheckGooBalance(balanceRepo));
        doc.registerMessageListener("die dr goo", new SecretSauce());
        doc.registerMessageListener("dr goo fortune", new FortuneTell());

    }
}