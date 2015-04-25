package edu.manatriya.banking.plugintransaksi;

/**
 * Created by Alberttriadrian on 4/25/2015.
 */
import com.sun.beans.util.Cache;

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
