package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class ShowTransactionHistory implements Command {
    private Account acc;

    public ShowTransactionHistory(Account _account){
        acc = _account;
    }

    /**
     * Mengenerate form yang menampilkan histori transaksi
     */
    private void generateTransactionHistoryTable(){
        JFrame frame = new JFrame(acc.getAccountID());
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //set column name
        String columnData[] = acc.getTransactionHistoryAt(0);
        int numColumn = columnData.length;
        String columnNames[] = new String[numColumn];
        for (int i = 0; i<columnData.length ; i++){
            columnNames[i] =columnData[i];
        }

        //set rowData
        int numRow = acc.getTransactionHistory().size() -1;
        String rowData[][]= new String[numRow][numColumn];
        for (int i = 1 ; i<acc.getTransactionHistory().size();i++){
            int k = i-1;
            String[] transaction = acc.getTransactionHistoryAt(i);
            for (int j = 0; j < transaction.length;j++){
                rowData[k][j] = transaction[j];
            }
        }

        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 300);
        frame.setVisible(true);
    }


    @Override
    public void execute(){
        generateTransactionHistoryTable();
    }



}
