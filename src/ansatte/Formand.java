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
        ui.returnMessage("\nDu er i gang med oprettelsen af et nyt medlem.\n");
        ui.returnMessage("Hvad er medlems navn?\nIndtast venigst et navn, der består af max 15 symboler: \n");
        ui.validerNavn(medlem, new Formand());}

    public void registrerAlder() {
        ui.returnMessage("Hvad er medlems alder?: \nIndtast venigst en alder her:\n");
        ui.validerAlder(medlem, new Formand());}


    public void registrerAktivitetsstatus(){

        ui.returnMessage("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: \n");

        mulighed = ui.valider1Eller2();

        if (mulighed.equals("1")) {
            medlem.setAktivitetsstatus("Aktiv");
            ui.returnMessage("Aktivitetsstatus sat til: Aktiv\n");
        } else if (mulighed.equals("2"))  {
            medlem.setAktivitetsstatus("Passiv");
            ui.returnMessage("Aktivitetsstatus sat til: Passiv\n");
            ui.returnMessage("Velkommen til klubben!\n");
        }
    }

    public void registrerAktivitetsform(){
        if (medlem.getAktivitetsstatus().equals("Aktiv")) {
            ui.returnMessage("Hvad for en aktivitetsform er du interesseret i?\n" +
                "Tast 1 for konkurrencesvømmer, 2 for motionist: \n");

            mulighed = ui.valider1Eller2();

            if (mulighed.equals("1")) {
                medlem.setAktivitetsForm("Konkurrencesvømmer");
                ui.returnMessage("Ny konkurrencesvømmer registeret: " + medlem.getNavn() + "\n");
                ui.returnMessage("Velkommen til klubben " + medlem.getNavn() + "!\n");

            } else if (mulighed.equals("2")) {
                medlem.setAktivitetsForm("Motionist");
                ui.returnMessage("Ny motionist registreret: " + medlem.getNavn() + "\n");
                ui.returnMessage("Velkommen til klubben " + medlem.getNavn() + "!\n");
                         }
        } else if (medlem.getAktivitetsstatus().equals("Passiv")) {
            medlem.setAktivitetsForm("Passiv");
        }
    }

   /* public void sletMedlem(Menu menu) {

        ui.returnMessage("VIGTIGT! Du er i gang med at slette et medlem");
        ui.returnMessage("Vi har følgende  konkurrencesvømmere i vores klub:\n");
        ui.seMedlemmer();
        ui.returnMessage("Intast venligst et medlemsnummer for et medlem fra listen: ");
        int svarMedlemsnummer = input.nextInt();
        String mulighed;
        ArrayList<Medlem> medlemTemp = filHåndtering.downloadMedlemsFil();
        for (int i = 0; i < medlemTemp.size(); i++) {

            if (medlemTemp.get(i).getMedlemsnummer() == svarMedlemsnummer) {
                ui.returnMessage("Er du sikker?\nIndtast venligst: \n1.Ja \n2.Nej");
                mulighed = ui.valider1Eller2();
                if (mulighed.equals("1")) {
                    System.out.println("Medlemsnummer " + medlemTemp.get(i).getMedlemsnummer() +
                        " som hører til " + medlemTemp.get(i).getNavn() + " er blevet slettet nu");

                    medlemTemp.remove(i);
                    /*try {
                        menu.visMenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                try {
                    menu.visMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            }
        }
     //  System.out.println(medlemTemp);
        try {
            new FileWriter("src/txt/Medlemliste.txt", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filHåndtering.uploadMedlemsFil(medlemTemp);
        medlemTemp.clear();
    }

    */

}
