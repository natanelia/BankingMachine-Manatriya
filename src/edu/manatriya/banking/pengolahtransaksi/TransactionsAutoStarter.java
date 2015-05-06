package edu.manatriya.banking.pengolahtransaksi;

/**
 * Kelas TransactionAutoStarter akan secara otomatis menjalankan semua transaksi setelah selang waktu tertentu
 */
public class TransactionsAutoStarter extends Thread {
    private TransactionProcessor transactionProcessor;
    private int interval;

    /**
     * Constructor Transaction Auto Starter
     * @param transactionProcessor TransactionProcesor
     * @param intervalInSecond interval waktu untuk memulai transaksi secara otomatis
     */
    public TransactionsAutoStarter(TransactionProcessor transactionProcessor, int intervalInSecond) {
        this.transactionProcessor = transactionProcessor;
    }

    /**
     * Menjalan semua transaksi secara otomatis setelah selang waktu tertentu
     */
    @Override
    public synchronized void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(interval * 1000);
                transactionProcessor.startAll();
            }
        } catch (Exception e) {
            /* do nothing */
        }
    }
}
