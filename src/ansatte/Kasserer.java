package ansatte;

import medlemmer.Medlem;
import ui.Filhåndtering;
import ui.UI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kasserer {

  //----Attributter----
  private LocalDate localDate = LocalDate.now();
  private UI ui = new UI();

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
    return dageTilbage;
  }

  public void beregnKontingent(Medlem medlem) {
    int dagePåEtÅr = 365;
    if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() < 18) {
      medlem.setKontingent(1000);
      ui.returnerBesked("\nDin årlige kontingent er: " + medlem.getKontingent() + "\n");
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      ui.returnerBesked("For resten af året skal du betale: " +
          (String.format("%.2f", medlem.getKontingentForRestenAfÅret())) + "\n"); //Der er noget galt med udregningen

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && 18 <= medlem.getAlder() && medlem.getAlder() < 60) {
      medlem.setKontingent(1600);
      ui.returnerBesked("\nDin årlige kontingent er: " + medlem.getKontingent() +  "\n");
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      ui.returnerBesked("For resten af året skal du betale: " +
              (String.format("%.2f", medlem.getKontingentForRestenAfÅret())) + "\n");

    } else if (medlem.getAktivitetsstatus().contains("Aktiv") && medlem.getAlder() >= 60) {
      medlem.setKontingent(1200);
      ui.returnerBesked("\nDin årlige kontingent er: " + medlem.getKontingent() + "\n");
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      ui.returnerBesked("For resten af året skal du betale: " +
              (String.format("%.2f", medlem.getKontingentForRestenAfÅret())) + "\n");

    } else if (medlem.getAktivitetsstatus().contains("Passiv")) {
      medlem.setKontingent(500);
      ui.returnerBesked("\nDin årlige kontingent er: " + medlem.getKontingent() + "\n");
      double kontingentPrDag = (double) medlem.getKontingent() / dagePåEtÅr; //2,7
      medlem.setKontingentForRestenAfÅret(kontingentPrDag * beregnResterendeDagePåÅret()); //2,7 * 224
      ui.returnerBesked("For resten af året skal du betale: " +
              (String.format("%.2f", medlem.getKontingentForRestenAfÅret())) + "\n");
    }
  }

  public void visKontingenter(Formand formand, Filhåndtering filhåndtering, Medlem medlem) {

    ArrayList<Medlem> kontingenterPåSkærm = new ArrayList<>();
    for (int i = 0; i < filhåndtering.downloadMedlemsFil().size(); i++) {
      if (filhåndtering.downloadMedlemsFil().get(i).getKontingent() > 0) {
        kontingenterPåSkærm.add(filhåndtering.downloadMedlemsFil().get(i));
      }
    }
    Collections.sort(kontingenterPåSkærm, medlem.medlemmerEfterNavn);

    System.out.println("Vi har følgende medlemmer med følgende kontingentsats i vores klub:\n ");

    System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "Medlems-",
            "Navn", "Alder", "Kontigent- ", "Kontingent");
    System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-8s \033[0m\n", "nummer", "", "", "sats", "i år");

    for (int i = 0; i < kontingenterPåSkærm.size(); i++) {

      System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-9.2f\033[0m\n", kontingenterPåSkærm.get(i).getMedlemsnummer(),
              kontingenterPåSkærm.get(i).getNavn(), kontingenterPåSkærm.get(i).getAlder() + " år",
              kontingenterPåSkærm.get(i).getKontingent(), kontingenterPåSkærm.get(i).getKontingentForRestenAfÅret());
    }
  }

  public void udregnKontingenter(Formand formand, Filhåndtering fh) {
    ArrayList<String> forventedeKontingentListe = new ArrayList<>();
    double forventedeKontingent = 0;

    for (int i = 0; i < fh.downloadMedlemsFil().size(); i++) {
      forventedeKontingent += fh.downloadMedlemsFil().get(i).getKontingentForRestenAfÅret();
    }
    System.out.printf("\033[4m %-40s %-5.2f %-4s\033[0m\n", "Den forventede årlige kontingentindkomst er ",
           forventedeKontingent, "kr.\n" );
  }

  public void findMedlemmerIRestance(Formand formand, Filhåndtering filhåndtering, Medlem medlem) {

    String betaltStr;
    ArrayList<Medlem> restanvePåSkærm = new ArrayList<>();
    for (int i = 0; i < filhåndtering.downloadMedlemsFil().size(); i++) {
      if (!filhåndtering.downloadMedlemsFil().get(i).getBetalt()) {
        restanvePåSkærm.add(filhåndtering.downloadMedlemsFil().get(i));
      }
    }
    Collections.sort(restanvePåSkærm, medlem.medlemmerEfterNavn);

    ui.returnerBesked("Vi har følgende medlemmer med restance i vores klub: \n ");

    System.out.printf("\n%-10s %-20s %-10s %-10s %-10s\n", "Medlems-",
        "Navn", "Alder", "Beløb i", "Restance");
    System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-8s \033[0m\n", "nummer", "", "", "restance", "Status");

    for (int i = 0; i < restanvePåSkærm.size(); i++) {
      if (restanvePåSkærm.get(i).getBetalt()){
        betaltStr = "Betalt";
      }
      else betaltStr = "Restance";

      System.out.printf("\033[4m %-10s %-20s %-10s %-10.2f %-9s\033[0m\n", restanvePåSkærm.get(i).getMedlemsnummer(),
          restanvePåSkærm.get(i).getNavn(), restanvePåSkærm.get(i).getAlder() + " år",
          restanvePåSkærm.get(i).getKontingentForRestenAfÅret(), betaltStr);
    }
  }

  public void harBetalt(Medlem medlem) {
    Scanner input = new Scanner(System.in);
    ArrayList<String> kortoplysninger = new ArrayList();
    ui.returnerBesked("Vil medlemmet betale med det samme? (ja/nej) ");
    String betalingNu = input.nextLine();

    if (betalingNu.equals("ja")) {
      ui.returnerBesked("Indtast kontonummer: (10 cifre) ");
      String kontoNr = input.nextLine();
      kortoplysninger.add(kontoNr);
      ui.returnerBesked("Indtast registreringsnummer: (4 cifre)");
      String regNr = input.nextLine();
      kortoplysninger.add(regNr);
      if (kortoplysninger.get(0).length() == 10 && kortoplysninger.get(1).length() == 4) {
        ui.returnerBesked("Tjekker betalingskort...");
        ui.returnerBesked("Godkendt.");
        medlem.setBetalt(true);
      } else {
        ui.returnerBesked("Noget gik galt. Husk mellemrummet. Prøv igen.");
        harBetalt(medlem);
      }
    } else {
      ui.returnerBesked(medlem.getNavn() + " står i restance.\n");
      medlem.setBetalt(false);
    }

  }

}


