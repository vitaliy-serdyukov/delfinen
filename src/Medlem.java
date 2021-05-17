

public class Medlem {
    private String navn;
    private int alder;
    private String aktivitetsstatus;
    private String aktivitetsForm;
    private int kontingent;

    public Medlem(String navn, int alder, String aktivitetsstatus, String aktivitetsForm, int kontingent){
        this.navn = navn;
        this.alder = alder;
        this.aktivitetsstatus = aktivitetsstatus;
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

    public String getAktivitetsstatus() {
        return aktivitetsstatus;
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
        this.aktivitetsstatus = aktivitetsstatus;
    }

    public void setAktivitetsForm(String aktivitetsForm) {
        this.aktivitetsForm = aktivitetsForm;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }


    @Override
    public String toString() {
<<<<<<< HEAD
        return  navn + ", " +  alder + " år" +   ", " + aktivitetsstatus +  ", " + aktivitetsForm +
=======
        return navn + ", " +  alder + " år" +   ", " +  aktivitetsStatus   +  ", " + aktivitetsForm +
>>>>>>> e36bc1e3ed5414b2fdb1316ddd89223e499dc77d
        ", " + "Kontingent " + kontingent + " kr.";
    }
}


