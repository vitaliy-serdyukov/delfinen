import java.time.LocalDate;
import java.util.Comparator;

public class Konkurrencesvømmer extends Medlem {

    //----Attributter----
    private String svømmedisciplin;
    private double svømmeresultat;
    private LocalDate resultatsDato = LocalDate.now();


    //----Konstruktøren----
    public Konkurrencesvømmer(int medlemsnummer, String navn, int alder, String svømmedisciplin, double svømmeresultat,
                              LocalDate resultatsDato) {
        super(medlemsnummer,navn, alder);
        this.svømmedisciplin = svømmedisciplin;
        this.svømmeresultat = svømmeresultat;
        this.resultatsDato = resultatsDato;
    }

    //----Overrrider konstruktøren----
    public Konkurrencesvømmer(int medlemsnummer, String navn, int alder) {
        super(medlemsnummer, navn, alder);

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



    /*public Comparator<Konkurrencesvømmer> konkurrencesvømmereEfterNavn = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            return k1.getNavn().compareTo(k2.getNavn());
        }
    };*/

   public Comparator<Konkurrencesvømmer> resultatEfterDouble = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            return Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
        }
    };


    public Comparator<Konkurrencesvømmer> konkurrencesvømmerEfterNavnAlder = new Comparator<Konkurrencesvømmer>() {
        @Override
        public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
            int flag =  k1.getNavn().compareTo(k2.getNavn());
            if (flag == 0) flag = Integer.compare(k1.getAlder(), k1.getAlder());
            return flag;
        }
    };


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
        return  super.getMedlemsnummer() +  super.getNavn() + " " + super.getAlder() + " " + svømmedisciplin + " " +
            svømmeresultat + " " + resultatsDato + "\n";

    }


}



