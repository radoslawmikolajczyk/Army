package baza;

abstract class Jednostka {      //Klasa abstrakcyjna z której dziedziczą dowodcy, oddziały oraz szeregowi
    private String nazwa;
    private int koszt;

    Jednostka(String nazwa, int koszt) {
        this.nazwa = nazwa;
        this.koszt = koszt;
    }

    String getNazwa() {
        return nazwa;
    }

    int getKoszt() {
        return koszt;
    }
}
