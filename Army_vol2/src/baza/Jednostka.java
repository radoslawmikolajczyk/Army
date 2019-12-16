package baza;

public abstract class Jednostka {
    private String nazwa;
    private int koszt;

    public Jednostka(String nazwa, int koszt) {
        this.nazwa = nazwa;
        this.koszt = koszt;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getKoszt() {
        return koszt;
    }
}
