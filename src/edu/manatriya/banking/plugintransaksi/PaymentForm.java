package edu.manatriya.banking.plugintransaksi;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by KEVIN on 22/04/2015.
 */
public class PaymentForm extends JFrame implements ActionListener{
    private JPanel Panel;
    private JButton[] payPluginButton;
    private String kindOfPayment;

    /**
     * Getter untuk tipe pembayaran
     * @return Tipe pembayaran
     */
    public String getKindOfPayment() {
        return kindOfPayment;
    }

    /**
     * Setter untuk tipe pembayaran
     * @param command Tipe pembayaran sesuai nama kelas di plugin transaksi
     */
    public void setKindOfPayment(String command) {
        kindOfPayment = command;
    }

    /**
     * Konstruktor PaymentForm. Membentuk dan menampilkan form untuk memilih tipe pembayaran
     */
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

        WindowAdapter windowCloser = new WindowAdapter() {
            public synchronized void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                closeForm();
            }
        };
        addWindowListener( windowCloser );


        File f = new File("out/production/BankingMachine-Manatriya/edu/manatriya/banking/plugintransaksi");
        String[] plugin = f.list();
        ArrayList<String> payPlugin = new ArrayList<String>();

        for (int i = 0 ; i < plugin.length; i++){
            if (plugin[i].startsWith("Pay") && !plugin[i].contains("Payment")){
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

    /**
     * Mengubah tipe pembayaran dengan tipe yang dipilih pengguna.
     * @param e ActionEvent yang mentrigger
     */
    public synchronized void actionPerformed(ActionEvent e) {
        setKindOfPayment(e.getActionCommand());
        notify();
    }

    private synchronized void closeForm() {
        setKindOfPayment("");
        this.notify();
        this.dispose();
    }
}