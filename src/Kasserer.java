import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kasserer {

  //----Attributter----
  private LocalDate localDate = LocalDate.now();

  //----Metoder----

  //Beregner hvor mange dage der er gået på en specifik dato
  public double beregnResterendeDagePåÅret(){
    int dagePåÅret = 365;
    double dageTilbage = localDate.getDayOfMonth();
    if (localDate.getMonthValue() == 1){
      dageTilbage += 0;
    } else if (localDate.getMonthValue() == 2){
      dageTilbage += 31; //passer ikke hvert år
    } else if (localDate.getMonthValue() == 3){
      dageTilbage += 59;
    } else if (localDate.getMonthValue() == 4){
      dageTilbage += 90;
    } else if (localDate.getMonthValue() == 5){
      dageTilbage += 120;
    } else if (localDate.getMonthValue() == 6) {
      dageTilbage += 151;
    } else if (localDate.getMonthValue() == 7) {
      dageTilbage += 181;
    } else if (localDate.getMonthValue() == 8) {
      dageTilbage += 212;
    } else if (localDate.getMonthValue() == 9) {
      dageTilbage += 243;
    } else if (localDate.getMonthValue() == 10) {
      dageTilbage += 273;
    } else if (localDate.getMonthValue() == 11) {
      dageTilbage += 304;
    } else if (localDate.getMonthValue() == 12) {
      dageTilbage += 334;
    }
    dageTilbage = dagePåÅret - dageTilbage;
    System.out.println("Tester dagsdato i værdi: " + dageTilbage);
    return dageTilbage;
  }


  public void beregnKontingent(Medlem medlem) {
    int dagePåEtÅr = 365;
    if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret((int) kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      System.out.println("For resten af året skal du betale: " +
          String.format(String.format("%.2f", medlem.getKontingentForRestenAfÅret()))); //Der er noget galt med udregningen

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret((int) kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      System.out.println("For resten af året skal du betale: " +
              String.format(String.format("%.2f", medlem.getKontingentForRestenAfÅret())));

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret((int) kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      System.out.println("For resten af året skal du betale: " +
              String.format(String.format("%.2f", medlem.getKontingentForRestenAfÅret())));

    } else if (medlem.getAktivitetsstatus().contains("Passiv")) {
      medlem.setKontingent(500);
      System.out.println("\nDin årlige kontingent er: " + medlem.getKontingent());
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret((int) kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      System.out.println("For resten af året skal du betale: " +
              String.format(String.format("%.2f", medlem.getKontingentForRestenAfÅret())));
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

  public void findMedlemmerIRestance(Formand formand, Filhåndtering filhåndtering, Medlem medlem) {

    String betaltStr;
    ArrayList<Medlem> restanvePåSkærm = new ArrayList<>();
    for (int i = 0; i < filhåndtering.downloadMedlemsliste().size(); i++) {
      if (!filhåndtering.downloadMedlemsliste().get(i).getBetalt()) {
        restanvePåSkærm.add(filhåndtering.downloadMedlemsliste().get(i));
      }
    }
    Collections.sort(restanvePåSkærm, medlem.medlemmerEfterNavn);

    System.out.println("Vi har følgende medlemmer med restance i vores klub:\n ");

    System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "Medlems-",
        "Navn", "Alder", "Beløb i", "Restance");
    System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-10s \033[0m\n", "nummer", "", "", "restance", "Status");

    for (int i = 0; i < restanvePåSkærm.size(); i++) {


      if (restanvePåSkærm.get(i).getBetalt()){
        betaltStr = "Betalt";
      }
      else betaltStr = "Restance";

      System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-10s\033[0m\n", restanvePåSkærm.get(i).getMedlemsnummer(),
          restanvePåSkærm.get(i).getNavn(), restanvePåSkærm.get(i).getAlder() + " år",
          restanvePåSkærm.get(i).getKontingentForRestenAfÅret(), betaltStr);
    }


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


