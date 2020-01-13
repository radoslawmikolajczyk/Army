package baza;

public class Szeregowy extends Jednostka {

    private int przychod_szer = 1;
    private static int zwiekszKoszt = 0;    //Uzywam zmiennej statycznej ktora jest wspolna dla wszystkich obiektow danej klasy

    public Szeregowy(String nazwa, int koszt) {
        super(nazwa, koszt+zwiekszKoszt);   //za kazdym razem gdy kupuje szeregowego, to koszt nastepnego wzrasta o 10
    }

    int getPrzychod_szer() {
        return przychod_szer;
    }

    void zwiekszKoszt(){
        zwiekszKoszt+=10;
    }

    static int getZwiekszKoszt() {
        return zwiekszKoszt;
    }
}
