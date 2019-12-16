package baza;

import java.util.LinkedList;
import java.util.List;

public class Baza extends Thread{

    Baza(){
        start();
    }      // gdy utworze obiekt tej klasy od razu uruchamiam watek

    Portfel portfel = new Portfel(100);   //portfel ktoryu mowi ile kasy mamy na start

    //LISTY OBIEKTOW

    List<Szeregowy> listaSzeregowych = new LinkedList<>();
    List<Dowodca> listaDowodca = new LinkedList<>();
    List<Oddzial> listaOddzialow = new LinkedList<>();
    List<Koszary> listKosz = new LinkedList<>();   //tworze aby po niej iterowac i aby gdy koszary nie zostaly jeszcze utworzone to zeby nie wywalalo NullPointerException

    Koszary koszary; //zostana zainicjalizowane dopiero wtedy gdy je kupimy

    void dodajDoPortfela(){
        for (Szeregowy szer: listaSzeregowych) {
            portfel.dodajKase(szer.getPrzychod_szer());
        }
        for (Dowodca dow: listaDowodca) {
            portfel.dodajKase(dow.getPrzychod());
        }                                                   // tutaj iterujemy po listach i dla kazdego elementu w liscie dodajemy do portfela przychod danej jednostki
        for (Oddzial odz: listaOddzialow) {
            portfel.dodajKase(odz.getPrzychod());
        }
        for (Koszary k : listKosz){
            for (Szeregowy s: k.listaKoszar){
                portfel.dodajKase(s.getPrzychod_szer());   // iterujemy po liscie z listy koszar ktora ma szeregowych i caly czas dodajemy przychod szeregowych z koszar do portfela
            }
        }
    }

