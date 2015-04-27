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
 * Created by Marco Orlando on 4/11/2015.
 */
public class CheckBalance implements Command {
    private Account account;

    public CheckBalance(Account _account){
        account= _account;
    }

    /**
     * Menampilkan saldo dari account
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


    @Override
    public void execute(){
        showBalance();
    }



}
