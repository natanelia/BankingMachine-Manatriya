package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PaySPP extends Transaction {
    private String idSekolah;
    private String idSiswa;

    public PaySPP(Account _acc, String _idSekolah, String _idSiswa, int _amount) {
        super(_acc, _amount);
        idSekolah = _idSekolah;
        idSiswa = _idSiswa;
        transactionName = this.getName();
    }

    public String getIdSekolah() {
        return idSekolah;
    }

    public String getIdSiswa() {
        return idSiswa;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
    }
}
