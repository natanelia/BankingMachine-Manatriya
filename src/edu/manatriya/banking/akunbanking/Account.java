package edu.manatriya.banking.akunbanking;/*Account.java
* Class yang merepresentasikan sebuah account pada banking
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account{
    protected String accountID;
    protected String name;
    protected long saldo;
    private ArrayList<String[]> transactionHistory;


/*

    public Account(String accID, String accName, long accSaldo){
        accountID = accID;
        name = accName;
        saldo = accSaldo;
    }
*/

    public Account(String _accountFileName) {
        transactionHistory = new ArrayList<>();
        String filename = _accountFileName;
        File fileTransaction = new File(filename);
        try {
            Scanner accountScanner = new Scanner(fileTransaction);
            name = accountScanner.nextLine();
            saldo = Long.parseLong(accountScanner.nextLine());

            while (accountScanner.hasNextLine()) {
                String[] rowTransaction = accountScanner.nextLine().split("\\|\\|");
                transactionHistory.add(rowTransaction.clone());
            }
            accountScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assert(false);
        }
    }

/*    public Account(String filename, String filedir) {
        String input = filedir.concat(filename);
        File transactionHistory = new File(input);
        Scanner transactionHistoryScan = null;
        try {
            transactionHistoryScan = new Scanner(transactionHistory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = transactionHistoryScan.nextLine();
        String accName = line;
        long accSaldo = transactionHistoryScan.nextLong();
        while (transactionHistoryScan.hasNextLine()) {
            String[] history = transactionHistoryScan.nextLine().split("\\|\\|");
            ArrayList<String> temp = null;
            for (int i = 0; i < history.length; ++i) {
                temp.add(history[i]);
            }
            addSuccessfulTransaction(temp);
        }
    }*/

    public String getAccountID() {
        return accountID;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public ArrayList<String[]> getTransactionHistory() {
        return transactionHistory;
    }

    public String[] getTransactionHistoryAt(int i) { return transactionHistory.get(i); }

    public void updateSaldo(long amount){
        long tempSaldo = getSaldo() + amount;
        setSaldo(tempSaldo);
    }

    public void addSuccessfulTransaction(String transactionElement){
        transactionHistory.add(transactionElement.split("\\|\\|"));
    }

    public void saveAccount() {

    }

}