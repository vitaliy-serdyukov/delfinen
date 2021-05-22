import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Træner {

    // ----Variabler--------------
    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerResultat = new ArrayList<>();  // Konkurrencesvømmere med resultat

    // -----Objekter--------------
    private Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
    private Filhåndtering fh2 = new Filhåndtering();
    //  private ArrayList<Motionist> motionistListe = new ArrayList<>();


    //----Gettere----
    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerListe() {
        return konkurrencesvømmerListe;
    }

    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerResultat() {
        return konkurrencesvømmerResultat;
    }


    public void printKonkurrencesvømmer(Filhåndtering fh) {
        System.out.println("Vi har følgende konkurrencesvømmere i vores klub:\n ");
        ArrayList<Konkurrencesvømmer> konkurrencesvømmereSorteretNavn = fh.downloadKonkurrencesvømmerFil();
        Collections.sort(konkurrencesvømmereSorteretNavn, konkurrencesvømmer.konkurrencesvømmereEfterNavn);

        System.out.printf("\033[4m %-3s %-15s %-10s\033[0m\n", "Nr.",
            "Navn", "Alder");

        for (int i = 0; i < konkurrencesvømmereSorteretNavn.size(); i++) {

            System.out.printf("\033[4m %-3d %-15s %-10s\033[0m\n", (i + 1),
                konkurrencesvømmereSorteretNavn.get(i).getNavn(),
                konkurrencesvømmereSorteretNavn.get(i).getAlder() + " år");
        }


    }

    public void printResultatKonkurrencesvømmer(Filhåndtering fh) {
        ArrayList<Konkurrencesvømmer> resultaterPåSkærm = fh.downloadKonkurrencesvømmerResultatFil();
        Collections.sort(resultaterPåSkærm, konkurrencesvømmer.konkurrencesvømmereEfterNavn);
        System.out.printf("\033[4m %-3s %-15s %-10s %-15s %-30s %-20s\033[0m\n", "Nr.",
            "Navn", "Alder", "Disciplin", "Resultat", "Dato");

        for (int i = 0; i < resultaterPåSkærm.size(); i++) {

            System.out.printf("\033[4m %-3d %-15s %-10s %-15s %-30s %-20s\033[0m\n", (i + 1),
                resultaterPåSkærm.get(i).getNavn(), resultaterPåSkærm.get(i).getAlder() + " år",
                resultaterPåSkærm.get(i).getSvømmedisciplin(), resultaterPåSkærm.get(i).getSvømmeresultat(),
                resultaterPåSkærm.get(i).getResultatsDato());
        }
    }


    public void registrerSvømmeresultat() {

        String svarNavn;
        int svarDisciplin;
        Scanner scan = new Scanner(System.in);

        System.out.println("Vi har følgende  konkurrencesvømmere i vores klub:\n");
        printKonkurrencesvømmer(fh2);
        System.out.println("\nIntast venligst navn fra overnævnte liste:");
        svarNavn = scan.nextLine();

        for (int i = 0; i < fh2.downloadKonkurrencesvømmerFil().size(); i++) {

            if (fh2.downloadKonkurrencesvømmerFil().get(i).getNavn().equals(svarNavn)) {

                konkurrencesvømmer.setNavn(fh2.downloadKonkurrencesvømmerFil().get(i).getNavn());
                konkurrencesvømmer.setAlder(fh2.downloadKonkurrencesvømmerFil().get(i).getAlder());
                System.out.println("For hvilken svømmedisciplin skal registreres resultat:  ");
                System.out.println("\nVælg venligst mellem 1 eller 4");
                System.out.println("1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning");

                svarDisciplin = scan.nextInt();

                switch (svarDisciplin) {
                    case 1 -> konkurrencesvømmer.setSvømmedisciplin("Butterfly");
                    case 2 -> konkurrencesvømmer.setSvømmedisciplin("Crawl");
                    case 3 -> konkurrencesvømmer.setSvømmedisciplin("Rygcrawl");
                    case 4 -> konkurrencesvømmer.setSvømmedisciplin("Brystsvømning");
                } // validering

                System.out.println("Indtast venligst resultat:");
                konkurrencesvømmer.setSvømmeresultat(scan.nextDouble());
                konkurrencesvømmer.setResultatsDato();

                konkurrencesvømmerResultat.add(new Konkurrencesvømmer(konkurrencesvømmer.
                    getNavn(), konkurrencesvømmer.getAlder(), konkurrencesvømmer.getSvømmedisciplin(),
                    konkurrencesvømmer.getSvømmeresultat(), konkurrencesvømmer.getResultatsDato()));

                fh2.uploadKonkurrencesvømmerResultatFil(konkurrencesvømmerResultat);

                System.out.println("Ny resultat er registreret for: \n");
                System.out.println(konkurrencesvømmerResultat.toString().
                    replaceAll("\\[", "").replaceAll("]", "").
                    replaceAll(", ", ""));

            } else {
                    /*do {
                        System.out.println("Navnet er indtastet forkert eller ikke fundet i junior liste");
                        svarNavn = scan.nextLine();

                    } while (!(downloadJuniorsvømmerFil().get(i).getNavn().equals(svarNavn)));*/
                // TO DO VALIDERING
            }
        }
    }


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


        for (int i = 0; i < fh2.downloadKonkurrencesvømmerResultatFil().size(); i++) {
            if (fh2.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() < 18) {
                junior.add(fh2.downloadKonkurrencesvømmerResultatFil().get(i));
            } else if (fh2.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() >= 18) {
                senior.add(fh2.downloadKonkurrencesvømmerResultatFil().get(i));
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
