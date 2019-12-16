package baza;

public class Dowodca extends Jednostka {
    private int przychod = 0;
    private static int zwiekszKoszt = 1;

    public Dowodca(String nazwa, int koszt, int przychod) {
        super(nazwa, koszt*zwiekszKoszt);      //za kazdym kolejnym razem gdy rekrutuje dowodce koszt wzrasta, np jak kupie podpulkownika place np 500, to za kolejnego juz musze zaplacic 1000
        this.przychod = przychod;                    // i jak kupie kolejnego dowodce, nie koniecznie podpulkownika to cena tez rosnie, czyli zalozmy ze teraz chcemy kupic generala ktorego koszt wynosi 10000,
        ++zwiekszKoszt;                              // to zeby go kupic majac juz 2 dowodcow to musze zaplacic 30 000 . Uzywam zmiennej statycznej ktora jest wspolna dla wszystkich obiektow danej klasy
    }

    public int getPrzychod() {
        return przychod;
    }
}
