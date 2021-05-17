import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private int svarPåAktivitetsStatus;
    private int svarPåAktivitetsForm;
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
    public void run(){

        registrerNavnOgAlder();
        registrerAktivitetsstatus();
        registrerAktivitetsform();
        kasserer.beregnKontingent(medlem);
        opretMedlem();
        indlæsmedlemListe();

       // seMedlemmer();

        new Konkurrencesvømmer().afgørKonkurrencesvømmer(medlem);


    }

    public void opretMedlem(){

        medlemmer.add(new Medlem(medlem.getNavn(), medlem.getAlder(), medlem.getAktivitetsStatus(),
            medlem.getAktivitetsForm(), medlem.getKontingent()));
    }

    public void registrerNavnOgAlder(){

        System.out.println("Hvad er dit navn?: ");
        medlem.setNavn(input.nextLine());
        System.out.println("Hvad er din alder?: ");
        medlem.setAlder(input.nextInt());
    }

    public void registrerAktivitetsstatus(){
        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");
        svarPåAktivitetsStatus =  input.nextInt();
        if (svarPåAktivitetsStatus == 1){
            medlem.setAktivitetsstatus("Aktiv medlemskab");
        }
        else if (svarPåAktivitetsStatus == 2)  {
            medlem.setAktivitetsstatus("Passiv medlemskab");
            System.out.println("Velkommen til klubben!");
        } // UI, input validering her

    }

    public void registrerAktivitetsform(){
        if (medlem.getAktivitetsStatus().contains("Aktiv medlemskab")) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? \nTast 1 for konkurrencesvømmer, 2 for motionist: ");
            svarPåAktivitetsForm = input.nextInt();
            if (svarPåAktivitetsForm == 1) {
                medlem.setAktivitetsForm("Konkurrencesvømmer");
                System.out.println("Ny konkurrencesvømmer registeret");
                System.out.println("Velkommen til klubben!");
                input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
            } else if (svarPåAktivitetsForm == 2) {
                medlem.setAktivitetsForm("Motionist");
                System.out.println("Ny motionist registreret:");
                System.out.println("Velkommen til klubben!");
                input.nextLine(); // forebygger scanner bug ved oprettelsen af de nye medlemmer
            }
        } else if (medlem.getAktivitetsStatus().contains("Passiv medlemskab")){
            medlem.setAktivitetsForm("Passiv medlemskab");
        }

    }

    public void seMedlemmer() {
        System.out.println("Ser medlemmer");
        for (int i = 0; i < medlemmer.size(); i++ ){
            System.out.println(medlemmer.get(i).toString().replaceAll("\\[", "").replaceAll("]", ""));
        }
    }

    public void indlæsmedlemListe() {

        File file = new File("src/Medlemliste.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append("\nMedlem: ");
            for (int i = 0; i < medlemmer.size(); i++) {
                fileWriter.write(medlemmer.get(i) + "\n");
            }
            fileWriter.close();
            medlemmer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  menu.visMenu();
    }

    /*public ArrayList<Medlem> udlæsmedlemListe(){
        File file = new File("src/Medlemliste.txt");
        ArrayList<Medlem> medlemListe = new ArrayList<>();
        try{
            Scanner fileReader = new Scanner(file);
            if (fileReader.hasNextLine()) {
                while (fileReader.hasNextLine()){
            String temp;
            String navn;
            int alder;
            String aktivitetsStatus;
            String aktivitetsForm;
            int kontingent;

            temp = fileReader.nextLine().replaceAll("Medlem: ", "").replaceAll( " år", "").
                replaceAll("  kontingent", "").replaceAll(" kr.", "");
            if (!(temp.isEmpty()){
                String[] navnArray = temp.split(",");
                alder = Integer.parseInt(temp);
                String [] statusArray = temp.split(",");
                String [] formArray = temp.split(",");
                String [] kontingentArray = temp.split(",");
                Integer[] kontingentInteger = new Integer[kontingentArray.length];
                    for (int i = 0; i < kontingentArray.length;i++){
                        kontingentInteger[i] = Integer.parseInt(kontingentArray[i]);
                    }
                        Collections.addAll()


                    }

                }
            }
        }

    }*/
}


