package org.gooteam;

import org.gooteam.listener.GooCentral;
import org.gooteam.listener.debug.DebugMessageLogger;

public class Goo {
    static final String PREFIX = "hi dr goo";
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String arg = (args.length >= 1 ? args[0] : "INVALID_TOKEN");
        System.out.println("token: " + arg);
        // this should be it for now
        Doc doc = new Doc(arg);

        // base command registry, add event listeners for commands here
        GooCentral rootCommand = new GooCentral();



        doc.registerMessageListener(PREFIX, rootCommand);
        doc.registerMessageListener(null, new DebugMessageLogger());
        doc.registerMessageListener(PREFIX, new Introduction());
    }
}