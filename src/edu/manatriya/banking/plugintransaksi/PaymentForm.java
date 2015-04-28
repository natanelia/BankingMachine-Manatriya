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
        /*checkBalanceButton.addMouseListener(new MouseAdapter() {
        });*/
    }

    public  synchronized void actionPerformed(ActionEvent e) {
        notify();
        setKindOfPayment(e.getActionCommand());
    }

}


/*package edu.manatriya.banking.plugintransaksi;

/**
 * Created by Alberttriadrian on 4/25/2015.
 */
//import com.sun.beans.util.Cache;
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;


public class Payment extends JFrame{
    private JTextField PaymentField;
    private JLabel PaymentLabel;
    private String KindOfPayment;

    public Payment( String title) {
        super( title );
        setSize( 500, 300 );

        // Creates a panel to hold all components
        JPanel panel = new JPanel( new BorderLayout() );
        panel.setLayout( new GridLayout(10,2) );

        PaymentLabel = new JLabel("Enter what payment do you want :");
        PaymentField = new JTextField();
        panel.add(PaymentLabel);
        panel.add(PaymentField);

        JButton submitButton = new JButton( "Submit" );
        panel.add( submitButton);
        JButton cancelButton = new JButton( "Cancel" );
        panel.add( cancelButton);

        JButton helpButton = new JButton( "Help" );
        panel.add( helpButton);

        //action listener
        WindowAdapter windowCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener( windowCloser );
        submitButton.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
                submitButton_Click();
            }
        });

        cancelButton.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
                cancelButton_Click ();
            }
        });

        helpButton.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
                helpButton_Click ();
            }
        });

        setContentPane(panel);
        setVisible( true );
    }

    //penangan event
    private synchronized void submitButton_Click(){
        KindOfPayment = PaymentField.getText();
        notify();
    }

    private void cancelButton_Click(){
        JOptionPane.showMessageDialog(null, "Cancel Click","Cancel Title", 0);
    }

    private void helpButton_Click(){
        JOptionPane.showMessageDialog(null, "Help Click","Help Title",0);
    }

    public String getKindOfPayment(){
        return KindOfPayment;
    }

}
*/