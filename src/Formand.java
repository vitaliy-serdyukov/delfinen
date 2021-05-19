import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private int svarPåAktivitetsStatus;
    private int svarPåAktivitetsForm;
    Scanner input = new Scanner(System.in);


    //----Lister----
    private ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Objekter----
    Medlem medlem = new Medlem();
    Kasserer kasserer = new Kasserer();
    Træner træner = new Træner();

    //----Konstruktøren----
    public Formand() {
    }

    // ---------Gettere----------
    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }

    public int getSvarPåAktivitetsStatus() {
        return svarPåAktivitetsStatus;
    }

    //----Metoder----
    public void run(){
        registrerStamoplysninger();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        opretMedlem();
        indlæsMedlemListe();



       //new Konkurrencesvømmer().afgørKonkurrencesvømmer(medlem);
       // new Træner().afgørHoldEfterÅrgang(medlem);
    }


    public void opretMedlem(){
        medlemmer.add(new Medlem(medlem.getNavn(), medlem.getAlder(), medlem.getAktivitetsstatus(),
            medlem.getAktivitetsForm(), medlem.getKontingent()));
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
        }
        else if (svarPåAktivitetsStatus == 2)  {
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
                System.out.println("Velkommen til klubben! " + medlem.getNavn());
                træner.afgørHoldEfterÅrgang();


                input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
            } else if (svarPåAktivitetsForm == 2) {
                medlem.setAktivitetsForm("Motionist");
                System.out.println("Ny motionist registreret: " + medlem.getNavn());
                træner.getMotionistListe().add(new Motionist(medlem.getNavn(), medlem.getAlder()));
                System.out.println("Velkommen til klubben!");
                input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
            }
        } else if (medlem.getAktivitetsstatus().contains("Passiv")){
            medlem.setAktivitetsForm("Passiv");
            input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
        }

    }

    public void seMedlemmer() {

        System.out.printf("%-10s %n", uploadMedlemListe().toString().replaceAll("\\[","").
            replaceAll("]", "").replaceAll(" , "
            , ""));

        /*ArrayList<String> downloadMedlemmer = new ArrayList<>();
        System.out.println("Medlemmer");
        try {
            File fileRead = new File("src/Medlemliste.txt");

            Scanner fileReader = new Scanner(fileRead);

            while (fileReader.hasNextLine()) {

                downloadMedlemmer.add(fileReader.nextLine()+ "\n");

            } //TODO Fiks mellemrum på første linje ved "Medlem" når man printer filen ovenpå
            System.out.println(downloadMedlemmer.toString()*//*.replaceAll(". , M" , " M").replaceAll("\\[", "").replaceAll("]", "")*//*);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    public void indlæsMedlemListe() {
        File file = new File("src/Medlemliste.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
          //  fileWriter.write("\nMedlem: \n");
            for (int i = 0; i < medlemmer.size(); i++) {
                fileWriter.write(medlemmer.get(i).getNavn() + "\n" +
                        medlemmer.get(i).getAlder() + "\n" +
                        medlemmer.get(i).getAktivitetsstatus() + "\n" +
                        medlemmer.get(i).getAktivitetsForm() + "\n" +
                        medlemmer.get(i).getKontingent() + "\n");
            }
            fileWriter.close();
            medlemmer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  menu.visMenu();
    }

    public  ArrayList<Medlem>  uploadMedlemListe() {

        ArrayList<Medlem> medlemList = new ArrayList<>();
        File file = new File("src/Medlemliste.txt");
        try {
            Scanner fileReader = new Scanner(file);

            if (fileReader.hasNextLine()) {
                while (fileReader.hasNextLine()) {

                    String temp;
                    String navn;
                    int alder;
                    String aktivitetsStatus;
                    String aktivitetsForm;
                    int kontingent;

                    temp = fileReader.nextLine();
                    navn = temp;

                    temp = fileReader.nextLine();
                    alder = Integer.parseInt(temp);

                    temp = fileReader.nextLine();
                    aktivitetsStatus = temp;

                    temp = fileReader.nextLine();
                    aktivitetsForm = temp;

                    temp = fileReader.nextLine();
                    kontingent = Integer.parseInt(temp);

                    medlemList.add(new Medlem(navn,alder,aktivitetsStatus,aktivitetsForm,kontingent));

                }
            }

            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return medlemList;
    }
}









