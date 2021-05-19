public class Kasserer {

  public void beregnKontingent(Medlem medlem) { // equals, contains eller matches?
    if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
    } else if (medlem.getAktivitetsstatus().contains("Passiv")) {
      medlem.setKontingent(500);
    }
  }

  public void visKontingenter(Formand formand){
    for (int i = 0; i < formand.downloadMedlemsliste().size();i++) {
      System.out.println(formand.downloadMedlemsliste().get(i).getKontingent());
    }
  }

  public void udregnKontingenter(Formand formand) {
    int kontingentSum = 0;
    for (int i = 0; i < formand.downloadMedlemsliste().size(); i++) {
      kontingentSum = kontingentSum + formand.downloadMedlemsliste().get(i).getKontingent();
    }
    System.out.println("Den samlede kontinget for alle medlemer er " + kontingentSum + " kr.");
  }
}

