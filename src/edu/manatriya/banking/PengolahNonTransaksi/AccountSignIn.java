package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AcountSignIn implements Command {
    /**
     * adalah salah satu kelas NonTransaksi
     *
     */


    /**
     * atribut-atribut
     */
    private Account acc;


    public AccountSignIn() {
        acc = new Account();
    }

    public AccountSignIn(Account _acc) {
        acc = _acc;
    }


    /**
     * Getter and setter
     */
    public Account getAcc(){
        return acc;
    }

    public void setAcc(Account A2){
        acc = A2;
    }



    /**
     * Method-method
     */
    /**
     * Mengecek apakah sudah melakukan login account
     * jika acc bernilai Null, artinya belum dilakukan login, dan harus melakukan login
     */

    private void checkAccValidity(){
        boolean found = false;
        Scanner inputreader = new Scanner(System.in);
        String input = inputreader.nextLine();
        Scanner filescan = null;
        filescan = new Scanner(new File("out/Accounts/account.txt"));

        System.out.println("Masukkan Account ID anda:");
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



    private void checkLoginStatus    {
        while (acc == null) {
            checkAccValidity();
            // Acount sudah berisis (login telah berhasil)
        }
    }

        @Override
        public void Execute () {
            checkLoginStatus();
    }


}
