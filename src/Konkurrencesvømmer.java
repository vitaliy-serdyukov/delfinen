import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Konkurrencesvømmer {

    //----Attributter----
   private String navn;
   private int alder;
   private int svarPåAktivitetsstatus;
   private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe = new ArrayList<>();
   private ArrayList<Motionist> motionistListe = new ArrayList<>();
   private ArrayList<Juniorsvømmer> juniorsvømmerListe = new ArrayList<>();
   private ArrayList<Seniorsvømmer> seniorsvømmerListe = new ArrayList<>();
   Scanner input = new Scanner(System.in);

// ----Settere---


//----Gettere----

  public int getAlder() {
  return alder;
}

  public String getNavn() {
    return navn;
  }

    //----Konstruktør----
   public Konkurrencesvømmer(String navn, int alder){
       this.navn = navn;
       this.alder = alder;
   }

   //----Overrrider konstruktøren----
    public Konkurrencesvømmer(){
    }

    //----Objekter----
    Medlem medlem = new Medlem();
    Motionist motionist = new Motionist();
    Formand formand = new Formand();

    //----Metoder----

    public void run(){
        formand.opretMedlem();
        afgørAktivitetsstatus();
    }
    public void afgørAktivitetsstatus(){
        System.out.println("Vil du være aktivt eller passivt medlem? \nTast 1 for aktiv, 2 for passiv: ");
        svarPåAktivitetsstatus = input.nextInt();
        afgørAktivitetsform();
    }

    public void afgørAktivitetsform(){

        if (svarPåAktivitetsstatus == 1) {
            System.out.println("Hvad for en aktivitetsform er du interesseret i? \nTast 1 for konkurrencesvømmer, 2 for motionist: ");
            int svarPåAktivitetsForm = input.nextInt();

            if (svarPåAktivitetsForm == 1){
                konkurrencesvømmerListe.add(new Konkurrencesvømmer(formand.getNavn(), formand.getAlder()));
                System.out.println("Ny konkurrencesvømmer registeret: " + formand.getNavn());


            } else if (svarPåAktivitetsForm == 2){
                motionistListe.add(new Motionist(formand.getNavn(), formand.getAlder()));
                System.out.println("Ny motionist registreret: " + formand.getNavn());
            }
        } else {
            System.out.println("Velkommen til klubben.");
        }
     // System.out.println(konkurrencesvømmerListe.toString() + "test Konkurrence liste"); test
        afgørÅrgang();
    }

    public void afgørÅrgang() {

        for (int i = 0; i < konkurrencesvømmerListe.size(); i++) {
            if (konkurrencesvømmerListe.get(i).getAlder() < 18) {
                juniorsvømmerListe.add(new Juniorsvømmer(konkurrencesvømmerListe.get(i)));
                System.out.println("Da det nye medlem er under 18, er der registreret en juniorsvømmer.");
            } else if (alder >= 18) {
                seniorsvømmerListe.add(new Seniorsvømmer(konkurrencesvømmerListe.get(i)));
                System.out.println("Da det nye medlem er 18+, er der registreret en seniorsvømmer.");
            }
        }
     // System.out.println(juniorsvømmerListe.toString() + "test Junior liste"); test

      indlæsJuniorsvømmerListe();
    }

    public void indlæsJuniorsvømmerListe() {
        File file = new File("src/JuniorsvømmerListe.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Juniorsvømmere: \n"); // printer kun den ene gang i toppen a filen i stedet  for flere
            for (int i = 0; i < juniorsvømmerListe.size(); i++) {

                fileWriter.write(juniorsvømmerListe.get(i).getNavn() + "\n");
                fileWriter.write(juniorsvømmerListe.get(i).getAlder() + "\n");

            }
            fileWriter.close();
        } catch(IOException e){
            e.printStackTrace();

        }
    }

    @Override
    public String toString() {
        return navn;
    }

}
