import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Formand {

    //----Attributter
    ArrayList<String> medlemsFilliste = new ArrayList<>();
    private int svarPåAktivitetsStatus;
    private int svarPåAktivitetsForm;
    Scanner input = new Scanner(System.in);

    //----Lister----
    private ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Objekter----
    Medlem medlem = new Medlem();
    Kasserer kasserer = new Kasserer();
    Træner træner = new Træner();
    Filhåndtering fh = new Filhåndtering();

    //----Konstruktøren----
    public Formand() {
    }

    // ---------Gettere----------
    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }
    public ArrayList<String> getMedlemsFilliste() {
        return medlemsFilliste;
    }

    //----Metoder----
    public void run() {
        registrerStamoplysninger();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        kasserer.harBetalt(medlem);
        opretMedlem();
        fh.uploadMedlemsFil(medlemmer);
    }

    public void opretMedlem() {
        medlemmer.add(new Medlem(medlem.getNavn(), medlem.getAlder(), medlem.getAktivitetsstatus(),
            medlem.getAktivitetsForm(), medlem.getKontingent(), medlem.getKontingentForRestenAfÅret(),
            medlem.getBetalt()));
    }

    public void registrerStamoplysninger(){
        System.out.println("Hvad er dit navn?: ");
        medlem.setNavn(input.nextLine());
        System.out.println("Hvad er din alder?: ");
        medlem.setAlder(input.nextInt());
    }

    public void registrerAktivitetsstatus(){
        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");
        svarPåAktivitetsStatus =  input.nextInt();
        if (svarPåAktivitetsStatus == 1){
            medlem.setAktivitetsstatus("Aktiv");
        } else if (svarPåAktivitetsStatus == 2)  {
            medlem.setAktivitetsstatus("Passiv");
            System.out.println("Velkommen til klubben!");
        } // UI, input validering her
    }

    public void registrerAktivitetsform(){
        if (medlem.getAktivitetsstatus().equals("Aktiv")) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? \nTast 1 for konkurrencesvømmer, 2 for motionist: ");
            svarPåAktivitetsForm = input.nextInt();
            if (svarPåAktivitetsForm == 1) {
                medlem.setAktivitetsForm("Konkurrencesvømmer");
                System.out.println("Ny konkurrencesvømmer registeret: " + medlem.getNavn());
                træner.getKonkurrencesvømmerListe().add(new Konkurrencesvømmer(medlem.getNavn(), medlem.getAlder()));
                System.out.println("Velkommen til klubben " + medlem.getNavn() + "!");
              //  træner.afgørHoldEfterÅrgang();
              //  træner.getKonkurrencesvømmerListe().clear();
                fh.uploadKonkurrencesvømmerFil(træner.getKonkurrencesvømmerListe());
                træner.getKonkurrencesvømmerListe().clear(); //

                input.nextLine(); //Scanner bug
            } else if (svarPåAktivitetsForm == 2) {
                medlem.setAktivitetsForm("Motionist");
                System.out.println("Ny motionist registreret: " + medlem.getNavn());
               // træner.getMotionistListe().add(new Motionist(medlem.getNavn(), medlem.getAlder()));
                System.out.println("Velkommen til klubben " + medlem.getNavn() + "!");
                input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
            }
        } else if (medlem.getAktivitetsstatus().contains("Passiv")){
            medlem.setAktivitetsForm("Passiv");
            input.nextLine(); //Scanner bug
        }
    }

    public void seMedlemmer() {
        System.out.println(fh.downloadMedlemsliste().toString().replaceAll("\\[","").
            replaceAll("]", "").replaceAll(", ", ""));
    }


}









