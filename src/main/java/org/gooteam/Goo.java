package org.gooteam;

public class Goo {
    public static void main(String[] args) {

        // need to wrap builder a bit so that we can do like simple bullshit
        // figure out an arch pattern that's centered around commands
        // that's my thought at least
        // i think it makes sense that each "command" should be handled by a listener
        // ergo: one listener can handle multiple commands

        // how to cross pollinate?? (ex leveling systems, etc?)
        // - flag for messages
        // - recipients receive messages which pass flag
        System.out.println("Hello world!");
    }
}