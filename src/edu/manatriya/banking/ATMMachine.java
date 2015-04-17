package edu.manatriya.banking;

<<<<<<< HEAD
import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.AccountSignOut;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionProcessor;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

=======
>>>>>>> origin/master
/**
 * Created by KEVIN on 14/04/2015.
 */
public class ATMMachine {
<<<<<<< HEAD
    Account acc;

    public void run(){
        doCommand("AccountSignIn", inputanuser);
        while (acc != null) {
            doCommand(inputanuser);
        }
    }

    private void doCommand(String cmd) {
        (new NonTransactionProcessor(cmd, acc)).process();
    }

    private void doCommand(String cmd, String accID) {
        NonTransactionProcessor NTP = (new NonTransactionProcessor(cmd, acc).process());
        acc = NTP.getAccount();
    }
=======

>>>>>>> origin/master
}
