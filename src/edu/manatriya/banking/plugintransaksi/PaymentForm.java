package edu.manatriya.banking.plugintransaksi;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by KEVIN on 22/04/2015.
 */
public class PaymentForm extends JFrame implements ActionListener{
    private JPanel Panel;
    private JButton[] payPluginButton;
    private String kindOfPayment;

    public String getKindOfPayment() {
        return kindOfPayment;
    }

    public void setKindOfPayment(String command) {
        kindOfPayment = command;
    }

    public PaymentForm(){
        super("Payment");

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setSize(500, 500);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        Panel = new JPanel();
        setContentPane(Panel);

        File f = new File("out/production/BankingMachine-Manatriya/edu/manatriya/banking/plugintransaksi");
        String[] plugin = f.list();
        ArrayList<String> payPlugin = new ArrayList<String>();

        for (int i = 0 ; i < plugin.length; i++){
            if (plugin[i].contains("Pay") && !plugin[i].contains("Payment")){
                payPlugin.add(plugin[i]);
            }
        }

        payPluginButton = new JButton[payPlugin.size()];
        for (int i = 0 ; i < payPluginButton.length ; i++){
            payPluginButton[i] = new JButton(payPlugin.get(i).replace(".class",""));
            payPluginButton[i].addActionListener(this);
            Panel.add(payPluginButton[i]);
        }

        pack();
        setVisible(true);
    }

    public synchronized void actionPerformed(ActionEvent e) {
        notify();
        setKindOfPayment(e.getActionCommand());
    }

}