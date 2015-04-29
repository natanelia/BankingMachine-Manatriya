package edu.manatriya.banking.pengolahtransaksi;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Alberttriadrian on 4/29/2015.
 */
public class ResultForm extends  JFrame{

    /**
     * menampilkan report dari hasil transaksi yang dilakukan
     * @param arrParam
     */
   public ResultForm(String[] arrParam){
       JFrame frame = new JFrame("Report");

       JPanel Panel = new JPanel(new GridLayout(3,1));
       JLabel[] resultLabel = new JLabel[arrParam.length];
       TitledBorder[] resultTitledBorder = new TitledBorder[arrParam.length];

       for (int i = 0 ; i < arrParam.length ; i++){
           resultLabel[i] = new JLabel(arrParam[i]);
           resultLabel[i].setHorizontalAlignment(JLabel.CENTER);
           Panel.add(resultLabel[i]);
           resultTitledBorder[i] = new TitledBorder(arrParam[i]);
           resultLabel[i].setBorder(resultTitledBorder[i]);
       }

       frame.setContentPane(Panel);
       frame.setSize(300, 150);
       frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       frame.setVisible(true);
   }
}
