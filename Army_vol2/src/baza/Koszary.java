package baza;

import java.util.LinkedList;
import java.util.List;

public class Koszary extends Thread {
    private int poziomKoszar=1;
    private int ileRekrutuje = 1;
    private int kosztUlepszenia = 1000000;
    private int kosztKupna = 1000000;
    List<Szeregowy> listaKoszar = new LinkedList<>();   //lista w ktorej bede przechowywac zarekrutowanych szeregowych

    Koszary(){}                     //konstruktor bezparametrowy okreslajacy parametry koszar

    void ulepszKoszary(){               //zeby ulepszyc koszary musze zaplacic wiecej za kazdym razem o 500 000, wzrasta poziom i to ile szeregowych rekrutuja koszary
        this.kosztUlepszenia += 500000;
        this.poziomKoszar++;
        this.ileRekrutuje += 1;
    }

    void rekrutujSzeregowych(){    //rekrutacja szeregowych
        int ile = ileRekrutuje;  //zeby nie tracic wartosci zmiennej ileRekrutuje przypisuje jej wartosc do zmiennej ile
        while(ile > 0) {        // w zaleznosci od poziomu Koszar, bedzie rekrutowac odpowiednia ilosc szeregowych i dodawac ja do listy szeregowych
            Szeregowy szeregowyzKoszar = new Szeregowy("Szeregowy z Koszar", 20);
            listaKoszar.add(szeregowyzKoszar);
            ile--;
        }
    }

    public int getPoziomKoszar() {
        return poziomKoszar;
    }

    public int getIleRekrutuje() {
        return ileRekrutuje;
    }

    public int getKosztUlepszenia() {
        return kosztUlepszenia;
    }

    public int getKosztKupna() {
        return kosztKupna;
    }

    public List<Szeregowy> getListaKoszar() {
        return listaKoszar;
    }

    @Override
    public void run(){       //odpalam dodatkowy watek, ktory caly czas bedzie lecial i co 7 s bedzie rekrutowal kolejnego szeregowego
        while(true){
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rekrutujSzeregowych();
        }
    }
}
