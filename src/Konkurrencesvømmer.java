import java.util.ArrayList;

public class Konkurrencesvømmer {

    //----Attributter----
   private String navn;
   private int alder;
   private ArrayList<Konkurrencesvømmer> juniorsvømmerListe;
   private ArrayList<Konkurrencesvømmer> seniorsvømmerListe;
   private


   //----Gettere----
   int getAlder() {
       return alder;
   }

    //----Konstruktør----
   public Konkurrencesvømmer(String navn, int alder){
       this.navn = navn;
   }

   //----Overrrider konstruktøren----
    public Konkurrencesvømmer(){

    }

   Medlem medlem = new Medlem();

    @Override
    public String toString() {
        return navn;
    }

    public void afgørÅrgang() {
        for (int i = 0; i < medlem.getKonkurrencesvømmerListe().size(); i++) {
            if (medlem.getKonkurrencesvømmerListe().get(i).getAlder() < 18) {
                juniorsvømmerListe.add(medlem.getKonkurrencesvømmerListe().get(i));
            } else if (alder >= 18) {
                seniorsvømmerListe.add(medlem.getKonkurrencesvømmerListe().get(i));
            }
        }
        System.out.println(juniorsvømmerListe.toString());
        System.out.println(seniorsvømmerListe.toString());
    }
}
