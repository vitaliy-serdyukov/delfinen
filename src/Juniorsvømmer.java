public class Juniorsvømmer {
    private String navn;
    private int alder;

   public Juniorsvømmer(Konkurrencesvømmer konkurrencesvømmer){

        this.navn = konkurrencesvømmer.getNavn();
        this.alder  = konkurrencesvømmer.getAlder();
    }

    public Juniorsvømmer(){
    }

    public String getNavn(){
        return navn;
    }

    public int getAlder() {return alder;}

    @Override
    public String toString() {
    // return super.toString();
     return navn + alder;
    }
}
