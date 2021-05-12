import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {

    //----Attributter
    private int alder;
    private String navn;
    private String aktivitetsStatus;
    private ArrayList<Konkurrencesvømmer> konkurrencesvømmerListe;

    //----Konstruktør----
    public Medlem(int alder, String navn, String aktivitetsStatus) {
        this.alder = alder;
        this.navn = navn;
        this.aktivitetsStatus = aktivitetsStatus;
    }

    //----Overrrider konstruktøren----
    public Medlem() {
    }

    //----Gettere----
    public ArrayList<Konkurrencesvømmer> getKonkurrencesvømmerListe() {
        return konkurrencesvømmerListe;
    }

    public String getNavn() {
        return navn;
    }

    //----Metoder----
    public void afgørAktivitetsform(){
        Scanner input = new Scanner(System.in);
        System.out.println("Hvad er din aktivitetsstatus? (aktiv/passiv): ");
        String aktivitetSvar = input.nextLine();
        if (aktivitetSvar.equals("aktiv")) {
            System.out.println("Hvad for en aktivitetsform? (motionist/konkurrencesvømmer): ");
            String aktivitetsForm = input.nextLine();
            if (aktivitetsForm.equals("konkurrencesvømmer")){
                konkurrencesvømmerListe.add(new Konkurrencesvømmer(navn, 21));
                System.out.println("Ny konkurrencesvømmer tilføjet");

            } else if (aktivitetsForm.equals("motionist")){
                System.out.println("Nt");
            }
        }
    }

}
