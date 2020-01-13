package baza;


class Oddzial extends Jednostka {

    private int przychod;
    private static int[] zwiekszKoszt = {1, 1,1};

    Oddzial(String nazwa, int koszt, int przychod, int typ) {
        super(nazwa, koszt*zwiekszKoszt[typ-1]);     //sytuacja taka sama jak w klasie Dowodca
        this.przychod = przychod;
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
