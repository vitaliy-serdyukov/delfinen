

public class Medlem {
    private String navn;
    private int alder;
    private int aktivitetsStatus;
    private int aktivitetsForm;
    private int kontingent;

    public Medlem(String navn, int alder, int aktivitetsstatus, int aktivitetsForm, int kontingent){
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

    public int getAktivitetsstatus() {return aktivitetsStatus;}


    public int getAktivitetsStatus() {
        return aktivitetsStatus;
    }

    public int getAktivitetsForm() {
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

    public void setAktivitetsstatus(int aktivitetsstatus) {
        this.aktivitetsStatus = aktivitetsstatus;
    }

    public void setAktivitetsForm(int aktivitetsForm) {
        this.aktivitetsForm = aktivitetsForm;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }


    @Override
    public String toString() {
        return  navn + ", " +  alder +  ", " +  aktivitetsStatus   +  ", " + aktivitetsForm +
        ", " + kontingent + " kr.";
    }
}


