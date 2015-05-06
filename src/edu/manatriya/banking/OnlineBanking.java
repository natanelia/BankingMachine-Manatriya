/**
 * Kelas utama untk menjalank
 */
package edu.manatriya.banking;

import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.Command;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionFactory;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountAutoSaver;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;
import edu.manatriya.banking.pengolahtransaksi.TransactionsAutoStarter;

/**
 * Kelas controller
 */

public class OnlineBanking {
    Account acc;
    TransactionProcessor transactionProcessor;

    /**
     * Konstruktor kelas ATMMachine
     */
    public OnlineBanking() {
        acc = null;
    }

    /**
     * Menciptakan MainForm untuk login, apabila username dan password yang diberikan tidak valid, akan diminta kembali username dan password
     * AccountAutoSaver diaktifkan untuk menyimpan perubahan informasi terhadap account dalam file eksternal secara otomatis
     * TransactionAutoStarter diaktifkan untuk dapat mengeksekusi transaksi secara berkala, apabila transaction queue memiliki isi
     * Apabila login berhasil, maka akan dibuat mainmenuform untuk mendapatkan apa yang ingin dilakukan user
     * Jika user mengklik salah satu menu yang disediakan maka docommand akan dipanggil dan mengeksekusi perintah yang diberikan pengguna
     * Mainmenuform akan terus terbuka sampai user memutuskan untuk signout
     */
    public void run() {
        while (true) {
            MainForm mainform = new MainForm();

            do {
                synchronized (mainform) {
                    try {
                        mainform.wait();
                    } catch (InterruptedException e) {
                    /* do nothing */
                    }
                    mainform.setSubmitted(false);
                    String accountID = mainform.getAccountID();
                    String accountPassword = new String(mainform.getPassword());
                    acc = new DebitAccount(accountID, accountPassword);
                    doCommand("AccountSignIn");
                    if (acc == null) {
                        mainform.setAccepted(false);
                    }
                }
            } while (acc == null);
            AccountAutoSaver accAutoSaver = new AccountAutoSaver(acc, 5);
            accAutoSaver.start();
            mainform.setAccepted(true);
            mainform.dispose();

            mainMenuForm mainmenuform = new mainMenuForm();
            transactionProcessor = new TransactionProcessor(acc);
            TransactionsAutoStarter transactionsAutoStarter = new TransactionsAutoStarter(transactionProcessor, 1);
            transactionsAutoStarter.start();
            while (acc.getAccountID() != null) {
                synchronized (mainmenuform) {
                    try {
                        mainmenuform.wait();
                    } catch (InterruptedException e) {
                    /* do nothing */
                    }
                    String command = mainmenuform.getCommand().replace(" ", "");
                    doCommand(command);
                }
            }
            try {
                transactionProcessor.startAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mainmenuform.dispose();
            transactionsAutoStarter.interrupt();
            accAutoSaver.interrupt();
        }
    }

    /**
     * Mencoba mengeksekusi perintah "cmd" sebagai perintah non-transaksi. Bila "cmd" bukan perintah non-transaksi, akan
     * dijalankan sebagai perintah transaksi. Bila bukan keduanya, mengeluarkan pesan error bahwa perintah tidak terdaftar.
     * @param cmd , sebuah string yang akan digunakan untuk menentukan perintah apa yang akan dieksekusi
     */
    private void doCommand(String cmd) {
        try {
            NonTransactionFactory nonTransactionFactory = new NonTransactionFactory();
            Command command = nonTransactionFactory.getCommand(cmd, acc);
            command.execute();
            if (cmd.equalsIgnoreCase("AccountSignIn"))
                acc = ((AccountSignIn)command).getAccount();
        } catch (Exception e) {
            try {
                transactionProcessor.generateForm(cmd);
                transactionProcessor.startAll();
            } catch (Exception ex) {
                System.err.println("Command not found!");
            }
        }
    }
}
