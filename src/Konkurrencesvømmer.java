import java.util.ArrayList;

public class Konkurrencesvømmer {

    //----Attributter----
   private String navn;

    public int getAlder() {
        return alder;
    }

    private int alder;


   //----Gettere----


    //----Konstruktør----
   public Konkurrencesvømmer(String navn, int alder){
       this.navn = navn;
       this.alder = alder;
   }

   //----Overrrider konstruktøren----
    public Konkurrencesvømmer(){
    }

    @Override
    public String toString() {
        return navn;
    }

}
