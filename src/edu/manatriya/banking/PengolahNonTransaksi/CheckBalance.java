package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Adalah kelas yang menangani aktivitas non transaksi pengecekan saldo rekening.
 */
public class CheckBalance implements Command {
    private Account account;


    /**
     * Adalah constructor berparameter kelas CheckBalance
     * @param _account account akan digunakan untuk set atribut account pada kelas ini
     */
    public CheckBalance(Account _account){
        account= _account;
    }

    /**
     * Menampilkan saldo dari account dalam bentuk form
     */
    private void showBalance(){
       JFrame frame = new JFrame("Saldo Rekening");

        DecimalFormat df = new DecimalFormat(
                "#,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        BigDecimal value = new BigDecimal(String.format("%.2f",account.getSaldo()));

        JPanel a = new JPanel(new GridLayout(3,1));
        JLabel accountID =new JLabel(account.getAccountID());
        accountID.setHorizontalAlignment(JLabel.CENTER);
        a.add(accountID);

        JLabel accountName = new JLabel(account.getName());
        accountName.setHorizontalAlignment(JLabel.CENTER);
        a.add(accountName);

        JLabel accountSaldo = new JLabel(account.getCurrency() + " " + df.format(value.floatValue()));
        accountSaldo.setHorizontalAlignment(JLabel.CENTER);
        a.add(accountSaldo);

        TitledBorder accID = new TitledBorder("Account ID");
        TitledBorder accName = new TitledBorder("Account Name");
        TitledBorder accSaldo = new TitledBorder("Account Balance");
        accountID.setBorder(accID);
        accountName.setBorder(accName);
        accountSaldo.setBorder(accSaldo);

        frame.setContentPane(a);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }


    /**
     * melakukan eksekusi void showBalance
     */
    @Override
    public void execute(){
        showBalance();
    }



}
