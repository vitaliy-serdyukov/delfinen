import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Kasserer {

  //----Attributter----
  private LocalDate localDate = LocalDate.now();

  //----Metoder----

  //Beregner hvor mange dage der er gået på en specifik dato
  public int beregnDagsDatoIVærdi(){
    int dagsDatoIVærdi = localDate.getDayOfMonth();
    if (localDate.getMonthValue() == 1){
      dagsDatoIVærdi += 0;
    } else if (localDate.getMonthValue() == 2){
      dagsDatoIVærdi += 31; //passer ikke hvert år
    } else if (localDate.getMonthValue() == 3){
      dagsDatoIVærdi += 59;
    } else if (localDate.getMonthValue() == 4){
      dagsDatoIVærdi += 90;
    } else if (localDate.getMonthValue() == 5){
      dagsDatoIVærdi += 120;
    } else if (localDate.getMonthValue() == 6) {
      dagsDatoIVærdi += 151;
    } else if (localDate.getMonthValue() == 7) {
      dagsDatoIVærdi += 181;
    } else if (localDate.getMonthValue() == 8) {
      dagsDatoIVærdi += 212;
    } else if (localDate.getMonthValue() == 9) {
      dagsDatoIVærdi += 243;
    } else if (localDate.getMonthValue() == 10) {
      dagsDatoIVærdi += 273;
    } else if (localDate.getMonthValue() == 11) {
      dagsDatoIVærdi += 304;
    } else if (localDate.getMonthValue() == 12) {
      dagsDatoIVærdi += 334;
    }
    System.out.println("Tester dagsdato i værdi: " + dagsDatoIVærdi);
    return dagsDatoIVærdi;
  }


  public void beregnKontingent(Medlem medlem) {
    int dagePåEtÅr = 365;
    if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      int resterendeDagePåÅret = dagePåEtÅr - beregnDagsDatoIVærdi(); //224
      int kontingentPrDag = medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * resterendeDagePåÅret); //2,7 * 224
      System.out.println("For resten af året skal du betale: " +
          medlem.getKontingentForRestenAfÅret()); //Der er noget galt med udregningen

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      int resterendeDagePåÅret = dagePåEtÅr - beregnDagsDatoIVærdi();
      int kontingentPrDag = medlem.getKontingent() / dagePåEtÅr;
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * resterendeDagePåÅret);
      System.out.println("For resten af året skal du betale: " +
          medlem.getKontingentForRestenAfÅret()); //Der er noget galt med udregningen

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      int resterendeDagePåÅret = dagePåEtÅr - beregnDagsDatoIVærdi();
      int kontingentPrDag = medlem.getKontingent() / dagePåEtÅr;
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * resterendeDagePåÅret);
      System.out.println("For resten af året skal du betale: " +
          medlem.getKontingentForRestenAfÅret()); //Der er noget galt med udregningen

    } else if (medlem.getAktivitetsstatus().contains("Passiv")) {
      medlem.setKontingent(500);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      int resterendeDagePåÅret = dagePåEtÅr - beregnDagsDatoIVærdi();
      int kontingentPrDag = medlem.getKontingent() / dagePåEtÅr;
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * resterendeDagePåÅret);
      System.out.println("For resten af året skal du betale: " +
          medlem.getKontingentForRestenAfÅret()); //Der er noget galt med udregningen
    }
  }


  public void visKontingenter(Formand formand, Filhåndtering fh) {
    fh.downloadMedlemsliste();

    for (int i = 0; i < fh.downloadMedlemsliste().size(); i++) {
      System.out.println(fh.downloadMedlemsliste().get(i).getKontingent());
    }
  }

  public void udregnKontingenter(Formand formand, Filhåndtering fh) {

    ArrayList<String> forventedeKontingentListe = new ArrayList<>();
    int forventedeKontingent = 0;

    for (int i = 0; i < fh.downloadMedlemsliste().size(); i++) {
      forventedeKontingent += fh.downloadMedlemsliste().get(i).getKontingentForRestenAfÅret();
    }
    System.out.println("Den forventede årlige kontingentindkomst er " + forventedeKontingent + "kr.");
  }

  public void findMedlemmerIRestance(Formand formand) {
    ArrayList<Medlem> medlemmerIRestance = new ArrayList<>();
    for (int i = 0; i < formand.getMedlemmer().size(); i++) {
      if (!formand.getMedlemmer().get(i).getBetalt()) {
        System.out.println("Test");
        medlemmerIRestance.add(formand.getMedlemmer().get(i));
      }
    }
    System.out.println(medlemmerIRestance);
  }

  public void harBetalt(Medlem medlem) {
    Scanner input = new Scanner(System.in);
    ArrayList<String> kortoplysninger = new ArrayList();
    System.out.println("Vil medlemmet betale med det samme? (ja/nej) ");
    String betalingNu = input.nextLine();

    if (betalingNu.equals("ja")) {
      System.out.println("Indtast kontonummer: (10 cifre) ");
      String kontoNr = input.nextLine();
      kortoplysninger.add(kontoNr);
      System.out.println("Indtast registreringsnummer: (4 cifre)");
      String regNr = input.nextLine();
      kortoplysninger.add(regNr);
      if (kortoplysninger.get(0).length() == 10 && kortoplysninger.get(1).length() == 4) {
        System.out.println("Tjekker betalingskort...");
        System.out.println("Godkendt.");
        medlem.setBetalt(true);
      } else {
        System.out.println("Noget gik galt. Husk mellemrummet. Prøv igen.");
        harBetalt(medlem);
      }
    } else {
      System.out.println(medlem.getNavn() + " står i restance.");
      medlem.setBetalt(false);
    }

  }

}


