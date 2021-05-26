package ui;

import ansatte.Formand;
import medlemmer.Konkurrencesvømmer;
import medlemmer.Medlem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UI {
  private Scanner input = new Scanner(System.in);
  private Filhåndtering filHåndtering = new Filhåndtering();
  private Medlem medlem = new Medlem();


  public static final String FONT_RESET = "\033[0m"; // Font color reset
  public static final String RED_BOLD = "\033[1;31m";    // Red bold font
  //  public static final String GREEN_BOLD = "\033[1;32m"; // Green bold font
  //   public static final String PURPLE_BOLD = "\033[1;35m"; // Purple bold font


  public void udskrivMedRød(String warning) {
    System.out.println(RED_BOLD + warning + FONT_RESET); // udskrivMedRød for varsler
  }

    public void seMedlemmer() {
      ArrayList<Medlem> medlemmerEfterNavn = filHåndtering.downloadMedlemsFil();
      String betaltStr;

      Collections.sort(medlemmerEfterNavn, medlem.medlemmerEfterNavn); // sorterer eksisterende medlemer efter navn

      System.out.printf(" %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \n", "Medlems-",
              "Navn", "Alder", "Status", "Aktivitetsform", "Kontingent", "Kontingent", "Betalings-");
      System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \033[0m\n", "nummer",
              "", "", "", "", "om året", "i år", "status");

      for (int i = 0; i < medlemmerEfterNavn.size(); i++){

        if (medlemmerEfterNavn.get(i).getBetalt()){
          betaltStr = "Betalt";
        }
        else betaltStr = "Restance";

        System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-25s %-15s %-15s %-10s \033[0m\n",
                medlemmerEfterNavn.get(i).getMedlemsnummer(),medlemmerEfterNavn.get(i).getNavn(),
                medlemmerEfterNavn.get(i).getAlder() + " år", medlemmerEfterNavn.get(i).getAktivitetsstatus(),
                medlemmerEfterNavn.get(i).getAktivitetsForm(), medlemmerEfterNavn.get(i).getKontingent(),
                medlemmerEfterNavn.get(i).getKontingentForRestenAfÅret(), betaltStr);
      }
    }
    public void returnMessage(String message){
    System.out.printf(message);
  }

  public void returnArrayList(ArrayList<Konkurrencesvømmer> arrayList){
    System.out.println(arrayList);
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

        System.out.printf("\033[4m %-10s %-20s %-10s %-10s %-9s\033[0m\n", kontingenterPåSkærm.get(i).getMedlemsnummer(),
                kontingenterPåSkærm.get(i).getNavn(), kontingenterPåSkærm.get(i).getAlder() + " år",
                kontingenterPåSkærm.get(i).getKontingent(), kontingenterPåSkærm.get(i).getKontingentForRestenAfÅret());
      }
  }

  public void validerNavn(Medlem medlem, Formand formand) {
    String navn;
    navn = input.nextLine();
    if (navn.matches("[a-zA-Z]+") && navn.length() <= 15) {

      System.out.println("Navnet er registreret som " + navn);
      medlem.setNavn(navn);
    } else {
      udskrivMedRød("Noget gik galt. Indtast venligst et navn af passende længde. Prøv igen.");
      formand.registrerNavn();
    }
  }

  public void validerAlder(Medlem medlem, Formand formand) {
    String alder;
    alder = input.nextLine();
    if (erNummer(alder)) {
      if (Integer.parseInt(alder) < 0 || Integer.parseInt(alder) > 150) {
        udskrivMedRød("Noget gik galt. Prøv venligst at være realisten...");
        formand.registrerAlder();
      } else {
        System.out.println("Alderen er registreret som " + alder + " år");
        medlem.setAlder(Integer.parseInt(alder));
      }
    } else {
      udskrivMedRød("Noget gik galt. Indtast venligst kun cifrer her");
      formand.registrerAlder();
    }
  }


  public boolean erNummer(String alder) {
    try {
      Integer.parseInt(alder);
      return true;
    } catch (NumberFormatException e) {
      return false;

    }

  }


  /*public String getVælg() {
    return vælg;
  }

  public void setVælg(String vælg) {
    this.vælg = vælg;
  }*/

  String vælg;
  public String valider1Eller2(){
    vælg =  input.nextLine();
    while (!vælg.equals("1")
        && !vælg.equals("2"))
    {
      udskrivMedRød("Intast venligst kun 1-tal  eller 2-tal");
      vælg = input.next();
    }
    return vælg;
  }





 /* public String insertName(){
    Scanner scan2 = new Scanner(System.in);
    System.out.println("\nPlease insert the name for Student: ");
    return scan2.nextLine();
  }*/


















  private String option;

  public void setOption(String option) {
    this.option = option;
  }

  public String getOption() {
    return option;
  }

  public int readChoice(int max){
    int choice = 0;
    do {
      System.out.println(getOption());
      while (!input.hasNextInt()) {
        System.out.println("NOT A VALID CHOICE");
        input.next();
        System.out.println(getOption());
      }
      choice = input.nextInt();
      input.nextLine();

      if (choice <= 0 || choice > max) {
        System.out.println("NOT A VALID CHOICE");
      }
    }
    while(choice <= 0 || choice > max);

    return choice;
  }



   /* public void getInt(String s) {
    }*/

    public void getString(String s) {
    }
}
