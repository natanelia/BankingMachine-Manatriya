package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PaySPP extends Transaction{
    private String idSekolah;
    private String idSiswa;
    private int amount;

    public PaySPP(Account _acc, String _idSekolah, String _idSiswa, int _amount) {
        super(_acc);
        idSekolah = _idSekolah;
        idSiswa = _idSiswa;
        amount = _amount;
    }

    public String getIdSekolah() {return idSekolah; }
    public String getIdSiswa() {return idSiswa; }
    public int getAmount() {return amount; }
    public void setAmount(int _amount) {amount = _amount; }

    public void run() {
        acc.updateSaldo(-amount);
    }
}
