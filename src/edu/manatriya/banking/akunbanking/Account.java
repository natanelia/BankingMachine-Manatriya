/**
 * Class Account
 * Sebuah kelas abstrak yang merepresentasikan sebuah account dalam mobile banking
 * menyimpan informasi mengenai account berupa accountID, passwrod, nem, currency, saldo
 * transactionHistory serta accountFileName
 */
package edu.manatriya.banking.akunbanking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    /**
     * Konstruktor dari kelas Account
     * @param accountID account
     * @param password
     */
    public Account(String accountID, String password) {
        this.accountID = accountID;
        this.accountFileName = "";
        this.password = password;
        this.name = "";
        this.currency = "";
        this.saldo = 0;
        transactionHistory = new ArrayList<String[]>();
    }

    /**
     * Konstruktor dengan parameter dari kelas account
     * @param _accountFileName merupakan nama dari file yang berisi informasi dari account yang bersangkutan
     * @throws FileNotFoundException
     */
    public Account(String _accountFileName) throws FileNotFoundException {
        transactionHistory = new ArrayList<String[]>();
        accountFileName = _accountFileName;
        File fileTransaction = new File(accountFileName);
        accountID = fileTransaction.getName().replace(".acc","");

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
    }

    /**
     * setter AccountID
     * @param _accountID accountID yang akan diset ke  sebuah account
     */
    public void setAccountID(String _accountID) {
        this.accountID = _accountID;
    }

    /**
     * Mengeset name dengan _name
     * @param _name
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Mengeset accountfilename dengan _accountFileName
     * @param _accountFileName
     */
    public void setAccountFileName(String _accountFileName) {
        this.accountFileName = _accountFileName;
    }
    /**
     * Mengeset currency dengan _currency
     * @param _currency
     */
    public void setCurrency(String _currency) {
        this.currency = _currency;
    }

    /**
     * Mengeset transactionhistroy dengan _transactionHistory
     * @param _transactionHistory
     */
    public void setTransactionHistory(ArrayList<String[]> _transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    /**
     * Membuat sebuah objek baru dengan account yang sebelumnya sudah ada
     * @param a sebuah account yang sudah terbentuk sebelumnya
     */
    public Account(Account a) {
        accountFileName = a.accountFileName;
        accountID = a.accountID;
        password = a.password;
        currency = a.currency;
        saldo = a.saldo;
        transactionHistory = new ArrayList<String[]>(a.transactionHistory.size());
        transactionHistory.addAll(a.transactionHistory);
    }

    /**
     * mengembalikan accountID dari account
     * @return accountID
     */
    public String getAccountID() {
        return accountID;
    }
    /**
     *
     * @return mengembalikan jumlah saldo dari account
     */
    public double getSaldo() {
        return saldo;
    }
    /**
     * Mengeset saldo dengan _saldo
     * @param _saldo
     */
    public void setSaldo(double _saldo) {
        this.saldo = _saldo;
    }
    /**
     *
     * @return accountFileName, nama dari file yang berisi informasi mengenai account
     */
    public String getAccountFileName() { return accountFileName; }

    /**
     *
     * @return name, nama dari pemilik account
     */
    public String getName() { return name; }

    /**
     *
     * @return password, password untuk mendapatkan password dari sebuah account
     */
    public String getPassword() { return password; }

    /**
     * mengeset password dari sebuah account dengan _password
     * @param _password , password baru yang akan diset pada sebuah account
     */
    public void setPassword(String _password) { this.password = _password; }
    /**
     *
     * @return currency, satuan mata uang yang dimiliki oleh account
     */
    public String getCurrency() { return currency; }

    /**
     *
     * @return transactionHistory, riwayat transaksi dari sebuah account
     */
    public ArrayList<String[]> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     *
     * @param i , indeks yang menunjukkan riwayat transaksi ke berapa yang ingin diambil
     * @return , riwayat transaksi ke-i
     */
    public String[] getTransactionHistoryAt(int i) { return transactionHistory.get(i); }

    /**
     * Mengcopy atribut dari a ke objek
     * @param a , sebuah account yang akan dicopy nilai atributnya
     */
    public void setAccount(Account a) {
        if (a != null) {
            accountFileName = a.accountFileName;
            accountID = a.accountID;
            name = a.name;
            password = a.password;
            currency = a.currency;
            saldo = a.saldo;
            transactionHistory = new ArrayList<String[]>(a.transactionHistory.size());
            transactionHistory.addAll(a.transactionHistory);
        } else {
            accountID = null;
            name = null;
            accountFileName = null;
            password = null;
            currency = null;
            saldo = 0;
            transactionHistory = null;
        }
    }


    /**
     * Mengubah curreny menjadi newcurrency apabila newcurrency terdefinisi
     * @param newCurrency
     */
    public void changeCurrency(String newCurrency) throws FileNotFoundException, Exception {
        if (!currency.equalsIgnoreCase(newCurrency)) {
            String currentCurrency = currency;
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

                if (newCurrencyRate != 0 && currentCurrencyRate != 0) {
                    saldo = newCurrencyRate / currentCurrencyRate * saldo;
                    currency = newCurrency;
                } else {
                    throw new Exception("Currency is not yet supported in our system: " + newCurrency);
                }
        }
    }

    /**
     * Mengupdate saldo, merupakan abstract
     * @param amount
     */
    public abstract void updateSaldo(double amount);

    /**
     * Menambah transactionhistroy dengan transactionElement
     * @param transactionElement , transactionElement akan ditambahkan ke dalam transactionhistory
     */
    public void addTransactionToHistory(String transactionElement){
        transactionHistory.add(transactionElement.split("\\|\\|"));
    }

    /**
     * I.S : Account terdefinisi , memiliki password, name , currency dan saldo
     * F.S : Informasi mengenai account disimpan dalam <accountfilename>.acc
     *      jika sebelumnya sudah ada maka file akan dioverride isinya
     */
    public void saveAccount() {
        File fileTransaction = new File(accountFileName);
        if (!fileTransaction.exists()) {
            try {
                fileTransaction.createNewFile();
            } catch (IOException e) {
                System.err.println("Account file can't be created.");
            }
        }
        try {
            PrintWriter accWriter = new PrintWriter(fileTransaction);
            accWriter.println(password);
            accWriter.println(name);
            accWriter.println(currency);
            accWriter.println(String.format("%.2f",saldo));
            for (String[] lineArrayTransaction : transactionHistory) {
                StringBuilder lineTransaction = new StringBuilder();
                for (String transactionElement : lineArrayTransaction) {
                    lineTransaction.append(transactionElement.concat("||"));
                }
                lineTransaction.delete(lineTransaction.length()-2,lineTransaction.length());
                accWriter.println(lineTransaction.toString());
            }
            accWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Account file is lost.");
        }
    }

    /**
     * Membandingkan account dengan other apakah objek yang sama atau tidak
     * @param other , objek yang akan dibandingkan dengan this
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Account)) {
            return false;
        }
        Account a = (Account) other;

        boolean equality = accountID.equals(a.accountID) && name.equals(a.name) && password.equals(a.password)
                && currency.equals(a.currency) && (saldo == a.saldo);

        for (int i=0;i<transactionHistory.size() && equality;i++) {
            for (int j=0;j<transactionHistory.get(i).length && equality;j++) {
                equality &= (transactionHistory.get(i)[j].equals(a.transactionHistory.get(i)[j]));
            }
        }
        return equality;
    }
}