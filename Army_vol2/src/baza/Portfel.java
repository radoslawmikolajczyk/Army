package baza;

public class Portfel {

    private long kasa;

    public Portfel(long kasa) {
        this.kasa = kasa;
    }

    public long getKasa() {
        return kasa;
    }

    void dodajKase(int ile_kasy){
        kasa += ile_kasy;
    }

    void odejmijKase(int ile_kasy){
        kasa -= ile_kasy;
    }
}
