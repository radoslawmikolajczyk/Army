package baza;

class Portfel {

    private long kasa;

    Portfel(long kasa) {
        this.kasa = kasa;
    }

    long getKasa() {
        return kasa;
    }

    void dodajKase(int ile_kasy){
        kasa += ile_kasy;
    }

    void odejmijKase(int ile_kasy){
        kasa -= ile_kasy;
    }
}