    void rekrutujOddzialPodpulkownika(){
        Oddzial oddzial = new Oddzial("Oddział podpułkownika",1000,20);
        if (portfel.getKasa() >= oddzial.getKoszt()) {                      //sprawdzam czy mnie stac na kupno tego oddzialu, czy w portfelu mam odpowiednie srodki na zakup
            if (listaSzeregowych.size() < 5)                                //zakladam ze aby powstal oddzial podpułkownika musi byc przynamniej 5 szeregowych, sprawdzam rozmiar listy szeregowych
                System.out.println("Za mało szeregowych");
            else {
                int licznik = 0;
                for (Dowodca d : listaDowodca){
                    if (d.getNazwa().equals("podpułkownik"))
                        licznik++;
                }                                                       //sprawdzam czy na mojej liscie dowodcow jest podpułkownik, jesli jest to usuwam go z listy dowodcow
                if (licznik == 0)                                       // i dodaje do listy oddzial generala, ktory generuje juz przychod
                    System.out.println("Nie masz dowodcy podpulkownik!");
                else {
                    portfel.odejmijKase(oddzial.getKoszt());
                    for (Dowodca d : listaDowodca) {
                        if (d.getNazwa().equals("podpułkownik")) {
                            listaDowodca.remove(d);
                            break;
                        }
                    }
                    listaOddzialow.add(oddzial);
                }
            }
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }


    void rekrutujOddzialPulkownika(){
        Oddzial oddzial = new Oddzial("Oddział Pułkownika",3000,40);
        if (portfel.getKasa() >= oddzial.getKoszt()) {                      //sprawdzam czy mnie stac na kupno tego oddzialu
            if (listaSzeregowych.size() < 10)                               //zakladam ze aby powstal oddzial pułkownika musi byc przynamniej 10 szeregowych, sprawdzam rozmiar listy szeregowych
                System.out.println("Za mało szeregowych");
            else {
                int licznik = 0;
                for (Dowodca d : listaDowodca){
                    if (d.getNazwa().equals("pułkownik"))
                        licznik++;
                }                                                   //sprawdzam czy na mojej liscie dowodcow jest pułkownik, jesli jest to usuwam go z listy dowodcow
                if (licznik == 0)                                   // i dodaje do listy oddzial generala, ktory generuje juz przychod
                    System.out.println("Nie masz dowodcy pulkownik!");
                else {
                    portfel.odejmijKase(oddzial.getKoszt());
                    for (Dowodca d : listaDowodca) {
                        if (d.getNazwa().equals("pułkownik")) {
                            listaDowodca.remove(d);
                            break;
                        }
                    }
                    listaOddzialow.add(oddzial);
                }
            }
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }

    void rekrutujOddzialGenerala(){
        Oddzial oddzial = new Oddzial("Oddział Generała",30000,80);
        if (portfel.getKasa() >= oddzial.getKoszt()) {          //sprawdzam czy mnie stac na kupno tego oddzialu
            if (listaSzeregowych.size() < 25)                   //zakladam ze aby powstal oddzial generala musi byc przynamniej 25 szeregowych
                System.out.println("Za mało szeregowych");
            else {
                int licznik = 0;
                for (Dowodca d : listaDowodca){
                    if (d.getNazwa().equals("generał"))
                        licznik++;
                }                                               //sprawdzam czy na mojej liscie dowodcow jest generał, jesli jest to usuwam go z listy dowodcow
                if (licznik == 0)                               // i dodaje do listy oddzial generala, ktory generuje juz przychod
                    System.out.println("Nie masz dowodcy general!");
                else {
                    portfel.odejmijKase(oddzial.getKoszt());
                    for (Dowodca d : listaDowodca) {
                        if (d.getNazwa().equals("generał")) {
                            listaDowodca.remove(d);
                            break;
                        }
                    }
                    listaOddzialow.add(oddzial);
                }
            }
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }

    void rekrutujSzeregowego(){
        Szeregowy szer = new Szeregowy("Szeregowy",10);
        if (portfel.getKasa() >= szer.getKoszt()) {                         //rekrutuje szeregowych, sprawdzam czy mnie stac na rekrutacje, pamietajac o tym ze za kazdym razem koszt kolejnego
            portfel.odejmijKase(szer.getKoszt());                           //szeregowego rosnie, jesli mnie stac to odejmuje koszt rekrutacji szeregowego z portfela i dodaje szeregowego do listy
            listaSzeregowych.add(szer);                                     //i on generuje mi przychod 1.
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }

    void rekrutujPodpulkownika(){
        Dowodca podpulkownik = new Dowodca("podpułkownik",500,5);
        if (portfel.getKasa() >= podpulkownik.getKoszt()){
            portfel.odejmijKase(podpulkownik.getKoszt());                   //analogcznie jak dla szeregowego
            listaDowodca.add(podpulkownik);
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }

    void rekrutujPulkownika(){
        Dowodca pulkownik = new Dowodca("pułkownik",3000,15);
        if (portfel.getKasa() >= pulkownik.getKoszt()){
            portfel.odejmijKase(pulkownik.getKoszt());                      //analogcznie jak dla szeregowego
            listaDowodca.add(pulkownik);
        } else {
            System.out.println("Za mało pieniędzy.");
        }
    }

    void rekrutujGenerala(){
        Dowodca general = new Dowodca("generał", 8000,40);
        if (portfel.getKasa() >= general.getKoszt()){
            portfel.odejmijKase(general.getKoszt());                   //analogcznie jak dla szeregowego
            listaDowodca.add(general);
        } else {
            System.out.println("Za mało pieniędzy");
        }
    }


    long aktualizujPortfel(){
        return portfel.getKasa();
    }          // aktualizuje zawartosc portfela biorac pod uwage przychod z jednostek ktore znajduja sie na listach obiektow

    boolean czyKoszaryNieUtworzone = true;

    void zbudujKoszary(){
        koszary = new Koszary();
        if (portfel.getKasa() >= koszary.getKosztKupna() && czyKoszaryNieUtworzone){      //sytuacja podobna do rekrutacji jednostek, jednak tutaj obiekt inicjalizuje dopiero jak sprawdze czy mnie na niego stac
            koszary.start();                                               //odpalam watek ktory rekrutuje mi szeregowych w koszarach
            portfel.odejmijKase(koszary.getKosztKupna());       //jesli tak to tworze obiekt -> watek Koszar sie odpala i juz rekrutuje szeregowych; odejmuje kase z portfela
            listKosz.add(koszary);                          //dodaje koszary do listy obiektow, nie tworzylem klasy Koszar jako wzorzec Singleton bo mogloby to namieszac podczas inicjalizacji obiektu
            czyKoszaryNieUtworzone = false;                 //mozemy wyjsc z zalozenia ze koszary beda tylko jedne, poprzez dodanie zmiennej ktora bedzie sprawdzac czy koszary sa juz utworzone
        } else {
            koszary = null;
            System.out.println("Za mało pieniędzy. Lub koszary sa juz utworzone");
        }
    }

    void ulepszKoszary(){
        try{
            if (portfel.getKasa() >= koszary.getKosztUlepszenia()){             //sprawdzam czy stac mnie na ulepszenie koszar i jesli tak to odejmuje kase z portfela
                koszary.ulepszKoszary();                                        // i ulepsam koszary
                portfel.odejmijKase(koszary.getKosztUlepszenia());
            } else
                System.out.println("Za mało pieniędzy.");               //sprawdzam czy koszary sa wgl utworzone, jesli nie sa, tzn ze Koszary koszary; nie zostaly zainicjalizowane i maja wartosc null
        } catch (NullPointerException ex){                              //co zostanie przechwycone i zasygnalizuje brak utworzonych koszar
            System.out.println("Nie ma utworzonych koszar");
        }
    }

    @Override
    public void run(){
        while(true){
            System.out.println(aktualizujPortfel());                //watek ktory startuje od razzu po utworzeniu obiektu baza , wyswietla aktualny stan portfela
            dodajDoPortfela();                                      //dodaje do portfela przychod z jednostek, ktore sa w listach
            try {
                Thread.sleep(500);                          //odswiezane jest to wszystko co 0.5 sekundy (mozna zmienic)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
