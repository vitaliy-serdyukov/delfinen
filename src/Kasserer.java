import java.util.ArrayList;

public class Kasserer {
  ArrayList<Integer> kontingenter = new ArrayList<>();



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

  //TODO Hent KUN kontingent fra filen og smid det ind i en int ArrayListe som vi kan bruge i næste metode og udregne årlig
   /* public void findKontingenterFraMedlemsliste() {
      try {
        File fileRead = new File("src/Medlemliste.txt");

        Scanner fileReader = new Scanner(fileRead);
        //String kontingenter = fileReader.nextLine();
        kontingenter.clear();
        while (fileReader.hasNextLine()) {
          if (fileReader.nextLine().equals("Aktiv") || fileReader.nextLine().equals("Passiv")) {

            kontingenter.add(Integer.parseInt(fileReader.nextLine()));
          }
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }*/

   /* public void printKontingenter(){
    findKontingenterFraMedlemsliste();
      System.out.println("Kontingenter: ");
      for (int i = 0; i < kontingenter.size(); i++) {
        System.out.println(kontingenter.get(i).toString().replaceAll("\\[", "").replaceAll("]", ""));
      } kontingenter.clear();
    }*/


  public void visKontingenter(Formand formand){

    //ArrayList<Medlem> medlemKontingent = formand.uploadMedlemListe();
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



