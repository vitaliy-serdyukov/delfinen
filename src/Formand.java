import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private Scanner input = new Scanner(System.in);

    //----Lister----
    ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Objekter----
    Medlem medlem = new Medlem();
    Kasserer kasserer = new Kasserer();
    // Menu menu = new Menu();

    //----Konstruktøren----
    public Formand() {
    }


    // ---------Gettere----------
    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }

    //----Metoder----
    public void run() throws IOException {

        registrerNavnOgAlder();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        opretMedlem();

        new Konkurrencesvømmer().afgørKonkurrencesvømmer(medlem);

    }

    public void opretMedlem() {

        medlemmer.add(new Medlem(medlem.getNavn(), medlem.getAlder(), medlem.getAktivitetsstatus(),
            medlem.getAktivitetsForm(), medlem.getKontingent()));
    }

    public void registrerNavnOgAlder(){
        System.out.println("Hvad er dit navn?: ");
        medlem.setNavn(input.nextLine());
        System.out.println("Hvad er din alder?: ");
        medlem.setAlder(input.nextInt());
    }

    public void registrerAktivitetsstatus() throws IOException {
        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");
        medlem.setAktivitetsstatus(input.nextInt());
        if (medlem.getAktivitetsstatus() == 1){
            medlem.setAktivitetsstatus(1);
        }
        else if (medlem.getAktivitetsStatus() == 2)  {
            medlem.setAktivitetsstatus(2);
            System.out.println("Velkommen til klubben!");
        } // UI, input validering her

    }

    public void registrerAktivitetsform() throws IOException {
        if (medlem.getAktivitetsStatus() == 1) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? \nTast 1 for konkurrencesvømmer, 2 for motionist: ");
            medlem.setAktivitetsForm(input.nextInt());
            if (medlem.getAktivitetsForm() == 1 )
            System.out.println("Ny konkurrencesvømmer registeret");
            System.out.println("Velkommen til klubben!");
        } else if (medlem.getAktivitetsForm() == 2) {
            System.out.println("Ny motionist registreret:");
            System.out.println("Velkommen til klubben!");
        }
    }

    public void seMedlemmer() {
        System.out.println("Ser medlemmer");
        for (int i = 0; i < medlemmer.size(); i++ ){
            System.out.println(medlemmer.toString().replaceAll("\\[", "").replaceAll("]", ""));
        }
    }

}


