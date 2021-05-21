import java.util.Comparator;

public class SammelignEfterNavnOgResultat implements Comparator<Konkurrencesvømmer> {


  @Override
  public int compare(Konkurrencesvømmer k1, Konkurrencesvømmer k2) {
    int flag = k1.getNavn().compareTo(k2.getNavn());

    if (flag == 0) flag = Double.compare(k1.getSvømmeresultat(), k2.getSvømmeresultat());
    return flag;

  }

}




