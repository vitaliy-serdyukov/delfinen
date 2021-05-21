public class Medlem {

    private String navn;
    private int alder;
    private String aktivitetsstatus;
    private String aktivitetsForm;
    private int kontingent;
    private int kontingentForRestenAfÅret;
    private boolean betalt;


    public Medlem(String navn, int alder, String aktivitetsstatus, String aktivitetsform, int kontingent,
                  int kontingentForRestenAfÅret, boolean betalt) {
        this.navn = navn;
        this.alder = alder;
        this.aktivitetsstatus = aktivitetsstatus;
        this.aktivitetsForm = aktivitetsform;
        this.kontingent = kontingent;
        this.kontingentForRestenAfÅret = kontingentForRestenAfÅret;
        this.betalt = betalt;
       // harBetalt();
    }

    public Medlem(){
    }

    public Medlem(String navn, int alder){ // konstruktør, som bliver brugt i Konkurrencesvømmer
        this.navn = navn;
        this.alder = alder;
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

    public int getKontingentForRestenAfÅret() {
        return kontingentForRestenAfÅret;
    }

    public int getKontingent() {
        return kontingent;
    }

    public boolean getBetalt() {
        return betalt;
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

    public void setKontingentForRestenAfÅret(int kontingentForRestenAfÅret) {
        this.kontingentForRestenAfÅret = kontingentForRestenAfÅret;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = betalt;
    }

    @Override
    public String toString() {

        String betaltStr;
        if (betalt){
            betaltStr = "Betalt"; // sådan, så er der ikke mere true eller false :)
        }
        else betaltStr = "Restance";


        return  "Medlem: "  + navn + " " +  alder + " år" +   " " +  aktivitetsstatus   +  " " + aktivitetsForm +
            " " + "Kontingent " + kontingent + " kr." + kontingentForRestenAfÅret + " kr. " + betaltStr + "\n";
    }
}

