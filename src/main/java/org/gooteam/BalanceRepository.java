package org.gooteam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class BalanceRepository {
    public HashMap<String, Double> balanceList = new HashMap<>();
    public BalanceRepository() throws IOException {
        populateBalanceList(balanceList);
    }

    //populates balanceList HashMap from a balanceList csv Document
    public void populateBalanceList(HashMap<String, Double> balanceList) throws IOException {
        File balanceListFile = new File("balanceList.csv");
        if (!balanceListFile.createNewFile()) {
            Scanner fileScanner = new Scanner(balanceListFile);
            while (fileScanner.hasNextLine()) {
                String entry = fileScanner.nextLine();
                String[] splitEntry = entry.split(",");
                String userIDKey = splitEntry[0];
                Double userBalanceValue = Double.parseDouble(splitEntry[1]);
                balanceList.put(userIDKey, userBalanceValue);
            }
            fileScanner.close();
        }
    }

    //makes a balance for user if they don't have one already.
    public void newBalance(String userID) {
        if (!this.balanceList.containsKey(userID)) {
            this.balanceList.put(userID, 100.00);
        }

    }

    //updates user balance with desired amount when passing userID and double representing the change in balance
    public void changeBalance(String userID, Double balanceChange) {
        newBalance(userID);
        this.balanceList.put(userID, (this.balanceList.get(userID) + balanceChange));
    }

    //returns the value of the balance when passed the userID
    public double getBalance(String userID) {
        newBalance(userID);
        return this.balanceList.get(userID);
    }


}
