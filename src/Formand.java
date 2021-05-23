import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formand {

    //----Attributter
    ArrayList<String> medlemsFilliste = new ArrayList<>();
    private int svarPåAktivitetsStatus;
    private int svarPåAktivitetsForm;
    Scanner input = new Scanner(System.in);
   // private int nummerTæller = 1;
    //----Lister----
    private ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Objekter----
    Medlem medlem = new Medlem();
    Kasserer kasserer = new Kasserer();
    Træner træner = new Træner();
    Filhåndtering filHåndtering = new Filhåndtering();

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
        findOgSetMedlemsnummer();
        registrerStamoplysninger();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        kasserer.harBetalt(medlem);
        opretMedlem();
        filHåndtering.uploadMedlemsFil(medlemmer);

    }

    public void opretMedlem() {
        medlemmer.add(new Medlem(medlem.getMedlemsnummer(), medlem.getNavn(), medlem.getAlder(), medlem.getAktivitetsstatus(),
            medlem.getAktivitetsForm(), medlem.getKontingent(), medlem.getKontingentForRestenAfÅret(),
            medlem.getBetalt()));
    }




    public void findOgSetMedlemsnummer() {
        int nummerTæller;
        if (filHåndtering.downloadMedlemsliste().size() == 0) {
            nummerTæller = 1;
            medlem.setMedlemsnummer(nummerTæller);
        } else {
            ArrayList<Integer> medlemsnumre = new ArrayList<>();
            for (int i = 0; i < filHåndtering.downloadMedlemsliste().size(); i++) {
                medlemsnumre.add(filHåndtering.downloadMedlemsliste().get(i).getMedlemsnummer());
            }
            nummerTæller = Collections.max(medlemsnumre);
            medlem.setMedlemsnummer(nummerTæller + 1);
        }
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
             //   træner.getKonkurrencesvømmerListe().add(new Konkurrencesvømmer(medlem. getMedlemsnummer(),
             //       medlem.getNavn(), medlem.getAlder()));
                System.out.println("Velkommen til klubben " + medlem.getNavn() + "!");
              //  træner.afgørHoldEfterÅrgang();
              //  træner.getKonkurrencesvømmerListe().clear();
             //   filHåndtering.uploadKonkurrencesvømmerFil(træner.getKonkurrencesvømmerListe());
             //   træner.getKonkurrencesvømmerListe().clear(); //

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
        ArrayList<Medlem> medlemmerEfterNavn = filHåndtering.downloadMedlemsliste();
        String betaltStr;

        Collections.sort(medlemmerEfterNavn, medlem.medlemmerEfterNavn); // sorterer eksisterende medlemer efter navn

        System.out.printf(" %-3s %-15s %-10s %-10s %-30s %-20s %-20s %-15s \n", "Nr.",
            "Navn", "Alder", "Status", "Aktivitetsform", "Kontingent", "Kontingent", "Betalings-");
        System.out.printf("\033[4m %-3s %-15s %-10s %-10s %-30s %-20s %-20s %-15s \033[0m\n", "",
            "", "", "", "", "", "i år", "status");
        for (int i = 0; i < medlemmerEfterNavn.size(); i++){

            if (medlemmerEfterNavn.get(i).getBetalt()){
                betaltStr = "Betalt";
            }
            else betaltStr = "Restance";

            System.out.printf("\033[4m %-3d %-15s %-10s %-10s %-30s %-20s %-20s %-15s \033[0m\n",
                medlemmerEfterNavn.get(i).getMedlemsnummer(),medlemmerEfterNavn.get(i).getNavn(),
                medlemmerEfterNavn.get(i).getAlder() + " år", medlemmerEfterNavn.get(i).getAktivitetsstatus(),
                medlemmerEfterNavn.get(i).getAktivitetsForm(), medlemmerEfterNavn.get(i).getKontingent(),
                medlemmerEfterNavn.get(i).getKontingentForRestenAfÅret(), betaltStr);
        }
    }

}













