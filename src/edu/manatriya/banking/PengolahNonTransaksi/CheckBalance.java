package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;
import java.text.NumberFormat;
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
        Locale IDR = new Locale("Indonesia");
        NumberFormat balanceFormat = NumberFormat.getCurrencyInstance(IDR);

        String Balance = new String(balanceFormat.format(account.getSaldo()));
        JFrame frame = new JFrame();
        frame.add(new JLabel("Saldo anda: "));
        frame.add(new JLabel(Balance));
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void execute(){
        showBalance();
    }



}
