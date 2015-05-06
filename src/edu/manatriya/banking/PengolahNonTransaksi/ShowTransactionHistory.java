package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *  Adalah kelas yang menangani aktivitas nontransaksi account untuk menampilkan history/riwayat transaksi dari account tersebut. Hasil akan ditampilkan dalam bentuk tabel.
 */
public class ShowTransactionHistory implements Command {
    private Account acc;


    /**
     * adalah constructor berparameter dari kelas ShowTransactionHistory
     * @param _account adalah parameter yang akan digunakan untuk mengeset atribut account
     */
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
        Object rowData[][]= new Object[numRow][numColumn];
        for (int i = 1 ; i<acc.getTransactionHistory().size();i++){
            int k = i-1;
            String[] transaction = acc.getTransactionHistoryAt(i);
            for (int j = 0; j < transaction.length;j++){
                String transaksi  = transaction[j].replaceAll("\\|","\n");
                rowData[k][j] = transaksi;
            }
        }
        DefaultTableModel dm = new DefaultTableModel() {
            public Class getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        dm.setDataVector(rowData, columnNames);
        JTable table = new JTable(dm);
        table.getColumn(columnNames[2]).setCellRenderer(new MultiLineCellRenderer());
        table.getColumn(columnNames[2]).setMinWidth(200);
        table.setRowHeight(table.getRowHeight() * 2 + 5);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 300);
        frame.setVisible(true);
    }

    /**
     * mengeksekusi prosedur generateTransactionHistoryTable
     */
    @Override
    public void execute(){
        generateTransactionHistoryTable();
    }
}

class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
    /**
     * prosedur yang digunakan untuk memungkinkan table memiliki lebih dari 1 row
     */
    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }


    /**
     * Fungsi yang digunakkan untuk mengembalikan component-componet pada tablecellrenderer
     * @param table bertipe JTable
     * @param value  value adalah objek yang dicek
     * @param isSelected boolean untuk mengetahui status dari objek, apakah sedang diolah atau tidak
     * @param hasFocus boolean untuk menentukan apakah object tersebut memiliki fokus
     * @param row indeks row
     * @param column indeks column
     * @return
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setFont(table.getFont());
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            if (table.isCellEditable(row, column)) {
                setForeground(UIManager.getColor("Table.focusCellForeground"));
                setBackground(UIManager.getColor("Table.focusCellBackground"));
            }
        } else {
            setBorder(new EmptyBorder(1, 2, 1, 2));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
