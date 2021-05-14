public class Motionist {
    private int alder;
    private String navn;

    public Motionist(String navn, int alder){
        this.alder = alder;
        this.navn = navn;
    }

    public Motionist(){

    }

    public String getNavn(){
        return navn;
    }

    public int getAlder(){
        return alder;
    }
}
