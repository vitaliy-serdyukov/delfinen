public class Juniorsvømmer {
    private String navn;
    private int alder;

    public Juniorsvømmer(Konkurrencesvømmer konkurrencesvømmer){
        this.navn = konkurrencesvømmer.getNavn();
        this.alder  = konkurrencesvømmer.getAlder();
    }

    public String navn(){
        return navn;
    }

    public int getAlder() {
        return alder;
    }

    @Override
    public String toString() {
        return navn + " " +  alder;
    }
}
