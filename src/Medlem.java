

public class Medlem {
    private String navn;
    private int alder;
    private String aktivitetsStatus;
    private String aktivitetsForm;
    private int kontingent;

    public Medlem(String navn, int alder, String aktivitetsstatus, String aktivitetsForm, int kontingent){
        this.navn = navn;
        this.alder = alder;
        this.aktivitetsStatus = aktivitetsstatus;
        this.aktivitetsForm = aktivitetsForm;
        this.kontingent = kontingent;
    }

    public Medlem(){
    }

    // ------Gettere----------

    public String getNavn(){
        return navn;
    }

    public int getAlder(){
        return alder;
    }


    public String getAktivitetsStatus() {
        return aktivitetsStatus;
    }

    public String getAktivitetsForm() {
        return aktivitetsForm;
    }

    public int getKontingent() {
        return kontingent;
    }


    // -------Settere----------

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public void setAktivitetsstatus(String aktivitetsstatus) {
        this.aktivitetsStatus = aktivitetsstatus;
    }

    public void setAktivitetsForm(String aktivitetsForm) {
        this.aktivitetsForm = aktivitetsForm;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }


    @Override
    public String toString() {
        return navn + ", " +  alder + " år" +   ", " +  aktivitetsStatus   +  ", " + aktivitetsForm +
        ", " + "Kontingent " + kontingent + " kr.";
    }
}


