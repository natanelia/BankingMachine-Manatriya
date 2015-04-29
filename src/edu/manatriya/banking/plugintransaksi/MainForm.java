package edu.manatriya.banking.plugintransaksi;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Created by KEVIN on 21/04/2015.
 */

public class MainForm extends JFrame {
    private String accountID=null;
    private String[] labels = {"AccountID"};
    private int numLabel = labels.length;
    private JLabel label;
    private char[] password;
    private JTextField textBox;
    private JButton submitButton;
    private JPanel pane;

    private boolean submitted = false;
    private boolean accepted = true;
    private JPasswordField passwordField;
    private JLabel passwordLabel;

    public MainForm() {
        super("Account Authentication");

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setContentPane(pane);
        this.setSize(500, this.getHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener (new java.awt.event.ActionListener() {
                                         public void actionPerformed(java.awt.event.ActionEvent evt) {
                                             submitButton_Click();
                                         }
                                     });
        pack();
        setVisible(true);
    }

    private synchronized void submitButton_Click(){
        setAccountID(textBox.getText());
        setPassword(passwordField.getPassword());
        notify();
        setSubmitted(true);
    }

    public void setAccepted(boolean acc){
        this.accepted =  acc;
        if (accepted)
            JOptionPane.showMessageDialog(null, "Login success!");
        else
            JOptionPane.showMessageDialog(null, "Invalid account ID or password!", "", JOptionPane.ERROR_MESSAGE);
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountID(){
        return accountID;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean getSubmitted() {
        return submitted;
    }
}

