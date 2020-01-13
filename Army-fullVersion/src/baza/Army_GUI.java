package baza;

import javax.swing.*;

public class Army_GUI {

    public JPanel GUI;
    private JButton rekrutujSzeregowegoButton;
    private JTextField kosztSzeregowego;
    private JTextField iloscSzeregowych;
    private JButton rekrutujPodButton;
    private JTextField kosztPodpulkownik;
    private JTextField iloscPodpulkownikow;
    private JButton stworzPulkButton;
    private JTextField kosztPulku;
    private JTextField iloscPulkow;
    private JButton rekrutujPulkownikaButton;
    private JButton stworzBrygadeButton;
    private JButton rekrutujGeneralaButton;
    private JButton stworzDywizjonButton;
    private JTextField kosztPulkownika;
    private JTextField iloscPulkownikow;
    private JTextField kosztBrygady;
    private JTextField iloscBrygad;
    private JTextField kosztGenerala;
    private JTextField iloscGeneralow;
    private JTextField kosztDywizjonu;
    private JTextField iloscDywizjonow;
    private JTextField kasa;
    private JTextField przychod;
    private JButton zbudujButton;
    private JButton ulepszButton;
    private JTextField poziomKoszar;
    private JTextField produkcjaKoszar;
    private JTextField kosztKoszar;
    private JTextField wiadomosc;

    private Baza baza;

    public Army_GUI(Baza baza){
        this.baza = baza;

        Timer timer = new Timer(200, e -> refresh());   //Timer, który nam odświerza wyświetlane zmienne

        timer.start();

        //ActionListenery dla wszystkich przycisków

        rekrutujSzeregowegoButton.addActionListener(e -> baza.rekrutujSzeregowego());
        rekrutujPodButton.addActionListener(e -> baza.rekrutujPodpulkownika());
        stworzPulkButton.addActionListener(e -> baza.rekrutujPulk());
        rekrutujPulkownikaButton.addActionListener(e -> baza.rekrutujPulkownika());
        stworzBrygadeButton.addActionListener(e -> baza.rekrutujBrygade());
        rekrutujGeneralaButton.addActionListener(e -> baza.rekrutujGenerala());
        stworzDywizjonButton.addActionListener(e -> baza.rekrutujDywizjon());
        zbudujButton.addActionListener(e -> baza.zbudujKoszary());
        ulepszButton.addActionListener(e -> baza.ulepszKoszary());

    }


    private void refresh(){             //wyświetlanie wszystkich zmiennych w polach tekstowych
        iloscSzeregowych.setText(Integer.toString( baza.liczSzeregowych()));
        iloscPodpulkownikow.setText(Integer.toString((baza.liczPodpulkownikow())));
        iloscPulkownikow.setText(Integer.toString((baza.liczPulkownikow())));
        iloscGeneralow.setText(Integer.toString((baza.liczGeneralow())));

        iloscPulkow.setText(Integer.toString((baza.liczPulki())));
        iloscBrygad.setText(Integer.toString((baza.liczBrygady())));
        iloscDywizjonow.setText(Integer.toString((baza.liczDywizjony())));

        kasa.setText(Long.toString(baza.aktualizujPortfel()));

        kosztSzeregowego.setText(Integer.toString(baza.kosztSzeregowego()));
        kosztPulku.setText(Integer.toString(baza.kosztPulku()));
        kosztPodpulkownik.setText(Integer.toString((baza.kosztPodpulkownika())));
        kosztBrygady.setText(Integer.toString(baza.kosztBrygady()));
        kosztPulkownika.setText(Integer.toString((baza.kosztPulkownika())));
        kosztDywizjonu.setText(Integer.toString(baza.kosztDywizjonu()));
        kosztGenerala.setText(Integer.toString((baza.kosztGenerala())));

        kosztKoszar.setText(Integer.toString((baza.getKoszary().getKosztUlepszenia())));
        poziomKoszar.setText(Integer.toString((baza.getKoszary().getPoziomKoszar())));
        produkcjaKoszar.setText(Integer.toString((baza.getKoszary().getIleRekrutuje())));

        przychod.setText(Integer.toString(baza.getAktualnyPrzychod()));

        wiadomosc.setText(baza.getWiad());
    }

}
