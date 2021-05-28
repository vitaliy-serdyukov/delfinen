package ui;

import ansatte.Formand;
import medlemmer.Konkurrencesvømmer;
import medlemmer.Medlem;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
  private Scanner input = new Scanner(System.in);
  private String vælg;

  public static final String FONT_RESET = "\033[0m"; // Font color reset
  public static final String RED_BOLD = "\033[1;31m"; // Red bold font

  public void udskrivMedRød(String warning) {
    System.out.println(RED_BOLD + warning + FONT_RESET); // udskrivMedRød for varsler
  }

  public void returnerBesked(String message){
    System.out.printf(message);
  }

  public void returnArrayList(ArrayList<Konkurrencesvømmer> arrayList){
    System.out.println(arrayList);
  }

  public void validerNavn(Medlem medlem, Formand formand) {
    String navn;
    navn = input.nextLine();
    if (navn.matches("[a-zA-Z]+") && navn.length() <= 15) {

      System.out.println("Navnet er registreret som " + navn + "\n");
      medlem.setNavn(navn);
    } else {
      udskrivMedRød("Noget gik galt. Indtast venligst et navn af passende længde. Prøv igen.");
      formand.registrerNavn();
    }
  }

  public String validerAlder(Medlem medlem, Formand formand) {
    String alder;
    alder = input.nextLine();
    if (erNummer(alder)) {
      if (Integer.parseInt(alder) < 0 || Integer.parseInt(alder) > 150) {
        udskrivMedRød("Noget gik galt. Prøv venligst at være realisten...");
        formand.registrerAlder();
      } else {
        System.out.println("Alderen er registreret som " + alder + " år\n");
        medlem.setAlder(Integer.parseInt(alder));
      }
    } else {
      udskrivMedRød("Noget gik galt. Indtast venligst kun cifrer her");
      formand.registrerAlder();
    }
    return alder;
  }

  public boolean erNummer(String alder) {
    try {
      Integer.parseInt(alder);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

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

}
