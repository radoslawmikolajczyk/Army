package baza;

import java.util.LinkedList;
import java.util.List;

public class Baza extends Thread{

    public Baza(){
        start();
    }      // gdy utworze obiekt tej klasy od razu uruchamiam watek

    private Portfel portfel = new Portfel(100);   //ile kasy mamy na start

    private Messege wiad = new Messege();       //Obiekt używany do wyświetlania wiadomości na spodzie ekranu

    //LISTY OBIEKTOW

    private List<Szeregowy> listaSzeregowych = new LinkedList<>();
    private List<Dowodca> listaDowodca = new LinkedList<>();
    private List<Oddzial> listaOddzialow = new LinkedList<>();

    private Koszary koszary = new Koszary();

    private int aktualnyPrzychod;


    private void dodajDoPortfela(){
        aktualnyPrzychod = 0;
        for (Szeregowy szer: listaSzeregowych) {         //pętle typu for each, "szer" pojedynczy szeregowy w liscie
            aktualnyPrzychod += szer.getPrzychod_szer();
        }
        for (Dowodca dow: listaDowodca) {
            aktualnyPrzychod += dow.getPrzychod();
        }                                                   // tutaj iterujemy po listach i dla kazdego elementu w liscie dodajemy do zmiennej aktualny przychod
        for (Oddzial odz: listaOddzialow) {
            aktualnyPrzychod += odz.getPrzychod();
        }
        portfel.dodajKase(aktualnyPrzychod);                // na koncu dodajemy aktualny przychod do portfela, metoda wywołuje się 5 razy na sekundę
    }

    void rekrutujPulk(){
        rekrutujOdzial("Pułk", "podpułkownik", 1000, 25, 1, 5);
    }


    void rekrutujBrygade(){
        rekrutujOdzial("Brygada", "pułkownik", 3000, 50, 2, 10);
    }

    void rekrutujDywizjon(){
        rekrutujOdzial("Dywizjon", "generał", 30000, 100, 3,25);
    }

    private void rekrutujOdzial(String nazwaOddzialu, String nazwaDowodcy, int koszt, int przychod, int typ, int wymaganiSzeregowi){
        Oddzial oddzial = new Oddzial(nazwaOddzialu,koszt,przychod, typ);
        if (portfel.getKasa() >= oddzial.getKoszt()) {                      //sprawdzam czy mnie stac na kupno tego oddzialu, czy w portfelu mam odpowiednie srodki na zakup
            if (listaSzeregowych.size() < wymaganiSzeregowi)   {                            //zakladam ze aby powstal oddzial podpułkownika musi odpowiednia ilosc szeregowych, sprawdzam rozmiar listy szeregowych
                wiad.szeregowy(wymaganiSzeregowi);}                                     //jeżeli mam za mało szeregowych, to wyrzucam odpowiednią wiadomość
            else {
                int licznik = 0;
                for (Dowodca d : listaDowodca){
                    if (d.getNazwa().equals(nazwaDowodcy))
                        licznik++;
                }                                                       //sprawdzam czy na mojej liscie dowodcow jest podpułkownik, jesli jest to usuwam go z listy dowodcow
                if (licznik == 0)                                       // i dodaje do listy oddzial podpulkownika, ktory generuje juz przychod
                    wiad.dowodca(nazwaDowodcy);
                else {
                    portfel.odejmijKase(oddzial.getKoszt());
                    for (Dowodca d : listaDowodca) {
                        if (d.getNazwa().equals(nazwaDowodcy)) {   //usuwam z listy dowodcow , mentalnie jest w oddziale
                            listaDowodca.remove(d);
                            break;
                        }
                    }
                    listaSzeregowych.subList(0, wymaganiSzeregowi).clear();               //usuwam także odpowiednią ilość szeregowych, którzy teraz już są w oddziale
                    oddzial.zwiekszKoszt(typ);
                    listaOddzialow.add(oddzial);
                }
            }
        } else {
            wiad.kasa(oddzial.getKoszt());
        }
    }


    void rekrutujSzeregowego(){
        Szeregowy szer = new Szeregowy("Szeregowy",10);
        if (portfel.getKasa() >= szer.getKoszt()) {                         //rekrutuje szeregowych, sprawdzam czy mnie stac na rekrutacje, pamietajac o tym ze za kazdym razem koszt kolejnego
            portfel.odejmijKase(szer.getKoszt());                           //szeregowego rosnie, jesli mnie stac to odejmuje koszt rekrutacji szeregowego z portfela i dodaje szeregowego do listy
            listaSzeregowych.add(szer);                                     //i on generuje mi przychod 1.
            szer.zwiekszKoszt();
        } else {
            wiad.kasa(szer.getKoszt());
        }
    }

    void rekrutujPodpulkownika(){
        rekrutujDowodce("podpułkownik", 500, 5, 1);
    }

    void rekrutujPulkownika(){
        rekrutujDowodce("pułkownik", 3000, 15, 2);
    }

    void rekrutujGenerala(){
        rekrutujDowodce("generał", 8000, 30, 3);
    }

