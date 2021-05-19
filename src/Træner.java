import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Træner {

    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe = new ArrayList<>();

    private ArrayList<Motionist> motionistListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> juniorsvømmerListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> seniorsvømmerListe = new ArrayList<>();

    //----Gettere----
    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerListe() {
        return konkurrencesvømmerListe;
    }
    public ArrayList<Motionist> getMotionistListe() {
        return motionistListe;
    }

    //----Settere----
    public void setKonkurrencesvømmerListe(ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe) {
        this.konkurrencesvømmerListe = konkurrencesvømmerListe;
    }

    public void visJuniorsvømmerFil() {
        ArrayList<String> downloadJuniorfil = new ArrayList<>();
        try {
            File fileRead = new File("src/Medlemliste.txt");

            Scanner fileReader = new Scanner(fileRead);

            while (fileReader.hasNextLine()) {

                downloadJuniorfil.add(fileReader.nextLine() + "\n");

            } //TODO Fiks mellemrum på første linje ved "Medlem" når man printer filen ovenpå
            System.out.println(downloadJuniorfil.toString().replaceAll("\\[", "").replaceAll("]", ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void afgørHoldEfterÅrgang() {
        for (int i = 0; i < konkurrencesvømmerListe.size(); i++) {
            if (konkurrencesvømmerListe.get(i).getAlder() < 18) {
                juniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Da det nye medlem er under 18, er der registreret en juniorsvømmer: \n" +
                        konkurrencesvømmerListe.get(i));
                uploadJuniorsvømmerFil();
                juniorsvømmerListe.clear();

            } else if (konkurrencesvømmerListe.get(i).getAlder() >= 18) {
                seniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Da det nye medlem er 18+, er der registreret en seniorsvømmer: \n" +
                        konkurrencesvømmerListe.get(i));
                uploadSeniorsvømmerFil();
                seniorsvømmerListe.clear();
            }
        }
    }


    public void uploadJuniorsvømmerFil(){

        File file = new File("src/Juniorsvømmerlisten.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            //fileWriter.append("Juniorsvømmer: ");
            for (int i = 0; i < juniorsvømmerListe.size(); i++) {
                fileWriter.write(juniorsvømmerListe.get(i).getNavn() + "\n" + juniorsvømmerListe.get(i).getAlder() + "\n");
            }
            fileWriter.close();
            //juniorsvømmerListe.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadSeniorsvømmerFil(){

        File file = new File("src/Seniorsvømmerlisten.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            //fileWriter.append("Seniorsvømmer: ");
            for (int i = 0; i < seniorsvømmerListe.size(); i++) {
                fileWriter.write(seniorsvømmerListe.get(i).getNavn() + "\n" + seniorsvømmerListe.get(i).getAlder() + "\n");
            }
            fileWriter.close();
            //seniorsvømmerListe.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadJuniorsvømmerFil(){
        ArrayList<Konkurrencesvømmer> juniorsvømmerFil = new ArrayList<>();

        File file = new File("src/Juniorsvømmerlisten.txt");
        try {
            Scanner fileReader = new Scanner(file);

            if (fileReader.hasNextLine()) {
                while (fileReader.hasNextLine()) {

                    String temp;
                    String navn;
                    int alder;

                    temp = fileReader.nextLine();
                    navn = temp;

                    temp = fileReader.nextLine();
                    alder = Integer.parseInt(temp);

                    juniorsvømmerFil.add(new Konkurrencesvømmer(navn,alder));

                }
                System.out.println("Juniorsvømmer: ");
                System.out.println(juniorsvømmerFil.toString().replaceAll("\\[","").
                        replaceAll("]", "").replaceAll(", ", ""));

            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadSeniorsvømmerFil() {
        ArrayList<Konkurrencesvømmer> seniorsvømmerFil = new ArrayList();

        File file = new File("src/Seniorsvømmerlisten.txt");
        try {
            Scanner fileReader = new Scanner(file);
            if (fileReader.hasNextLine()) {
                while (fileReader.hasNextLine()) {

                    String temp;
                    String navn;
                    int alder;

                    temp = fileReader.nextLine();
                    navn = temp;

                    temp = fileReader.nextLine();
                    alder = Integer.parseInt(temp);

                    seniorsvømmerFil.add(new Konkurrencesvømmer(navn,alder));

                }
                System.out.println("Seniorsvømmer: ");
                System.out.println(seniorsvømmerFil.toString().replaceAll("\\[","").
                        replaceAll("]", "").replaceAll(", ", ""));
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}