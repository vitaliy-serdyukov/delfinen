import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {

    Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();

    public int getAlder() {
        return alder;
    }

    //----Attributter
    private int alder;
    private String navn;
    //private String aktivitetsStatus;
    private int svarPåAktivitetsstatus;
    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe = new ArrayList<>();
    private ArrayList<Motionist> motionistListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> juniorsvømmerListe = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> seniorsvømmerListe = new ArrayList<>();

    //----Konstruktør----
    public Medlem(String navn, int alder) {
        this.alder = alder;
        this.navn = navn;
    }

    //----instantierer scanneren----
    Scanner input = new Scanner(System.in);

    //----Overrrider konstruktøren----
    public Medlem() {
    }

    //----Gettere----

    public String getNavn() {
        return navn;
    }

    //----Metoder----
    public void run(){
        registrerStamoplysninger();
    }

    public void registrerStamoplysninger(){
        System.out.println("Hvad er dit navn?: ");
        navn = input.nextLine();
        System.out.println("Hvad er din alder?: ");
        alder = input.nextInt();
        new Medlem(navn, alder);
        afgørAktivitetsstatus();
    }

    public void afgørAktivitetsstatus() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hvad er din aktivitetsstatus? (aktiv tast 1/passiv tast 2): ");
        svarPåAktivitetsstatus = input.nextInt();
        afgørAktivitetsform();
    }

    public void afgørAktivitetsform(){
        if (svarPåAktivitetsstatus == 1) {
            System.out.println("Hvad for en aktivitetsform? (Konkurrencesvømmer tast 1/Motionist tast 2): ");
            int svarPåAktivitetsForm = input.nextInt();

            if (svarPåAktivitetsForm == 1){
                konkurrencesvømmerListe.add(new Konkurrencesvømmer(navn, alder));
                System.out.println("Ny konkurrencesvømmer tilføjet");

            } else if (svarPåAktivitetsForm == 2){
                motionistListe.add(new Motionist(navn, alder));
                System.out.println("Ny motionist tilføjet");
            }
        } else {
            System.out.println("Velkommen til klubben.");
        }
        afgørÅrgang();
    }

    public void afgørÅrgang() {
        for (int i = 0; i < konkurrencesvømmerListe.size(); i++) {
            if (konkurrencesvømmerListe.get(i).getAlder() < 18) {
                juniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Junior svømmer tilføjet!");
            } else if (alder >= 18) {
                seniorsvømmerListe.add(konkurrencesvømmerListe.get(i));
                System.out.println("Senior svømmer tilføjet!");
            }
        }
    }

}