    private void rekrutujDowodce(String nazwa, int koszt, int przychod, int typ){
        Dowodca dowodca = new Dowodca(nazwa,koszt,przychod, typ);
        if (portfel.getKasa() >= dowodca.getKoszt()){
            portfel.odejmijKase(dowodca.getKoszt());                   //analogcznie jak dla szeregowego
            listaDowodca.add(dowodca);
            dowodca.zwiekszKoszt(typ);
        } else {
            wiad.kasa(dowodca.getKoszt());
        }
    }


    long aktualizujPortfel(){
        return portfel.getKasa();
    }          // aktualizuje zawartosc portfela biorac pod uwage przychod z jednostek ktore znajduja sie na listach obiektow


    void zbudujKoszary(){
        if (!koszary.CzyZbudowane()) {
            if (portfel.getKasa() >= koszary.getKosztKupna()) {      //sytuacja podobna do rekrutacji jednostek, jednak tutaj obiekt inicjalizuje dopiero jak sprawdze czy mnie na niego stac
                portfel.odejmijKase(koszary.getKosztKupna());       //jesli tak to tworze obiekt
                koszary.wybuduj();
            } else {
                wiad.kasa(koszary.getKosztKupna());
            }
        } else wiad.setMessege("Koszary już zbudowane");
    }

    void ulepszKoszary(){
        if (koszary.CzyZbudowane()){
            if (portfel.getKasa() >= koszary.getKosztUlepszenia()){             //sprawdzam czy stac mnie na ulepszenie koszar i jesli tak to odejmuje kase z portfela
                portfel.odejmijKase(koszary.getKosztUlepszenia());
                koszary.ulepszKoszary();                                        // i ulepszam koszary
            } else
                wiad.kasa(koszary.getKosztUlepszenia());               //sprawdzam czy koszary sa wgl utworzone, jesli nie sa, tzn ze Koszary koszary; nie zostaly zainicjalizowane i maja wartosc null
        }
        else{                             //co zostanie przechwycone i zasygnalizuje brak utworzonych koszar
            wiad.setMessege("Koszary jeszcze nie są zbudowane");
        }
    }

    private void rekrutuj(){                                                    //Metoda, która rekrutuje odpowiednią ilość szeregowych w zależności od poziomu koszar
        for(int i = 0; i < koszary.getIleRekrutuje(); i++){
            listaSzeregowych.add(new Szeregowy("Szeregowy",10));
        }
    }


    // Metody liczenia ilości i kosztu rekrutacji jednostek i oddziałów, które są wykorzystywane przez GUI

    int liczSzeregowych(){
        return(listaSzeregowych.size());
    }
    int liczPodpulkownikow(){
        int n = 0;
        for (Dowodca d : listaDowodca){
            if (d.getNazwa().equals("podpułkownik"))
                n++;
        }
        return n;
    }
    int liczPulkownikow(){
        int n = 0;
        for (Dowodca d : listaDowodca){
            if (d.getNazwa().equals("pułkownik"))
                n++;
        }
        return n;
    }
    int liczGeneralow(){
        int n = 0;
        for (Dowodca d : listaDowodca){
            if (d.getNazwa().equals("generał"))
                n++;
        }
        return n;
    }
    int liczPulki(){
        int n = 0;
        for (Oddzial o : listaOddzialow){
            if (o.getNazwa().equals("Pułk"))
                n++;
        }
        return n;
    }
    int liczBrygady(){
        int n = 0;
        for (Oddzial o : listaOddzialow){
            if (o.getNazwa().equals("Brygada"))
                n++;
        }
        return n;
    }
    int liczDywizjony(){
        int n = 0;
        for (Oddzial o : listaOddzialow){
            if (o.getNazwa().equals("Dywizjon"))
                n++;
        }
        return n;
    }

    int kosztPulku(){
        return 1000 * Oddzial.getZwiekszKoszt(1);
    }
    int kosztBrygady(){
        return 3000 * Oddzial.getZwiekszKoszt(2);
    }
    int kosztDywizjonu(){
        return 30000 * Oddzial.getZwiekszKoszt(3);
    }

    int kosztPodpulkownika(){
        return 500*Dowodca.getZwiekszKoszt(1);
    }
    int kosztPulkownika(){
        return 3000*Dowodca.getZwiekszKoszt(2);
    }
    int kosztGenerala(){
        return 8000*Dowodca.getZwiekszKoszt(3);
    }
    int kosztSzeregowego(){
        return 10 + Szeregowy.getZwiekszKoszt();
    }


    Koszary getKoszary() {
        return koszary;
    }

    int getAktualnyPrzychod() {
        return aktualnyPrzychod;
    }

    String getWiad() {
        return wiad.getMessege();
    }

    private int i = 0;

    @Override
    public void run(){
        while(true){
            System.out.println(aktualizujPortfel());                //watek ktory startuje od razzu po utworzeniu obiektu baza , wyswietla aktualny stan portfela
            dodajDoPortfela();                                      //dodaje do portfela przychod z jednostek, ktore sa w listach
            if (i == 10){                                           //raz na 2s powstaje odpowiednia liczba jednostek z koszar
                rekrutuj();
                i = 0;
            }
            else i++;
            try {
                Thread.sleep(200);                          //odswiezane jest to wszystko co 0.2 sekundy (mozna zmienic)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
