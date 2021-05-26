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
    private Træner træner = new Træner();
    private Filhåndtering filHåndtering = new Filhåndtering();
    private UI ui = new UI();


    //----Konstruktøren----
    public Formand() {
    }

    // ---------Gettere----------
    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
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
        System.out.println("\nDu er i gang med oprettelsen af et nyt medlem");
        System.out.println("Hvad er medlems navn?\nIndtast venigst et navn, der består af max 15 symboler: \n");
        ui.validerNavn(medlem, new Formand());}

    public void registrerAlder() {
        System.out.println("Hvad er medlems alder?: \nIndtast venigst en alder her:\n");
        ui.validerAlder(medlem, new Formand());}




    public void registrerAktivitetsstatus(){

        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");

        mulighed = ui.valider1Eller2();

        if (mulighed.equals("1")) {
            medlem.setAktivitetsstatus("Aktiv");
            System.out.println("Aktivitetsstatus sat til: Aktiv");
        } else if (mulighed.equals("2"))  {
            medlem.setAktivitetsstatus("Passiv");
            System.out.println("Aktivitetsstatus sat til: Passiv");
            System.out.println("Velkommen til klubben!");
        }
    }


    public void registrerAktivitetsform(){
        if (medlem.getAktivitetsstatus().equals("Aktiv")) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? " +
                "\nTast 1 for konkurrencesvømmer, 2 for motionist: ");

            mulighed = ui.valider1Eller2();

            if (mulighed.equals("1")) {
                medlem.setAktivitetsForm("Medlemmer.Konkurrencesvømmer");
                System.out.println("Ny konkurrencesvømmer registeret: " + medlem.getNavn());
                System.out.println("Velkommen til klubben " + medlem.getNavn() + "!");

            } else if (mulighed.equals("2")) {
                medlem.setAktivitetsForm("Motionist");
                System.out.println("Ny motionist registreret: " + medlem.getNavn());
                System.out.println("Velkommen til klubben " + medlem.getNavn() + "!");
                         }
        } else if (medlem.getAktivitetsstatus().equals("Passiv")) {
            medlem.setAktivitetsForm("Passiv");
        }
    }


    public void seMedlemmer() {
        ArrayList<Medlem> medlemmerEfterNavn = filHåndtering.downloadMedlemsFil();
        String betaltStr;

        Collections.sort(medlemmerEfterNavn, medlem.medlemmerEfterNavn); // sorterer eksisterende medlemer efter navn

        System.out.printf(" %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \n", "Medlems-",
            "Navn", "Alder", "Status", "Aktivitetsform", "Kontingent", "Kontingent", "Betalings-");
        System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \033[0m\n", "nummer",
            "", "", "", "", "om året", "i år", "status");
        for (int i = 0; i < medlemmerEfterNavn.size(); i++){

            if (medlemmerEfterNavn.get(i).getBetalt()){
                betaltStr = "Betalt";
            }
            else betaltStr = "Restance";

            System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \033[0m\n",
                medlemmerEfterNavn.get(i).getMedlemsnummer(),medlemmerEfterNavn.get(i).getNavn(),
                medlemmerEfterNavn.get(i).getAlder() + " år", medlemmerEfterNavn.get(i).getAktivitetsstatus(),
                medlemmerEfterNavn.get(i).getAktivitetsForm(), medlemmerEfterNavn.get(i).getKontingent(),
                medlemmerEfterNavn.get(i).getKontingentForRestenAfÅret(), betaltStr);
        }
    }

    public void sletMedlem(Menu menu) {

        System.out.println("VIGTIGT! Du er i gang med at slette et medlem");
        System.out.println("Vi har følgende  konkurrencesvømmere i vores klub:\n");
        seMedlemmer();
        System.out.println("Intast venligst et medlemsnummer for et medlem fra listen:");
        int svarMedlemsnummer = input.nextInt();
        String mulighed;
        ArrayList<Medlem> medlemTemp = filHåndtering.downloadMedlemsFil();
        for (int i = 0; i < medlemTemp.size(); i++) {

            if (medlemTemp.get(i).getMedlemsnummer() == svarMedlemsnummer) {
                System.out.println("Er du sikker?\nIndtast venligst: \n1.Ja \n2.Nej");
                mulighed = ui.valider1Eller2();
                if (mulighed.equals("1")) {
                    System.out.println("Medlemsnummer " + medlemTemp.get(i).getMedlemsnummer() +
                        " som hører til " + medlemTemp.get(i).getNavn() + " er blevet slettet nu");

                    medlemTemp.remove(i);
                    /*try {
                        menu.visMenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                } else {
                try {
                    menu.visMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            }
        }
     //   System.out.println(medlemTemp);
        try {
            new FileWriter("src/txt/Medlemliste.txt", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filHåndtering.uploadMedlemsFil(medlemTemp);
        medlemTemp.clear();
    }


}
