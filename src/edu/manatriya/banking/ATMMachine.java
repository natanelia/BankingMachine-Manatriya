package edu.manatriya.banking;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class ATMMachine {
    Account acc;

    public void run(){
        try {
            boolean found = false;
            Scanner inputreader = new Scanner(System.in);
            String input = inputreader.nextLine();
            Scanner filescan = null;
            filescan = new Scanner(new File("out/Accounts/account.txt"));

            while (filescan.hasNextLine() && (!found)) {
                String line = filescan.nextLine();
                if (line.contains(input))
                    found = true;
            }
            if (found) {
                String accID= input;
                input.concat(".acc");
                File transactionHistory = new File(input);
                Scanner transactionHistoryScan = new Scanner(transactionHistory);
                String line = transactionHistoryScan.nextLine();
                String accName = line;
                long accSaldo = transactionHistoryScan.nextLong();

                if (input.contains("cr")){
                    acc = new CreditAccount(accID,accName,accSaldo);
                }
                else {
                    acc = new DebitAccount(accID, accName, accSaldo);
                }
                while (transactionHistoryScan.hasNextLine()){
                    String[] history = transactionHistoryScan.nextLine().split("||");
                    ArrayList<String> temp = null;
                    for (int i = 0; i < history.length; ++i){
                        temp.add(history[i]);
                    }
                    acc.addSuccessfulTransaction(temp);
                }
            }
            else {
                System.out.println("account not found");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
