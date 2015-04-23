//Transaction Form.java

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;


public class TransactionForm extends JDialog{
	public TransactionForm( JFrame frame , String title, ArrayList<String> formLabels) {
		super( frame, true );
		setTitle( title );
		setSize( 500, 300 );
		
		// Creates a panel to hold all components
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setLayout( new GridLayout(10,2) );
		
		JLabel[] allLabels = new JLabel[formLabels.size() - 1];
		JTextField[] allLabelsTextField = new JTextField[formLabels.size() - 1];
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
 
	//penangan event
	private void submitButton_Click(){
		/*String st;
		st="Name: "+txtName.getText();
		st+="\nPhone: "+txtPhone.getText();
		if (rdoMale.isSelected()) {
			st+="\nSex: "+rdoMale.getText();
		}
		else {
			st+="\nSex: "+rdoFemale.getText();
		}
		st+="\nCategory: "+cmbCategory.getSelectedItem();
		st+="\nHobby: ";
		if (chkSport.isSelected()) st+= chkSport.getText()+" ";
		if (chkReading.isSelected()) st+= chkReading.getText()+" ";
		if (chkShopping.isSelected()) st+= chkShopping.getText()+"\n";

		JOptionPane.showMessageDialog(null, st,"Data", 0);
		JOptionPane.showMessageDialog(null, "Submit Click","Submit Title", 0);*/
	};
 
	private void cancelButton_Click(){
		JOptionPane.showMessageDialog(null, "Cancel Click","Cancel Title", 0);
	};
 
	private void helpButton_Click(){
		JOptionPane.showMessageDialog(null, "Help Click","Help Title",0);
	};
 
	/*public static void main( String[] args ) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("Amount"); 	a.add("ElecID");	a.add("Acount");
		new TransactionForm( new JFrame(),"Pay Electricity", a ).show();
	}*/
} 
