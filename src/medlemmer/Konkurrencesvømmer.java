package medlemmer;

import java.time.LocalDate;
import java.util.Comparator;

public class Konkurrencesvømmer extends Medlem {

    //----Attributter----
    private String svømmedisciplin;
    private double svømmeresultat;
    private LocalDate resultatsDato = LocalDate.now();
    private String stævneNavn;
    private int placering;
    // skal være lavet om med henblik til at stævnedato skal kunne være tastet ind manuelt
    private LocalDate stævneDato = LocalDate.now();

    //----Gettere----
    public String getStævneNavn() {
        return stævneNavn;
    }
    public int getPlacering() {
        return placering;
    }

    public LocalDate getStævneDato() {
        return stævneDato;
    }

    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public double getSvømmeresultat() {
        return svømmeresultat;
    }

    public LocalDate getResultatsDato() {
        return resultatsDato;
    }

    //----Settere----
    public void setStævneNavn(String stævneNavn) {
        this.stævneNavn = stævneNavn;
    }

    public void setPlacering(int placering) {
        this.placering = placering;
    }

    public void setStævneDato(LocalDate stævneDato) {
        this.stævneDato = stævneDato;
    }

    public void setSvømmedisciplin(String svømmedisciplin) {
        this.svømmedisciplin = svømmedisciplin;
    }

    public void setSvømmeresultat(double svømmeresultat) {
        this.svømmeresultat = svømmeresultat;
    }

    public void setResultatsDato() {
    }

    public void setResultatsDato(LocalDate resultatsDato) {
        this.resultatsDato = resultatsDato;
    }

    //----Konstruktøren----
    public Konkurrencesvømmer(int medlemsnummer, String navn, int alder, String stævneNavn, LocalDate stævneDato,
                              String svømmedisciplin,int placering, double svømmeresultat) {
        super(medlemsnummer, navn, alder);
        this.stævneNavn = stævneNavn;
        this.stævneDato = stævneDato;
        this.svømmedisciplin = svømmedisciplin;
        this.placering  = placering;
        this.svømmeresultat = svømmeresultat;
    }

    //----Overrider konstruktør----
    public Konkurrencesvømmer(int medlemsnummer, String navn, int alder, String svømmedisciplin, double svømmeresultat,
                              LocalDate resultatsDato) {
        super(medlemsnummer,navn, alder);
        this.svømmedisciplin = svømmedisciplin;
        this.svømmeresultat = svømmeresultat;
        this.resultatsDato = resultatsDato;
    }

    //----Overrrider konstruktøren----
    public Konkurrencesvømmer() {
    }

    // bruges for en brugervenlig visning af konkurrencesvømmere på skærm (punkt 6)
    public Comparator<Konkurrencesvømmer> konkurrencesvømmerEfterNavnAlder = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            int flag =  k1.getNavn().compareTo(k2.getNavn());
            if (flag == 0) flag = Integer.compare(k1.getAlder(), k1.getAlder());
            return flag;
        }
    };

    // bruges for en brugervenlig visning af konkurrencesvømmere med rerultater på skærm (punkt 7)
    public Comparator<Konkurrencesvømmer> konkurrencesvømmerNavnDisciplin = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            int flag = k1.getSvømmedisciplin().compareTo(k2.getSvømmedisciplin());
            if (flag == 0) flag = Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
            return flag;
        }
    };

    // bruges til at sammenligne efter resultat
    public Comparator<Konkurrencesvømmer> sorterResultatEfterDouble = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            return Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
        }
    };


    // bruges i udskiftTilEnBedreResultat() til at sortere brugere med samme navn, svømmedisciplin, men forskellige
    // resultater
    public Comparator<Konkurrencesvømmer> konkurrencesvømmerEfterNavnDisciplinResultat = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            int flag = k1.getNavn().compareTo(k2.getNavn());

            if (flag == 0) flag = k1.getSvømmedisciplin().compareTo(k2.getSvømmedisciplin());

            if (flag == 0) flag = Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
            return flag;
        }
    };

    @Override
    public String toString() {
        return  super.getMedlemsnummer() + " " +  super.getNavn() + " " + super.getAlder() + " " + svømmedisciplin + " " +
            svømmeresultat + " " + resultatsDato + "\n";
    }
}



