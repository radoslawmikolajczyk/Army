package baza;


public class Oddzial extends Jednostka {

    private int przychod = 0;
    private static int zwiekszKoszt = 1;

    public Oddzial(String nazwa, int koszt,int przychod) {
        super(nazwa, koszt*zwiekszKoszt);     //sytuacja taka sama jak w klasie Dowodca
        this.przychod = przychod;
        ++zwiekszKoszt;
    }

    public int getPrzychod() {
        return przychod;
    }
}
