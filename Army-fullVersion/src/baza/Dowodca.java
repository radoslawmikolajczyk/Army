package baza;

class Dowodca extends Jednostka {
    private int przychod;
    private static int[] zwiekszKoszt = {1, 1, 1};             //Uzywam zmiennej statycznej ktora jest wspolna dla wszystkich obiektow danej klasy

    Dowodca(String nazwa, int koszt, int przychod, int typ) {
        super(nazwa, koszt*zwiekszKoszt[typ-1]);         //za kazdym kolejnym razem gdy rekrutuje dowodce koszt wzrasta, np jak kupie podpulkownika place np 500, to za kolejnego juz musze zaplacic 1000
        this.przychod = przychod;                              //Aby każdemu dowodcy osobno zmieniał się koszt używamy listy 3 elementowej
    }

    int getPrzychod() {
        return przychod;
    }

    void zwiekszKoszt(int typ){
        zwiekszKoszt[typ-1]++;
    }

    static int getZwiekszKoszt(int typ) {
        return zwiekszKoszt[typ-1];
    }
}
