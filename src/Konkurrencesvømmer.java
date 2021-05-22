import java.time.LocalDate;
import java.util.Comparator;

public class Konkurrencesvømmer extends Medlem {

    //----Attributter----
    private String svømmedisciplin;
    private double svømmeresultat;
    private LocalDate resultatsDato = LocalDate.now();


    //----Konstruktøren----
    public Konkurrencesvømmer(String navn, int alder, String svømmedisciplin, double svømmeresultat,
                              LocalDate resultatsDato) {
        super(navn, alder);
        this.svømmedisciplin = svømmedisciplin;
        this.svømmeresultat = svømmeresultat;
        this.resultatsDato = resultatsDato;
    }

    //----Overrrider konstruktøren----
    public Konkurrencesvømmer(String navn, int alder) {
        super(navn, alder);

    }

    public Konkurrencesvømmer() {
    }



    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public void setSvømmedisciplin(String svømmedisciplin) {
        this.svømmedisciplin = svømmedisciplin;
    }

    public double getSvømmeresultat() {
        return svømmeresultat;
    }

    public void setSvømmeresultat(double svømmeresultat) {
        this.svømmeresultat = svømmeresultat;
    }

    public LocalDate getResultatsDato() {
        return resultatsDato;
    }

    public void setResultatsDato() {
    }

    public void setResultatsDato(LocalDate resultatsDato) {
        this.resultatsDato = resultatsDato;
    }



    public Comparator<Konkurrencesvømmer> konkurrencesvømmereEfterNavn = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            return k1.getNavn().compareTo(k2.getNavn());
        }
    };

   public Comparator<Konkurrencesvømmer> resultatEfterDouble = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            return Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
        }
    };


    @Override
    public String toString() {
        return  super.getNavn() + " " + super.getAlder() + " " + svømmedisciplin + " " + svømmeresultat + " " +
             resultatsDato + "\n";

    }


}



