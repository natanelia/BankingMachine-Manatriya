package edu.manatriya.banking.akunbanking;

/**
 * Created by Natan Elia on 4/23/2015.
 */
public class AccountAutoSaver extends Thread {
    private Account accOld;
    private Account accCurrent;
    private int interval;

    /**
     * konstruktor kelas AccountAutoSaver
     * @param account , account yang informasinya akan disave ke dalam sebuah file
     * @param intervalInSecond , interval waktu sebuah informasi sebuah account akan di autosave ke dalam file
     */
    public AccountAutoSaver(Account account, int intervalInSecond) {

        this.interval = intervalInSecond;
        assignOldAccount(account);
        accCurrent = account;
    }

    /**
     * Mengeset keadaan awal account
     * @param account , account yang akan diautosave
     */
    private void assignOldAccount(Account account) {
        if (account instanceof DebitAccount) {
            accOld = new DebitAccount((DebitAccount)account);
        } else {
            accOld = new CreditAccount((CreditAccount)account);
        }
    }

    /**
     * I.S : accCurrent dan accOld terdefinisi
     * F.S : accCurrent akan disave apabila berbeda dengan accOld (mengalami perubahan)
     */
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
