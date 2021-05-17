import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kasserer {

   // Menu menu = new Menu();

   // Formand formand = new Formand();

  public void beregnKontingent(Medlem medlem) { // equals, contains eller matches?
    if (medlem.getAktivitetsstatus().contains("Aktivt medlemskab") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
    } else if (medlem.getAktivitetsstatus().contains("Aktivt medlemskab") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
    } else if (medlem.getAktivitetsstatus().contains("Aktivt medlemskab") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
    } else if (medlem.getAktivitetsstatus().contains("Passivt medlemskab")) {
      medlem.setKontingent(500);
    }
  }

    public void seKontingentOversigt() {
    //TODO Hent KUN kontingent fra filen og smid det ind i en int ArrayListe som vi kan bruge i næste metode og udregne årlig
      ArrayList<String> downloadKontingent = new ArrayList<>();
      System.out.println("Ser kontingentoversigt");
        try {
          File fileRead = new File("src/Medlemliste.txt");

          Scanner fileReader = new Scanner(fileRead);

          while (fileReader.hasNextLine()) {

            downloadKontingent.add(fileReader.nextLine()+ "\n");

          } //TODO Fiks mellemrum på første linje ved "Medlem" når man printer filen ovenpå
          System.out.println(downloadKontingent.toString().replaceAll(".," , "").replaceAll("\\[", "").replaceAll("]", ""));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }

      public void udregnKontingent(){

      }


}

