package edu.manatriya.banking.pengolahtransaksi;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.*;
import java.util.List;


public class TransactionForm extends JFrame{
	private JTextField[] allLabelsTextField;
	private JLabel[] allLabels;

	public TransactionForm( String title, List<String> formLabels) {
		super( title );
		setSize( 500, 300 );
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Creates a panel to hold all components
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setLayout( new GridLayout(10,2) );

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

		JButton helpButton = new JButton( "Help" );
		panel.add( helpButton);

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

		helpButton.addActionListener (new java.awt.event.ActionListener () {
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				helpButton_Click();
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
		this.showResult();
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

	private void showResult(){
		JFrame frame = new JFrame("Report");

		JPanel Panel = new JPanel(new GridLayout(3,1));
		JLabel[] resultLabel = new JLabel[allLabelsTextField.length];
		TitledBorder[] resultTitledBorder = new TitledBorder[allLabelsTextField.length];

		for (int i = 0 ; i < allLabelsTextField.length ; i++){
			resultLabel[i] = new JLabel(allLabelsTextField[i].getText());
			resultLabel[i].setHorizontalAlignment(JLabel.CENTER);
			Panel.add(resultLabel[i]);
			resultTitledBorder[i] = new TitledBorder(allLabels[i].getText());
			resultLabel[i].setBorder(resultTitledBorder[i]);
		}

		frame.setContentPane(Panel);
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}

}
