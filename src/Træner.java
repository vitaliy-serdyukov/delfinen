import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Træner {

    // ----Variabler--------------
      private ArrayList<Konkurrencesvømmer> KonkurrencesvømmerResultat = new ArrayList<>();  // Konkurrencesvømmere med resultat

    // -----Objekter--------------
    private Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
    private Filhåndtering filhåndtering = new Filhåndtering();



    //----Gettere----

    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerResultat() {
        return KonkurrencesvømmerResultat;
    }


    public void printKonkurrencesvømmer(Medlem medlem) {

    ArrayList <Medlem> konkurrencesvømmere  = new ArrayList<>();
        for (int i = 0; i < filhåndtering.downloadMedlemsliste().size();i++){
            if (filhåndtering.downloadMedlemsliste().get(i).getAktivitetsForm().equals("Konkurrencesvømmer")){
                konkurrencesvømmere.add(filhåndtering.downloadMedlemsliste().get(i));
            }
        }
        Collections.sort(konkurrencesvømmere, medlem.medlemmerEfterNavn);

        System.out.println("Vi har følgende konkurrencesvømmere i vores klub:\n ");

        System.out.printf(" %-10s %-15s %-10s\n", "Medlems-",
            "Navn", "Alder");
        System.out.printf("\033[4m %-10s %-15s %-10s \033[0m\n", "nummer","","");

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
        Scanner scan = new Scanner(System.in);

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
                } // validering

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

              //  udskiftTilEnBedreResultat();
            } else {
                    /*do {
                        System.out.println("Navnet er indtastet forkert eller ikke fundet i junior liste");
                        svarNavn = scan.nextLine();

                    } while (!(downloadJuniorsvømmerFil().get(i).getNavn().equals(svarNavn)));*//*
                // TO DO VALIDERING
*/
            }
        }
    }

    /*public void udskiftTilEnBedreResultat(){

        ArrayList<Konkurrencesvømmer> bedsteResultat = filhåndtering.downloadKonkurrencesvømmerResultatFil();

        System.out.println("Test1" + bedsteResultat);

        Collections.sort(bedsteResultat,konkurrencesvømmer.konkurrencesvømmerEfterNavnDisciplinResultat);

        System.out.println("Test2" + bedsteResultat);

            if (bedsteResultat.size() > 1) {
                for (int j = 0; j < bedsteResultat.size(); j++) { // fjerner værste sesultat, hvis en medlem har flere
                    for (int k = j + 1; j < bedsteResultat.size(); j++) {

                        if (bedsteResultat.get(j).getMedlemsnummer() == bedsteResultat.get(k).
                            getMedlemsnummer()) {
                            if (bedsteResultat.get(j).getSvømmedisciplin().
                                equals(bedsteResultat.get(k).getSvømmedisciplin())){
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

            } else {

                System.out.println("Test3" + bedsteResultat);

                try {
                    new FileWriter("src/KonkurrencesvømmerResultat.txt", false).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                filhåndtering.uploadKonkurrencesvømmerResultatFil(bedsteResultat);
                bedsteResultat.clear();
            }
    }*/




     /*public void afgørHoldEfterÅrgang() {
        for (int i = 0; i < konkurrencesvømmerListe.size(); i++) {
            if (konkurrencesvømmerListe.get(i).getAlder() < 18) {
                System.out.println("Da det nye medlem er under 18, er der registreret en juniorsvømmer: \n" +
                    konkurrencesvømmerListe.get(i));
            } else if (konkurrencesvømmerListe.get(i).getAlder() >= 18) {
                System.out.println("Da det nye medlem er 18+, er der registreret en seniorsvømmer: \n" +
                    konkurrencesvømmerListe.get(i));
            }
        }
    }*/

    ArrayList<Konkurrencesvømmer> junior = new ArrayList<>();
    ArrayList<Konkurrencesvømmer> senior = new ArrayList<>();
    String disciplin;

    public void findTopFem() {

        Scanner scan = new Scanner(System.in);
        int svarHold;
        int svarDisciplin;


        for (int i = 0; i < filhåndtering.downloadKonkurrencesvømmerResultatFil().size(); i++) {
            if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() < 18) {
                junior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
            } else if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() >= 18) {
                senior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
            }

        }
        System.out.println("For hvilken svømmedisciplin skal vises bedste 5 resultater (Top 5):  ");
        System.out.println("\nVælg venligst mellem 1 eller 4");
        System.out.println("1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning");

        svarDisciplin = scan.nextInt();
        ArrayList<Konkurrencesvømmer> disciplinArray = new ArrayList<>();

        switch (svarDisciplin) {
            case 1 -> disciplin = "Butterfly";
            case 2 -> disciplin = "Crawl";
            case 3 -> disciplin = "Rygcrawl";
            case 4 -> disciplin = "Brystsvømning";
            default -> scan.nextLine(); // validering her
        }
        System.out.println("Hvilket hold skal vises resultaterne for?\n1. Junior (under 18 år)\n2. Senior (over 18 år)");
        svarHold = scan.nextInt();

        if (svarHold == 1) {
            for (int i = 0; i < junior.size(); i++) {
                if (junior.get(i).getSvømmedisciplin().equals(disciplin)) {
                    disciplinArray.add(junior.get(i));

                    // sorterSvømmereOgPrintTop5();
                }
            }
            System.out.println(junior);

        } else if (svarHold == 2) {

        } else {
        } // validate her*/

    }
}
        /*public void sorterSvømmereOgPrintTop5 () {

            Collections.sort(disciplinArray, konkurrencesvømmer.resultatEfterDouble);
            System.out.println(disciplinArray);

            Collections.sort(disciplinArray, new SammelignEfterNavnOgResultat());// sorterer efter resultat, med forbehold
            // at den samme medlem har flere resultater

        }
        System.out.println(butterfly);

        for (
            int i = 0; i < butterfly.size(); i++) {             // fjerner værste sesultat, hvis en medlem har flere
            for (int j = i + 1; j < butterfly.size(); j++) {

                if (butterfly.get(i).getNavn().equals(butterfly.get(j).getNavn())) {
                    System.out.println("Test");
                    butterfly.remove(j);
                    j--;
                }
            }

        }
        System.out.println(butterfly);

        System.out.println("\nFinally vores top 5 i batterfly:\n");

        for (
            int i = 0;
            i < 5; i++) {
            System.out.println(butterfly.get(i)); // fejler hvis der færre end 5 medlemer i listen


        }
    }*/

/*
&&
    fh2.downloadKonkurrencesvømmerFil().get(i).getAlder() < 18){}*/




