package baza;

import java.util.LinkedList;
import java.util.List;

public class Koszary extends Thread {
    private int poziomKoszar = 0;
    private int ileRekrutuje = 0;
    private int kosztUlepszenia = 100000;
    private int kosztKupna = 100000;
    private boolean czyZbudowane = false;       //mozemy wyjsc z zalozenia ze koszary beda tylko jedne, poprzez dodanie zmiennej ktora bedzie sprawdzac czy koszary sa juz utworzone


    Koszary() {}                     //konstruktor bezparametrowy

    void wybuduj() {
        this.poziomKoszar = 1;
        this.ileRekrutuje = 1;
        czyZbudowane = true;
    }

    void ulepszKoszary() {               //zeby ulepszyc koszary musze zaplacic wiecej za kazdym razem o 500 000, wzrasta poziom i to ile szeregowych rekrutuja koszary
        this.kosztUlepszenia += 50000;
        this.poziomKoszar++;
        this.ileRekrutuje++;
    }


    int getPoziomKoszar() {
        return poziomKoszar;
    }

    int getIleRekrutuje() {
        return ileRekrutuje;
    }

    int getKosztUlepszenia() {
        return kosztUlepszenia;
    }

    int getKosztKupna() {
        return kosztKupna;
    }

    boolean CzyZbudowane() {
        return czyZbudowane;
    }

}

