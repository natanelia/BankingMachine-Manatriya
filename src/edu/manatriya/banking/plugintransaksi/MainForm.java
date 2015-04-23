package edu.manatriya.banking.plugintransaksi;
import com.intellij.uiDesigner.core.GridLayoutManager;
import edu.manatriya.banking.ATMMachine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by KEVIN on 21/04/2015.
 */

public class MainForm extends JFrame {
    private String accountID=null;
    private String[] labels = {"AccountID"};
    private int numLabel = labels.length;
    private JLabel label;
    private char[] password;
    private JTextField textbox;
    private JButton submitButton;
    private JPanel pane;

    private boolean submitted = false;
    private boolean accepted = true;
    private JPasswordField passwordField;
    private JLabel passwordlabel;


    public MainForm() {
        super("MainForm");

        setContentPane(pane);
        this.setSize(500,this.getHeight());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


  /*      pane.setLayout(new GridLayout(3, 1, 10, 10));


        label.setLabelFor(textbox);
        setContentPane(pane);

        passwordlabel.setLabelFor(passwordField);

        pane.add(textbox);
        pane.add(label);
        pane.add(passwordField);
        pane.add(passwordlabel);
        pane.add(submitButton);*/
        submitButton.addActionListener (new java.awt.event.ActionListener() {
                                         public void actionPerformed(java.awt.event.ActionEvent evt) {
                                             submitButton_Click();
                                         }
                                     }
        );
        pack();
        setVisible(true);
    }

    private synchronized void submitButton_Click(){
        setAccountID(textbox.getText());
        setPassword(passwordField.getPassword());
        notify();
        setSubmitted(true);
    }

    public void setAccepted(boolean acc){
        this.accepted =  acc;
        if (accepted)
            JOptionPane.showMessageDialog(null, "Login berhasil");
        else
            JOptionPane.showMessageDialog(null, "Username not valid");
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

