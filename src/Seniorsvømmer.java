public class Seniorsvømmer {

    private String navn;
    private int alder;


    public Seniorsvømmer(Konkurrencesvømmer konkurrencesvømmer){
        this.navn = konkurrencesvømmer.getNavn();
        this.alder = konkurrencesvømmer.getAlder();
    }

    public String getNavn() {
        return navn;
    }

    public int getAlder() {
        return alder;
    }

    @Override
    public String toString() {
       // return super.toString(); // ??? hvem er superklassen her?
        return navn + ", " + alder;
    }
}
