package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;
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
       JFrame frame = new JFrame("SALDO REKENING");

        DecimalFormat df = new DecimalFormat(
                "#,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        BigDecimal value = new BigDecimal(String.format("%.2f",account.getSaldo()));

        frame.setLayout(new GridLayout(3,1));
        frame.add(new JLabel(account.getAccountID()));
        frame.add(new JLabel(account.getName()));
        frame.add(new JLabel("Saldo: "+account.getCurrency() + " " + df.format(value.floatValue())));
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void execute(){
        showBalance();
    }



}
