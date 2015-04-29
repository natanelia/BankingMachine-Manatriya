package edu.manatriya.banking.pengolahtransaksi;

import org.w3c.dom.ranges.RangeException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.util.List;


public class TransactionForm extends JFrame{
	private JTextField[] allLabelsTextField;
	private JLabel[] allLabels;

	/**
	 * menampilkan form untuk pengisian data - data yang dibutuhkan untuk suatu transaksi
	 * @param title jenis transaksi sekaligus judul dari form
	 * @param formLabels parameter-parameter dari transaksi
	 */
	public TransactionForm( String title, List<String> formLabels) {
		super( title );
		setSize( 500, (formLabels.size()+1)*60 );
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Creates a panel to hold all components
		JPanel panel = new JPanel( new BorderLayout() );
		GridLayout gl = new GridLayout(formLabels.size()*2,1);
		panel.setLayout( gl);
		panel.setBorder(new EmptyBorder(5,10,10,10));

		allLabels = new JLabel[formLabels.size() - 1];
		allLabelsTextField = new JTextField[formLabels.size() - 1];
		for (int i = 0 ; i < formLabels.size() - 1 ; i++){
			allLabels[i] = new JLabel(formLabels.get(i + 1));
			panel.add(allLabels[i]);
			allLabelsTextField[i] = new JTextField();
			panel.add(allLabelsTextField[i]);
		}

		JButton submitButton = new JButton( "Submit" );
		panel.add( submitButton);
		JButton cancelButton = new JButton( "Cancel" );
		panel.add( cancelButton);

		WindowAdapter windowCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
				e.getWindow().setVisible(false);
				cancelButton_Click();
			}
		};

		addWindowListener( windowCloser );

		submitButton.addActionListener (new java.awt.event.ActionListener () {
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				submitButton_Click ();
			}
		});

		cancelButton.addActionListener (new java.awt.event.ActionListener () {
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				cancelButton_Click();
			}
		});

		setContentPane(panel);
		setVisible( true );
	}

	/**
	 * Mengembalikan array of String yang berisi nilai nilai yang telah diinputkan user pada form
	 * @return String[] nilai dari inputan user
	 */
	public String[] getUserInputs() {
		String[] userInputs = new String[allLabelsTextField.length];
		int i = 0;
		for (JTextField field : allLabelsTextField) {
			userInputs[i++] = field.getText();
		}
		return userInputs;
	}


	/**
	 * Menjalankan event saat tombol Submit di click
	 */
	private synchronized void submitButton_Click(){
		notify();
	};

	/**
	 * Menjalankan event saat tombol Submit di click
	 */
	private synchronized void cancelButton_Click(){
		for (JTextField field : allLabelsTextField) {
			field.setText("");
		}
		notify();
		this.dispose();
	}
}
