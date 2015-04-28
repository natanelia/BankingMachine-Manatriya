package edu.manatriya.banking.akunbanking;

/**
 * Created by Natan Elia on 4/23/2015.
 */
public class AccountAutoSaver extends Thread {
    private Account accOld;
    private Account accCurrent;
    private int interval;

    public AccountAutoSaver(Account account, int intervalInSecond) {
        this.interval = intervalInSecond;
        assignOldAccount(account);
        accCurrent = account;
    }

    private void assignOldAccount(Account account) {
        if (account instanceof DebitAccount) {
            accOld = new DebitAccount((DebitAccount)account);
        } else {
            accOld = new CreditAccount((CreditAccount)account);
        }
    }

    @Override
    public synchronized void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(interval * 1000);
                if (!accCurrent.equals(accOld)) {
                    accCurrent.saveAccount();
                    assignOldAccount(accCurrent);
                }
            }
        } catch (InterruptedException e) {
            /* do nothing */
        } catch (NullPointerException e) {
            /* do nothing */
        }
    }
}
