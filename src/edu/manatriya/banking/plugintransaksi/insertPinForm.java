package edu.manatriya.banking.plugintransaksi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by KEVIN on 22/04/2015.
 */
public class insertPinForm extends JFrame{
    private JPasswordField passwordField;
    private JLabel enterpin;
    private JButton okButton;
    private JPanel panel1;
    private String pin;
    private char[] correctPin;

    public char[] getCorrectPin() {
        return correctPin;
    }

    public void setCorrectPin(char[] correctPassword) {
        this.correctPin = correctPassword;
    }

    public void setPin(String _pin){
        pin = _pin;
    }

    public String getPin(){
        return pin;
    }

    public insertPinForm(){
        super("Insert Your Pin");
        panel1 = new JPanel();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        panel1.add(passwordField);
        enterpin = new JLabel("enter your pin number");
        enterpin.setLabelFor(passwordField);
        panel1.add(okButton);
        okButton.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {
                char[] input = passwordField.getPassword();
                if (isPasswordCorrect(input)){
                    JOptionPane.showMessageDialog(null,"Correct pin" );
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid pin","Error Message", JOptionPane.ERROR_MESSAGE);
                }
                Arrays.fill(input, '0');
                passwordField.selectAll();
            }
        });

        pack();
        setVisible(true);

    }


    private boolean isPasswordCorrect(char[] input){
        boolean isCorrect = true;
        if (input.length != getCorrectPin().length){
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, getCorrectPin());
        }
        return isCorrect;
    }
}
