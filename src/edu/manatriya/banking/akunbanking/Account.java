package edu.manatriya.banking.akunbanking;

/*Account.java
* Class yang merepresentasikan sebuah account pada banking
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account{
    protected String accountFileName;
    protected String accountID;
    protected String password;
    protected String name;
    protected String currency;
    protected double saldo;
    protected ArrayList<String[]> transactionHistory;

    public Account(String accountID, String password) {
        this.accountID = accountID;
        this.accountFileName = "";
        this.password = password;
        this.name = "";
        this.currency = "";
        this.saldo = 0;
        transactionHistory = new ArrayList<String[]>();
    }

    public Account(String _accountFileName) {
        transactionHistory = new ArrayList<String[]>();
        accountFileName = _accountFileName;
        File fileTransaction = new File(accountFileName);
        accountID = fileTransaction.getName().replace(".acc","");
        try {
            Scanner accountScanner = new Scanner(fileTransaction);
            password = accountScanner.nextLine();
            name = accountScanner.nextLine();
            currency = accountScanner.nextLine();
            saldo = Double.parseDouble(accountScanner.nextLine());

            while (accountScanner.hasNextLine()) {
                String[] rowTransaction = accountScanner.nextLine().split("\\|\\|");
                transactionHistory.add(rowTransaction.clone());
            }
            accountScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountFileName(String accountFileName) {
        this.accountFileName = accountFileName;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setTransactionHistory(ArrayList<String[]> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Account(Account a) {
        accountFileName = a.accountFileName;
        accountID = a.accountID;
        password = a.password;
        currency = a.currency;
        saldo = a.saldo;
        transactionHistory = new ArrayList<String[]>(a.transactionHistory.size());
        transactionHistory.addAll(a.transactionHistory);
    }

    public String getAccountID() {
        return accountID;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAccountFileName() { return accountFileName; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCurrency() { return currency; }

    public ArrayList<String[]> getTransactionHistory() {
        return transactionHistory;
    }

    public String[] getTransactionHistoryAt(int i) { return transactionHistory.get(i); }

    public void setAccount(Account a) {
        if (a != null) {
            accountFileName = a.accountFileName;
            accountID = a.accountID;
            password = a.password;
            currency = a.currency;
            saldo = a.saldo;
            transactionHistory = new ArrayList<String[]>(a.transactionHistory.size());
            transactionHistory.addAll(a.transactionHistory);
        } else {
            accountID = null;
            accountFileName = null;
            password = null;
            currency = null;
            saldo = 0;
            transactionHistory = null;
        }
    }

    public void changeCurrency(String newCurrency) {
        if (!currency.equalsIgnoreCase(newCurrency)) {
            String currentCurrency = currency;
            try {
                File curFile = new File("out/CurrencyExchange.dat");
                Scanner curScanner = new Scanner(curFile);
                double newCurrencyRate = 0;
                double currentCurrencyRate = 0;
                String line = "";
                while (curScanner.hasNextLine() && (newCurrencyRate == 0 || currentCurrencyRate == 0)) {
                    line = curScanner.nextLine();
                    if (line.startsWith(newCurrency + " ")) {
                        newCurrencyRate = Double.parseDouble(line.split(" ")[1]);
                    } else if (line.startsWith(currentCurrency + " ")) {
                        currentCurrencyRate = Double.parseDouble(line.split(" ")[1]);
                    }
                }
                curScanner.close();

                saldo = newCurrencyRate / currentCurrencyRate * saldo;
                currency = newCurrency;
            } catch (FileNotFoundException e) {
                System.err.println("Currency exchange database not found.");
            }
        }
    }

    public abstract void updateSaldo(double amount);

    public void addTransactionToHistory(String transactionElement){
        transactionHistory.add(transactionElement.split("\\|\\|"));
    }

    public void saveAccount() {
        File fileTransaction = new File(accountFileName);
        try {
            PrintWriter accWriter = new PrintWriter(fileTransaction);
            accWriter.println(password);
            accWriter.println(name);
            accWriter.println(currency);
            accWriter.println(saldo);
            for (String[] lineArrayTransaction : transactionHistory) {
                StringBuilder lineTransaction = new StringBuilder();
                for (String transactionElement : lineArrayTransaction) {
                    lineTransaction.append(transactionElement.concat("||"));
                }
                lineTransaction.delete(lineTransaction.length()-3,lineTransaction.length()-1);
                accWriter.println(lineTransaction.toString());
            }
            accWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Account file is lost.");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Account)) {
            return false;
        }
        Account a = (Account) other;

        return accountID.equalsIgnoreCase(a.accountID) && password.equalsIgnoreCase(a.password)
                && currency.equalsIgnoreCase(a.currency) && (saldo == a.saldo) && (transactionHistory.equals(a.transactionHistory));
    }
}