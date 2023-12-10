package org.gooteam;

public class Goo {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String arg = (args.length >= 1 ? args[0] : "INVALID_TOKEN");
        System.out.println("token: " + arg);
        // this should be it for now
        Doc doc = new Doc(arg);
    }
}