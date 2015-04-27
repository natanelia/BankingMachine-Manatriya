package edu.manatriya.banking.pengolahtransaksi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;


public class TransactionForm extends JFrame{
	private JTextField[] allLabelsTextField;

	public TransactionForm( String title, List<String> formLabels) {
		super( title );
		setSize( 500, 300 );

		// Creates a panel to hold all components
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setLayout( new GridLayout(10,2) );

		JLabel[] allLabels = new JLabel[formLabels.size() - 1];
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
				submitButton_Click ();
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

	public String[] getUserInputs() {
		String[] userInputs = new String[allLabelsTextField.length];
		int i = 0;
		for (JTextField field : allLabelsTextField) {
			userInputs[i++] = field.getText();
		}
		return userInputs;
	}


	//penangan event
	private synchronized void submitButton_Click(){
		notify();
	};

	private synchronized void cancelButton_Click(){
		for (JTextField field : allLabelsTextField) {
			field.setText("");
		}
		notify();
		this.dispose();
	}

	private void helpButton_Click(){
		JOptionPane.showMessageDialog(null, "Help Click","Help Title",0);
	}



}
