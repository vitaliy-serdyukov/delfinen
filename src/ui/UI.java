package ui;

import ansatte.Formand;
import medlemmer.Medlem;

import java.util.Scanner;

public class UI {
  public Scanner input = new Scanner(System.in);


  public static final String FONT_RESET = "\033[0m"; // Font color reset
  public static final String RED_BOLD = "\033[1;31m";    // Red bold font
  //  public static final String GREEN_BOLD = "\033[1;32m"; // Green bold font
  //   public static final String PURPLE_BOLD = "\033[1;35m"; // Purple bold font


  public void udskrivMedRød(String warning) {
    System.out.println(RED_BOLD + warning + FONT_RESET); // udskrivMedRød for varsler
  }

  public void returnMessage(String message){
    System.out.println(message);
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
