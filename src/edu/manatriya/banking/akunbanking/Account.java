package edu.manatriya.banking.akunbanking;/*Account.java
* Class yang merepresentasikan sebuah account pada banking
 */

import edu.manatriya.banking.PluginTransaksi.Transaction;
import java.util.ArrayList;

public class Account{
    protected String accountID;
    protected String name;
    protected long saldo;
    private ArrayList<ArrayList<String>> transactionHistory;

    public Account(String accID, String accName, long accSaldo){
        accountID = accID;
        name = accName;
        saldo = accSaldo;
    }

    public String getAccountID() {
        return accountID;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public ArrayList<ArrayList<String>> getTransactionHistory() {
        return transactionHistory;
    }

    public void updateSaldo(int amount){
        long   tempSaldo= getSaldo() + amount;
        setSaldo(tempSaldo);
    }

    public void  addSuccessfulTransaction(ArrayList<String> transaction){
        transactionHistory.add(transaction);
    }

}