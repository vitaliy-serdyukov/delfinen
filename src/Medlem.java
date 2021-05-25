import java.util.Comparator;

public class Medlem {

    private int medlemsnummer;
    private String navn;
    private int alder;
    private String aktivitetsstatus;
    private String aktivitetsForm;
    private int kontingent;
    private double kontingentForRestenAfÅret;
    private boolean betalt;


    public Medlem(int medlemsnummer,String navn, int alder, String aktivitetsstatus, String aktivitetsform,
                  int kontingent, double kontingentForRestenAfÅret, boolean betalt) {
        this.medlemsnummer = medlemsnummer;
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

    public Medlem(int medlemsnummer, String navn, int alder){ // konstruktør, som bliver brugt i Konkurrencesvømmer
        this.medlemsnummer = medlemsnummer;
        this.navn = navn;
        this.alder = alder;
    }


    // ------Gettere----------

    public int getMedlemsnummer() {return medlemsnummer;}

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

    public double getKontingentForRestenAfÅret() {
        return kontingentForRestenAfÅret;
    }

    public int getKontingent() {
        return kontingent;
    }

    public boolean getBetalt() {
        return betalt;
    }



    // -------Settere----------

    public void setMedlemsnummer(int medlemsnummer) {this.medlemsnummer = medlemsnummer;}

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

    public void setKontingentForRestenAfÅret(double kontingentForRestenAfÅret) {
        this.kontingentForRestenAfÅret = kontingentForRestenAfÅret;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = betalt;
    }



    public static Comparator<Medlem> medlemmerEfterNavn = new Comparator<Medlem>() {
        @Override
        public int compare(Medlem m1, Medlem m2) {
           int flag =  m1.getNavn().compareTo(m2.getNavn());
           if (flag == 0) flag = Integer.compare(m1.getAlder(), m2.getAlder());
           return flag;
        }
    };


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

