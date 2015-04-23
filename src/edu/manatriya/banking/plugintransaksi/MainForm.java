package edu.manatriya.banking.plugintransaksi;
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
    private final JLabel label;
    private JTextField textbox;
    private JButton submitButton;
    private JPanel pane;

    private boolean submitted = false;
    private boolean accepted = true;


    public MainForm() {
        super("MainForm");
        setSize(100, 100);
        pane = new JPanel(new BorderLayout());
        pane.setLayout(new GridLayout(1, 1));
        label = new JLabel(labels[0]);
        pane.add(label);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        textbox = new JTextField(20);

        label.setLabelFor(textbox);
        pane.add(textbox);
        setContentPane(pane);

        submitButton = new JButton("SignIn");
        pane.add(submitButton);
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

