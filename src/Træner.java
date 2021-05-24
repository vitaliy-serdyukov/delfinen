import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Træner {

    // ----Variabler--------------
    private ArrayList<Konkurrencesvømmer> KonkurrencesvømmerResultat = new ArrayList<>();  // Konkurrencesvømmere med resultat
    private ArrayList<Konkurrencesvømmer> junior = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> senior = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> disciplinArray = new ArrayList<Konkurrencesvømmer>();
    private Scanner scan = new Scanner(System.in);
    private String disciplin;


    // -----Objekter--------------
    private Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
    private Filhåndtering filhåndtering = new Filhåndtering();


    //----Gettere----

    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerResultat() {
        return KonkurrencesvømmerResultat;
    }


    public void printKonkurrencesvømmer(Medlem medlem) {

        ArrayList<Medlem> konkurrencesvømmere = new ArrayList<>();
        for (int i = 0; i < filhåndtering.downloadMedlemsliste().size(); i++) {
            if (filhåndtering.downloadMedlemsliste().get(i).getAktivitetsForm().equals("Konkurrencesvømmer")) {
                konkurrencesvømmere.add(filhåndtering.downloadMedlemsliste().get(i));
            }
        }
        Collections.sort(konkurrencesvømmere, medlem.medlemmerEfterNavn);

        System.out.println("Vi har følgende konkurrencesvømmere i vores klub:\n ");

        System.out.printf(" %-10s %-15s %-10s\n", "Medlems-",
            "Navn", "Alder");
        System.out.printf("\033[4m %-10s %-15s %-10s \033[0m\n", "nummer", "", "");

        for (int i = 0; i < konkurrencesvømmere.size(); i++) {

            System.out.printf("\033[4m %-10s %-15s %-10s\033[0m\n", konkurrencesvømmere.get(i).getMedlemsnummer(),
                konkurrencesvømmere.get(i).getNavn(), konkurrencesvømmere.get(i).getAlder() + " år");
        }


    }

    public void printResultatKonkurrencesvømmer() {
        ArrayList<Konkurrencesvømmer> resultaterPåSkærm = filhåndtering.downloadKonkurrencesvømmerResultatFil();
        Collections.sort(resultaterPåSkærm, konkurrencesvømmer.konkurrencesvømmerEfterNavnAlder);
        System.out.printf("\033[4m %-3s %-15s %-10s %-15s %-30s %-20s\033[0m\n", "Nr.",
            "Navn", "Alder", "Disciplin", "Resultat", "Dato");

        for (int i = 0; i < resultaterPåSkærm.size(); i++) {

            System.out.printf("\033[4m %-3d %-15s %-10s %-15s %-30s %-20s\033[0m\n", resultaterPåSkærm.get(i).
                    getMedlemsnummer(), resultaterPåSkærm.get(i).getNavn(), resultaterPåSkærm.get(i).getAlder() + " år",
                resultaterPåSkærm.get(i).getSvømmedisciplin(), resultaterPåSkærm.get(i).getSvømmeresultat(),
                resultaterPåSkærm.get(i).getResultatsDato());
        }
    }


    public void registrerSvømmeresultat(Medlem medlem) {
        int svarMedlemsnummer;
        int svarDisciplin;

        System.out.println("Vi har følgende  konkurrencesvømmere i vores klub:\n");
        printKonkurrencesvømmer(medlem);
        System.out.println("\nIntast venligst medlemsnummer for medlem fra overnævnte liste:");
        svarMedlemsnummer = scan.nextInt();

        for (int i = 0; i < filhåndtering.downloadMedlemsliste().size(); i++) {

            if (filhåndtering.downloadMedlemsliste().get(i).getMedlemsnummer() == svarMedlemsnummer) {

                konkurrencesvømmer.setMedlemsnummer(filhåndtering.downloadMedlemsliste().get(i).getMedlemsnummer());
                konkurrencesvømmer.setNavn(filhåndtering.downloadMedlemsliste().get(i).getNavn());
                konkurrencesvømmer.setAlder(filhåndtering.downloadMedlemsliste().get(i).getAlder());

                System.out.println("For hvilken svømmedisciplin skal registreres resultat:  ");
                System.out.println("\nVælg venligst mellem 1 eller 4");
                System.out.println("\n1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning");

                svarDisciplin = scan.nextInt();

                switch (svarDisciplin) {
                    case 1 -> konkurrencesvømmer.setSvømmedisciplin("Butterfly");
                    case 2 -> konkurrencesvømmer.setSvømmedisciplin("Crawl");
                    case 3 -> konkurrencesvømmer.setSvømmedisciplin("Rygcrawl");
                    case 4 -> konkurrencesvømmer.setSvømmedisciplin("Brystsvømning");
                    default -> registrerSvømmeresultat(medlem);
                }

                System.out.println("Indtast venligst resultat i format '00,0' sekunder:");
                konkurrencesvømmer.setSvømmeresultat(scan.nextDouble());
                konkurrencesvømmer.setResultatsDato();

                // evt. en metode her
                KonkurrencesvømmerResultat.add(new Konkurrencesvømmer(konkurrencesvømmer.getMedlemsnummer(),
                    konkurrencesvømmer.getNavn(), konkurrencesvømmer.getAlder(), konkurrencesvømmer.getSvømmedisciplin(),
                    konkurrencesvømmer.getSvømmeresultat(), konkurrencesvømmer.getResultatsDato()));


                filhåndtering.uploadKonkurrencesvømmerResultatFil(KonkurrencesvømmerResultat);
                KonkurrencesvømmerResultat.clear();

                System.out.println("Ny resultat er registreret for:  \n" +
                    konkurrencesvømmer.getNavn() + "\nDisciplin: " + konkurrencesvømmer.getSvømmedisciplin() +
                    "\nTid: " + konkurrencesvømmer.getSvømmeresultat());

                  udskiftTilEnBedreResultat();
            } else {
                    /*do {
                        System.out.println("Navnet er indtastet forkert eller ikke fundet i junior liste");
                        svarNavn = scan.nextLine();

                    } while (!(downloadJuniorsvømmerFil().get(i).getNavn().equals(svarNavn)));*//*
                // TO DO VALIDERING*/
            }
        }
    }

    public void udskiftTilEnBedreResultat(){
        ArrayList<Konkurrencesvømmer> bedsteResultat = filhåndtering.downloadKonkurrencesvømmerResultatFil();

        System.out.println("Test1" + bedsteResultat);

        Collections.sort(bedsteResultat,konkurrencesvømmer.konkurrencesvømmerEfterNavnDisciplinResultat);

        System.out.println("Test2" + bedsteResultat);

            if (bedsteResultat.size() > 1) {
                for (int j = 0; j < bedsteResultat.size(); j++) { // fjerner værste sesultat, hvis en medlem har flere?
                    for (int k = j + 1; j < bedsteResultat.size(); j++) {

                        if ((bedsteResultat.get(j).getMedlemsnummer() == bedsteResultat.get(k).
                            getMedlemsnummer()) && bedsteResultat.get(j).getSvømmedisciplin().
                            equals(bedsteResultat.get(k).getSvømmedisciplin())) {

                            bedsteResultat.remove(k);
                            System.out.println("JA");
                             k--;

                        }
                    }

                }
            }
        System.out.println("Test3" + bedsteResultat);
        try {
            new FileWriter("src/KonkurrencesvømmerResultat.txt", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filhåndtering.uploadKonkurrencesvømmerResultatFil(bedsteResultat);
        bedsteResultat.clear();
    }


    public void registrerStævneresultat() {

    String svarStævneNavn;
    String svarStævneDato;
    int svarMedlemsnummer;
    String stævneDato;
    int placeringSvar;

    System.out.println("Vælg venligst en disciplin og Top-5 hold først:");
        delKonkurrencesvømmere();
        vælgSvømmedisciplinTop5();
        vælgHoldTop5();

    System.out.println("Her er vores konkurrencesvømmere, som skal have registreret deres stævneresultater ");
    ArrayList <Konkurrencesvømmer> stævneResultater = sorterOgUdskrivTop5();
    ArrayList <Konkurrencesvømmer> stævneResultatTemp = new ArrayList<>();

    System.out.println("\nIntast venligst medlemsnummer for medlem fra overnævnte liste:");
    svarMedlemsnummer = scan.nextInt();
    scan.nextLine();
        for (int i = 0; i < stævneResultater.size(); i++) {

            if (stævneResultater.get(i).getMedlemsnummer() == svarMedlemsnummer) {
                konkurrencesvømmer.setMedlemsnummer(stævneResultater.get(i).getMedlemsnummer());
                konkurrencesvømmer.setNavn(stævneResultater.get(i).getNavn());
                konkurrencesvømmer.setAlder(stævneResultater.get(i).getAlder());

                System.out.println("Indtast venligst stævnenavn :");
                svarStævneNavn = scan.nextLine();
                konkurrencesvømmer.setStævneNavn(svarStævneNavn);

                konkurrencesvømmer.setStævneDato(stævneResultater. get(i).getResultatsDato());

                konkurrencesvømmer.setSvømmedisciplin(stævneResultater.get(i).getSvømmedisciplin());

                System.out.println("Indtast venligst placering: ");
                placeringSvar = scan.nextInt();
                konkurrencesvømmer.setPlacering(placeringSvar);

                System.out.println("Indtast venligst resultat i format '00,0' sekunder:");
                konkurrencesvømmer.setSvømmeresultat(scan.nextDouble());


                stævneResultatTemp.add(new Konkurrencesvømmer(konkurrencesvømmer.getMedlemsnummer(),
                    konkurrencesvømmer.getNavn(), konkurrencesvømmer.getAlder(), konkurrencesvømmer.getStævneNavn(),
                    konkurrencesvømmer.getStævneDato(), konkurrencesvømmer.getSvømmedisciplin(),
                    konkurrencesvømmer.getPlacering(), konkurrencesvømmer.getSvømmeresultat()));

                System.out.println(stævneResultatTemp);

               /* System.out.println("Ny resultat er registreret for:  \n" +
                    konkurrencesvømmer.getNavn() + "\nDisciplin: " + konkurrencesvømmer.getSvømmedisciplin() +
                    "\nTid: " + konkurrencesvømmer.getSvømmeresultat());*/
            }
        }

    filhåndtering.uploadStævneFil(stævneResultatTemp);
    stævneResultater.clear();


    }



    public void runTop5(){
        delKonkurrencesvømmere();
        vælgSvømmedisciplinTop5();
        vælgHoldTop5();
        sorterOgUdskrivTop5();
    }


    public void delKonkurrencesvømmere(){
        for (int i = 0; i < filhåndtering.downloadKonkurrencesvømmerResultatFil().size(); i++) {
            if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() < 18) {
                junior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
            } else if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() >= 18) {
                senior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
            }
        }
        filhåndtering.downloadKonkurrencesvømmerResultatFil().clear();

    }


    public void vælgSvømmedisciplinTop5() {
        int svarDisciplin;

        System.out.println("For hvilken svømmedisciplin skal vises bedste 5 resultater (Top 5):  ");
        System.out.println("\nVælg venligst mellem 1 eller 4");
        System.out.println("1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning");

        svarDisciplin = scan.nextInt();
        switch (svarDisciplin) {
            case 1 -> disciplin = "Butterfly";
            case 2 -> disciplin = "Crawl";
            case 3 -> disciplin = "Rygcrawl";
            case 4 -> disciplin = "Brystsvømning";
            default ->  vælgSvømmedisciplinTop5(); //System.out.println("Intast venligst et rigtigt nummer");
        }

    }


    public void vælgHoldTop5 () {

        int svarHold;
            System.out.println("Hvilket hold skal vises resultaterne for?\n1. Junior (under 18 år)\n2. Senior (over 18 år)");
            svarHold = scan.nextInt();
            disciplinArray.clear(); // virker dette her?
            if (svarHold == 1) {

                for (int i = 0; i < junior.size(); i++) {
                    if (junior.get(i).getSvømmedisciplin().equals(disciplin)) {
                        disciplinArray.add(junior.get(i));
                    }
                }
                junior.clear();
                senior.clear();

                if (disciplinArray.size() == 0) {
                System.out.println("Der er ingen konkurrencesvømmere i junior hold med denne disciplin");

                } else {
                    sorterOgUdskrivTop5();
                }
            } else if (svarHold == 2) {
                for (int i = 0; i < senior.size(); i++) {
                    if (senior.get(i).getSvømmedisciplin().equals(disciplin)) {
                        disciplinArray.add(senior.get(i));
                    }
                }
                junior.clear();
                senior.clear();
                if (disciplinArray.size() == 0) {
                    System.out.println("Der er ingen konkurrencesvømmere i senior hold med denne disciplin");

                } else {
                   sorterOgUdskrivTop5();
                }
            }
    }


    public ArrayList<Konkurrencesvømmer> sorterOgUdskrivTop5() {
        String holdJunior = "junior";
        String holdSenior = "senior";
        Collections.sort(disciplinArray, konkurrencesvømmer.resultatEfterDouble);

        int max;
        if (disciplinArray.size() > 5) {
            max = 5;
        } else max = disciplinArray.size();

        if(disciplinArray.size() > 0 && disciplinArray.get(0).getAlder() < 18) {
            // fortæller os hvad for et hold vi har i vores ArrayList
            System.out.println("\nHer er Top-5 " + holdJunior + "svømmere i disciplinen " + disciplin);
        } else { System.out.println("\nHer er Top-5 " + holdSenior + "svømmere i disciplinen " + disciplin);
        }

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n", "Nr.", "Navn", "Alder", "Disciplin",
            "Tid", "Dato");
        System.out.println("--------------------------------------------------------------------------------------");

        for (int i = 0; i < max; i++) {
            System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n",disciplinArray.get(i).getMedlemsnummer(),
                disciplinArray.get(i).getNavn(), disciplinArray.get(i).getAlder(), disciplinArray.get(i).
                    getSvømmedisciplin(), disciplinArray.get(i).getSvømmeresultat(),
                disciplinArray.get(i).getResultatsDato());
           }

        return disciplinArray;
    }


}






