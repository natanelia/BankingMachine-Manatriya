package edu.manatriya.banking.pengolahtransaksi;

/**
 * Created by Natan Elia on 4/23/2015.
 */
public class TransactionsAutoStarter extends Thread {
    private TransactionProcessor transactionProcessor;
    private int interval;

    public TransactionsAutoStarter(TransactionProcessor transactionProcessor, int intervalInSecond) {
        this.transactionProcessor = transactionProcessor;
    }

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
