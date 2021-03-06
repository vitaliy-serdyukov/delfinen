package ansatte;

import medlemmer.Medlem;
import menu.Menu;
import ui.Filhåndtering;
import ui.UI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private String mulighed;
    private Scanner input = new Scanner(System.in);
    //----Lister----
    private ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Objekter----
    private Medlem medlem = new Medlem();
    private Kasserer kasserer = new Kasserer();
    private Filhåndtering filHåndtering = new Filhåndtering();
    private UI ui = new UI();


    //----Konstruktøren----
    public Formand() {
    }

    //----Metoder----
    public void run() {
        findOgSetMedlemsnummer();
        registrerNavn();
        registrerAlder();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        kasserer.harBetalt(medlem);
        opretMedlem();
        filHåndtering.uploadMedlemsFil(medlemmer);
    }

    public void opretMedlem() {
        medlemmer.add(new Medlem(medlem.getMedlemsnummer(), medlem.getNavn(), medlem.getAlder(),
            medlem.getAktivitetsstatus(), medlem.getAktivitetsForm(), medlem.getKontingent(),
            medlem.getKontingentForRestenAfÅret(),
            medlem.getBetalt()));
    }

    public void seMedlemmer() {
        ArrayList<Medlem> medlemmerEfterNavn = filHåndtering.downloadMedlemsFil();
        String betaltStr;

        Collections.sort(medlemmerEfterNavn, medlem.medlemmerEfterNavn); // sorterer eksisterende medlemer efter navn

        System.out.printf("\n %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \n", "Medlems-",
            "Navn", "Alder", "Status", "Aktivitetsform", "Kontingent", "Kontingent", "Betalings-");
        System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \033[0m\n", "nummer",
            "", "", "", "", "om året", "i år", "status");

        for (int i = 0; i < medlemmerEfterNavn.size(); i++) {

            if (medlemmerEfterNavn.get(i).getBetalt()) {
                betaltStr = "Betalt";
            } else betaltStr = "Restance";

            System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15.2f %-10s \033[0m\n",
                medlemmerEfterNavn.get(i).getMedlemsnummer(), medlemmerEfterNavn.get(i).getNavn(),
                medlemmerEfterNavn.get(i).getAlder() + " år", medlemmerEfterNavn.get(i).getAktivitetsstatus(),
                medlemmerEfterNavn.get(i).getAktivitetsForm(), medlemmerEfterNavn.get(i).getKontingent(),
                medlemmerEfterNavn.get(i).getKontingentForRestenAfÅret(), betaltStr);
        }
    }

    public void findOgSetMedlemsnummer() {
        int nummerTæller;
        if (filHåndtering.downloadMedlemsFil().size() == 0) {
            nummerTæller = 1;
            medlem.setMedlemsnummer(nummerTæller);
        } else {
            ArrayList<Integer> medlemsnumre = new ArrayList<>();
            for (int i = 0; i < filHåndtering.downloadMedlemsFil().size(); i++) {
                medlemsnumre.add(filHåndtering.downloadMedlemsFil().get(i).getMedlemsnummer());
            }
            nummerTæller = Collections.max(medlemsnumre);
            medlem.setMedlemsnummer(nummerTæller + 1);
        }
    }

    public void registrerNavn() {
        ui.returnerBesked("\nDu er i gang med oprettelsen af et nyt medlem.\n");
        ui.returnerBesked("Indtast venligst et navn: \n");
        ui.validerNavn(medlem, new Formand());
    }

    public void registrerAlder() {
        ui.returnerBesked("Indtast venligst en alder:\n");
        ui.validerAlder(medlem, new Formand());
    }


    public void registrerAktivitetsstatus() {

        ui.returnerBesked("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: \n");

        mulighed = ui.valider1Eller2();

        if (mulighed.equals("1")) {
            medlem.setAktivitetsstatus("Aktiv");
            ui.returnerBesked("Aktivitetsstatus sat til: Aktiv\n");
        } else if (mulighed.equals("2")) {
            medlem.setAktivitetsstatus("Passiv");
            ui.returnerBesked("Aktivitetsstatus sat til: Passiv\n");
            ui.returnerBesked("\nVelkommen til klubben!\n");
        }
    }

    public void registrerAktivitetsform() {
        if (medlem.getAktivitetsstatus().equals("Aktiv")) {
            ui.returnerBesked("\nHvad for en aktivitetsform er du interesseret i?\n" +
                "Tast 1 for konkurrencesvømmer, 2 for motionist: \n");

            mulighed = ui.valider1Eller2();

            if (mulighed.equals("1")) {
                medlem.setAktivitetsForm("Konkurrencesvømmer");
                ui.returnerBesked("Ny konkurrencesvømmer registeret: " + medlem.getNavn() + "\n");
                ui.returnerBesked("Velkommen til klubben " + medlem.getNavn() + "!\n");

            } else if (mulighed.equals("2")) {
                medlem.setAktivitetsForm("Motionist");
                ui.returnerBesked("Ny motionist registreret: " + medlem.getNavn() + "\n");
                ui.returnerBesked("Velkommen til klubben " + medlem.getNavn() + "!\n");
            }
        } else if (medlem.getAktivitetsstatus().equals("Passiv")) {
            medlem.setAktivitetsForm("Passiv");
        }

    }

    public void sletMedlem() {

        ui.returnerBesked("VIGTIGT! Du er i gang med at slette et medlem");
        ui.returnerBesked("Vi har følgende  konkurrencesvømmere i vores klub:\n");
        seMedlemmer();
        ui.returnerBesked("\nIntast venligst et medlemsnummer for et medlem fra listen:\n");
        int svarMedlemsnummer = input.nextInt();

        ArrayList<Medlem> medlemTemp = filHåndtering.downloadMedlemsFil();

        for (int i = 0; i < medlemTemp.size(); i++) {

            if (medlemTemp.get(i).getMedlemsnummer() == svarMedlemsnummer) {
                ui.returnerBesked("Er du sikker?\nIndtast venligst: \n1.Ja \n2.Nej\n");
                mulighed = ui.valider1Eller2();
                if (mulighed.equals("1")) {
                    System.out.println("Medlemsnummer " + medlemTemp.get(i).getMedlemsnummer() +
                        " som hører til " + medlemTemp.get(i).getNavn() + " er blevet slettet nu");
                    medlemTemp.remove(i);
                    break;
                }
            }
        }
            try {
                new FileWriter("src/txt/Medlemliste.txt", false).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            filHåndtering.uploadMedlemsFil(medlemTemp);
            medlemTemp.clear();

    }
}



