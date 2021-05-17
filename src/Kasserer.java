import java.io.FileNotFoundException;

public class Kasserer {

   // Menu menu = new Menu();

   // Formand formand = new Formand();

  public void beregnKontingent(Medlem medlem) { // equals, contains eller matches?
    if (medlem.getAktivitetsStatus().contains("Aktiv medlemskab") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
    } else if (medlem.getAktivitetsStatus().contains("Aktiv medlemskab") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
    } else if (medlem.getAktivitetsStatus().contains("Aktiv medlemskab") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
    } else if (medlem.getAktivitetsStatus().contains("Passiv medlemskab")) {
      medlem.setKontingent(500);
    }
  }


    public void seKontingentOversigt(Formand formand) {
      System.out.println("Ser kontingentoversigt");
      for (int i = 0; i < formand.getMedlemmer().size(); i++) {
        System.out.println(formand.getMedlemmer().get(i).toString());
      }
    }

}

