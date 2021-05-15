import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Konkurrencesvømmer {

    //----Attributter----
    private String navn;
    private int alder;
    private int svarPåAktivitetsstatus;
    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe = new ArrayList<>();
    private ArrayList<Motionist> motionistListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> juniorsvømmerListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> seniorsvømmerListe = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    //----Gettere----
    public ArrayList<Konkurrencesvømmer> getSeniorsvømmerListe() {
        return seniorsvømmerListe;
    }
    public ArrayList<Konkurrencesvømmer> getJuniorsvømmerListe() {
        return juniorsvømmerListe;
    }
    public String getNavn(){
        return navn;
    }
    public int getAlder() {
        return alder;
    }

    //----Konstruktør----
    public Konkurrencesvømmer(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    //----Overrrider konstruktøren----
    public Konkurrencesvømmer() {
    }

    //----Objekter----
    Medlem medlem = new Medlem();
    Motionist motionist = new Motionist();
    Formand formand = new Formand();
    Menu menu = new Menu();

    //----Metoder----
    public void run() throws IOException {
        formand.opretMedlem();
        afgørAktivitetsstatus();
    }

    public void afgørAktivitetsstatus() throws IOException {
        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");
        svarPåAktivitetsstatus = input.nextInt();
        afgørAktivitetsform();
    }

    public void afgørAktivitetsform() throws IOException {

        if (svarPåAktivitetsstatus == 1) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? \nTast 1 for konkurrencesvømmer, 2 for motionist: ");
            int svarPåAktivitetsForm = input.nextInt();

            if (svarPåAktivitetsForm == 1) {
                konkurrencesvømmerListe.add(new Konkurrencesvømmer(formand.getNavn(), formand.getAlder()));
                System.out.println("Ny konkurrencesvømmer registeret: " + formand.getNavn());
                afgørÅrgang();

            } else if (svarPåAktivitetsForm == 2) {
                motionistListe.add(new Motionist(formand.getNavn(), formand.getAlder()));
                System.out.println("Ny motionist registreret: " + formand.getNavn());

                menu.visMenu();
            }
        } else {
            System.out.println("Velkommen til klubben.");
        }
    }

    public void afgørÅrgang() throws IOException {

        for (int i = 0; i < konkurrencesvømmerListe.size(); i++) {
            if (konkurrencesvømmerListe.get(i).getAlder() < 18) {
                juniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Da det nye medlem er under 18, er der registreret en juniorsvømmer: \n" +
                        juniorsvømmerListe.get(i));
                indlæsJuniorsvømmerListe();

            } else if (konkurrencesvømmerListe.get(i).getAlder() >= 18) {
                seniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Da det nye medlem er 18+, er der registreret en seniorsvømmer: \n" +
                        seniorsvømmerListe.get(i));
                indlæsSeniorsvømmerListe();
            }
        }
    }

    public void indlæsJuniorsvømmerListe() throws IOException {

        File file = new File("src/Juniorsvømmerlisten.txt");
        try {
            FileWriter fileWriter= new FileWriter(file, true);
            fileWriter.append("Juniorsvømmer: ");
            for (int i = 0; i < juniorsvømmerListe.size(); i++) {
                fileWriter.write(juniorsvømmerListe.get(i) + "\n");
            }
            fileWriter.close();
            juniorsvømmerListe.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu.visMenu();
    }
    public void indlæsSeniorsvømmerListe() throws IOException {

        File file = new File("src/Seniorsvømmerlisten.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append("Seniorsvømmer: ");
            for (int i = 0; i < seniorsvømmerListe.size(); i++) {
                fileWriter.write(seniorsvømmerListe.get(i) + "\n");
            }
            fileWriter.close();
            seniorsvømmerListe.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        return "Navn og alder: " + navn + ", " + alder + " år";
    }

}
