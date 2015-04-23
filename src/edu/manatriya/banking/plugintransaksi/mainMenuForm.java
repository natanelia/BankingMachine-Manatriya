package edu.manatriya.banking.plugintransaksi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * Created by KEVIN on 22/04/2015.
 */
public class mainMenuForm extends JFrame implements ActionListener{
    private JButton checkBalanceButton;
    private JButton paymentButton;
    private JButton transferButton;
    private JButton openSavingAccountButton;
    private JButton showTransactionHistoryButton;
    private JButton signOutButton;
    private JButton convertAccountCurrencyButton;
    private JPanel Panel;
    private JButton createNewAccountButton;
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public mainMenuForm(){
        super("Main Menu Form");
        setSize(500, 500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
//        Panel.setLayout(new GridLayout(4,2));
//        Panel.add(checkBalanceButton);
//        Panel.add(paymentButton);
//        Panel.add(transferButton);
//        Panel.add(openSavingAccountButton);
//        Panel.add(showTransactionHistoryButton);
//        Panel.add(signOutButton);
//        Panel.add(convertAccountCurrencyButton);
        setContentPane(Panel);
        checkBalanceButton.addActionListener(this);
        paymentButton.addActionListener(this);
        transferButton.addActionListener(this);
        openSavingAccountButton.addActionListener(this);
        showTransactionHistoryButton.addActionListener(this);
        signOutButton.addActionListener(this);
        convertAccountCurrencyButton.addActionListener(this);
        pack();
        setVisible(true);
        checkBalanceButton.addMouseListener(new MouseAdapter() {
        });
    }

    public  synchronized void actionPerformed(ActionEvent e) {
        notify();
        if(e.getSource() == checkBalanceButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() ==  paymentButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() ==  transferButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() == openSavingAccountButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() == showTransactionHistoryButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() == signOutButton){
            setCommand(e.getActionCommand());
        } else
        if(e.getSource() == convertAccountCurrencyButton){
            setCommand(e.getActionCommand());
        }
    }

}
