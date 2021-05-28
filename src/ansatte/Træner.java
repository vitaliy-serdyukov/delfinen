package ansatte;

import medlemmer.Konkurrencesvømmer;
import medlemmer.Medlem;
import menu.Menu;
import ui.Filhåndtering;
import ui.UI;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/** @author */

public class Træner {

  // ----Attributter---------
  private ArrayList<Konkurrencesvømmer> KonkurrencesvømmerResultat = new ArrayList<>();
  private ArrayList<Konkurrencesvømmer> junior = new ArrayList<>();
  private ArrayList<Konkurrencesvømmer> senior = new ArrayList<>();
  private ArrayList<Konkurrencesvømmer> disciplinArray = new ArrayList<Konkurrencesvømmer>();
  private Scanner scan = new Scanner(System.in);
  private String disciplin;

  // -----Objekter--------------
  Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
  Filhåndtering filhåndtering = new Filhåndtering();
  UI ui = new UI();

  //----Metoder----
  public void printKonkurrencesvømmer(Medlem medlem) {
    ArrayList<Medlem> konkurrencesvømmerePåSkærm = new ArrayList<>();
    for (int i = 0; i < filhåndtering.downloadMedlemsFil().size(); i++) {
      if (filhåndtering.downloadMedlemsFil().get(i).getAktivitetsForm().equals("Konkurrencesvømmer")) {
        konkurrencesvømmerePåSkærm.add(filhåndtering.downloadMedlemsFil().get(i));
      }
    }
    Collections.sort(konkurrencesvømmerePåSkærm, medlem.medlemmerEfterNavn);

    ui.returnerBesked("Vi har følgende konkurrencesvømmere i vores klub:\n");

    System.out.printf("\n %-10s %-20s %-6s\n", "Medlems-",
        "Navn", "Alder");
    System.out.printf("\033[4m %-10s %-20s %-5s \033[0m\n", "nummer", "", "");

    for (int i = 0; i < konkurrencesvømmerePåSkærm.size(); i++) {

      System.out.printf("\033[4m %-10s %-20s %-6s\033[0m\n", konkurrencesvømmerePåSkærm.get(i).getMedlemsnummer(),
          konkurrencesvømmerePåSkærm.get(i).getNavn(), konkurrencesvømmerePåSkærm.get(i).getAlder() + " år");
    }
  }

  public void printResultatKonkurrencesvømmer() {
    ArrayList<Konkurrencesvømmer> resultaterPåSkærm = filhåndtering.downloadKonkurrencesvømmerResultatFil();
    Collections.sort(resultaterPåSkærm, konkurrencesvømmer.konkurrencesvømmerNavnDisciplin);
    System.out.printf("\033[4m %-3s %-15s %-10s %-15s %-10s %-12s\033[0m\n", "Nr.",
        "Navn", "Alder", "Disciplin", "Resultat", "Dato");

    for (int i = 0; i < resultaterPåSkærm.size(); i++) {
      System.out.printf("\033[4m %-3s %-15s %-10s %-15s %-10s %-12s\033[0m\n", resultaterPåSkærm.get(i).
              getMedlemsnummer(), resultaterPåSkærm.get(i).getNavn(), resultaterPåSkærm.get(i).getAlder() + " år",
          resultaterPåSkærm.get(i).getSvømmedisciplin(), resultaterPåSkærm.get(i).getSvømmeresultat(),
          resultaterPåSkærm.get(i).getResultatsDato());
    }
  }

  public void printStævne() {
    ArrayList<Konkurrencesvømmer> stævnePåSkærm = filhåndtering.downloadStævneFil();
    Collections.sort(stævnePåSkærm, konkurrencesvømmer.konkurrencesvømmerEfterNavnAlder);
    ui.returnerBesked("\nHer er vores konkurrencesvømmere, som har deltaget i forskellige stævne\n");
    System.out.printf("\n %-10s %-20s %-7s %-15s %-15s %-15s %-10s %-10s\n", "Medlems-",
        "Navn", "Alder", " Stævnenavn", "Stævnedato", "Disciplin","Placering", "Svømme-");
    System.out.printf("\033[4m %-10s %-20s %-7s %-15s %-15s %-15s %-10s %-10s\033[0m\n", "nummer",
        "", "", " ", "", "","", "resultat");

    for (int i = 0; i < stævnePåSkærm.size(); i++) {
      System.out.printf("\033[4m %-10s %-20s %-7s %-15s %-15s %-15s %-10s %-10s\033[0m\n", stævnePåSkærm.get(i).
              getMedlemsnummer(), stævnePåSkærm.get(i).getNavn(), stævnePåSkærm.get(i).getAlder() + " år",
          stævnePåSkærm.get(i).getStævneNavn(), stævnePåSkærm.get(i).getStævneDato(),
          stævnePåSkærm.get(i).getSvømmedisciplin(),stævnePåSkærm.get(i).getPlacering(),
          stævnePåSkærm.get(i).getSvømmeresultat());
    }
  }


  public void findSvømmer(Medlem medlem){

    String svarMedlemsnummer;
    printKonkurrencesvømmer(medlem);
    ui.returnerBesked("\nIntast venligst medlemsnummer for medlem fra overnævnte liste:\n");
    svarMedlemsnummer = scan.nextLine();
        while (!ui.erNummer(svarMedlemsnummer)) {
          ui.udskrivMedRød("Indtast venligst et nummer fra listen...");
          svarMedlemsnummer = scan.next();
        }
    for (int i = 0; i < filhåndtering.downloadMedlemsFil().size(); i++) {
      if (filhåndtering.downloadMedlemsFil().get(i).getMedlemsnummer() ==
          Integer.parseInt(svarMedlemsnummer)) {
        konkurrencesvømmer.setMedlemsnummer(filhåndtering.downloadMedlemsFil().get(i).getMedlemsnummer());
        konkurrencesvømmer.setNavn(filhåndtering.downloadMedlemsFil().get(i).getNavn());
        konkurrencesvømmer.setAlder(filhåndtering.downloadMedlemsFil().get(i).getAlder());

        registrerØvrigeDataSvømmer(medlem);
      }
    }
    udskiftResultatSvømmer();
  }


  public void registrerØvrigeDataSvømmer(Medlem medlem) {
    registrerSvømmedisciplinSvømmer(medlem);
    registrerSvømmeresultatSvømmer();
    gemOgUdstrivDataSvømmer();
  }

  public void registrerSvømmedisciplinSvømmer(Medlem medlem) {
    int svarDisciplin;
    ui.returnerBesked("For hvilken svømmedisciplin skal registreres resultat?\n");
    ui.returnerBesked("\nVælg venligst mellem 1 eller 4");
    ui.returnerBesked("\n1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning\n");
    svarDisciplin = scan.nextInt();
    switch (svarDisciplin) {
      case 1 -> konkurrencesvømmer.setSvømmedisciplin("Butterfly");
      case 2 -> konkurrencesvømmer.setSvømmedisciplin("Crawl");
      case 3 -> konkurrencesvømmer.setSvømmedisciplin("Rygcrawl");
      case 4 -> konkurrencesvømmer.setSvømmedisciplin("Brystsvømning");
      default -> registrerØvrigeDataSvømmer(medlem);
    }
  }

  public void registrerSvømmeresultatSvømmer() {
    ui.returnerBesked("Indtast venligst resultat i format '00,0' sekunder:\n");
    konkurrencesvømmer.setSvømmeresultat(scan.nextDouble());
    scan.nextLine();
    konkurrencesvømmer.setResultatsDato();
  }

  public void gemOgUdstrivDataSvømmer() {
    KonkurrencesvømmerResultat.add(new Konkurrencesvømmer(konkurrencesvømmer.getMedlemsnummer(),
        konkurrencesvømmer.getNavn(), konkurrencesvømmer.getAlder(), konkurrencesvømmer.getSvømmedisciplin(),
        konkurrencesvømmer.getSvømmeresultat(), konkurrencesvømmer.getResultatsDato()));

    filhåndtering.uploadKonkurrencesvømmerResultatFil(KonkurrencesvømmerResultat);
    KonkurrencesvømmerResultat.clear();

    ui.returnerBesked("Ny resultat er registreret for en konkurrencesvømmer: " + konkurrencesvømmer.getNavn() +
        "\nDisciplin: " + konkurrencesvømmer.getSvømmedisciplin() + "\nTid: " + konkurrencesvømmer.getSvømmeresultat());

  }


  public void udskiftResultatSvømmer(){
    ArrayList<Konkurrencesvømmer> bedsteResultat = filhåndtering.downloadKonkurrencesvømmerResultatFil();
    Collections.sort(bedsteResultat,konkurrencesvømmer.konkurrencesvømmerEfterNavnDisciplinResultat);

    // fjerner værste resultat, hvis en medlem har flere af samme svømmedisciplin
    if (bedsteResultat.size() > 1) {
      for (int j = 0; j < bedsteResultat.size(); j++) {
        for (int k = j + 1; k < bedsteResultat.size(); k++) {
          if ((bedsteResultat.get(j).getMedlemsnummer() == bedsteResultat.get(k).
              getMedlemsnummer()) && bedsteResultat.get(j).getSvømmedisciplin().
              equals(bedsteResultat.get(k).getSvømmedisciplin())) {

            bedsteResultat.remove(k);
            k--;
          }
        }
      }
    }
    try
    {
      new FileWriter("src/txt/KonkurrencesvømmerResultat.txt", false).close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    filhåndtering.uploadKonkurrencesvømmerResultatFil(bedsteResultat);
    bedsteResultat.clear();
  }

  public void registrerStævneresultat() {
    String svarStævneNavn;
    int svarMedlemsnummer;
    int placeringSvar;

    ui.returnerBesked("Vælg venligst en disciplin og Top-5 hold først\n");
    delKonkurrencesvømmere();
    vælgSvømmedisciplinTop5();
    vælgHoldTop5(new Menu());

    ui.returnerBesked("Her er vores konkurrencesvømmere, som kan have registreret deres stævneresultater\n");

    ArrayList<Konkurrencesvømmer> stævneResultater = sorterOgUdskrivTop5();// kalder rigtigt her
    ArrayList<Konkurrencesvømmer> stævneResultatTemp = new ArrayList<>();

    ui.returnerBesked("\nIntast venligst medlemsnummer for medlem fra overnævnte liste:\n");
    svarMedlemsnummer = scan.nextInt();
    scan.nextLine();
    for (int i = 0; i < stævneResultater.size(); i++) {

      if (stævneResultater.get(i).getMedlemsnummer() == svarMedlemsnummer) {
        konkurrencesvømmer.setMedlemsnummer(stævneResultater.get(i).getMedlemsnummer());
        konkurrencesvømmer.setNavn(stævneResultater.get(i).getNavn());
        konkurrencesvømmer.setAlder(stævneResultater.get(i).getAlder());

        ui.returnerBesked("Indtast venligst stævnenavn:\n");
        svarStævneNavn = scan.nextLine();
        konkurrencesvømmer.setStævneNavn(svarStævneNavn);

        konkurrencesvømmer.setStævneDato(stævneResultater.get(i).getStævneDato());

        konkurrencesvømmer.setSvømmedisciplin(stævneResultater.get(i).getSvømmedisciplin());

        ui.returnerBesked("Indtast venligst placering:\n");
        placeringSvar = scan.nextInt();
        konkurrencesvømmer.setPlacering(placeringSvar);

        ui.returnerBesked("Indtast venligst resultat i format '00,0' sekunder:\n");
        konkurrencesvømmer.setSvømmeresultat(scan.nextDouble());

        stævneResultatTemp.add(new Konkurrencesvømmer(konkurrencesvømmer.getMedlemsnummer(),
            konkurrencesvømmer.getNavn(), konkurrencesvømmer.getAlder(), konkurrencesvømmer.getStævneNavn(),
            konkurrencesvømmer.getStævneDato(), konkurrencesvømmer.getSvømmedisciplin(),
            konkurrencesvømmer.getPlacering(), konkurrencesvømmer.getSvømmeresultat()));

        ui.returnArrayList(stævneResultatTemp);
      }
    }
    filhåndtering.uploadStævneFil(stævneResultatTemp);
    stævneResultater.clear();
    stævneResultatTemp.clear();

  }

  public void runTop5(Menu menu){
    delKonkurrencesvømmere();
    vælgSvømmedisciplinTop5();
    vælgHoldTop5(menu);
    sorterOgUdskrivTop5();
  }

  public void delKonkurrencesvømmere(){
    for (int i = 0; i < filhåndtering.downloadKonkurrencesvømmerResultatFil().size(); i++) {
      if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() < 18) {
        junior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
      } else if (filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i).getAlder() >= 18) {
        senior.add(filhåndtering.downloadKonkurrencesvømmerResultatFil().get(i));
      }
    }
    filhåndtering.downloadKonkurrencesvømmerResultatFil().clear();
  }

  public void vælgSvømmedisciplinTop5() {
    int svarDisciplin;
    ui.returnerBesked("\nFor hvilken svømmedisciplin skal vises bedste 5 resultater (Top 5):  ");
    ui.returnerBesked("\nVælg venligst mellem 1 eller 4\n");
    ui.returnerBesked("\n1. Butterfly" + "\n2. Crawl" + "\n3. Rygcrawl" + "\n4. Brystsvømning\n");

    svarDisciplin = scan.nextInt();
    switch (svarDisciplin) {
      case 1 -> disciplin = "Butterfly";
      case 2 -> disciplin = "Crawl";
      case 3 -> disciplin = "Rygcrawl";
      case 4 -> disciplin = "Brystsvømning";
      default ->  vælgSvømmedisciplinTop5();
    }
  }

  public void vælgHoldTop5 (Menu menu) {
    int svarHold;
    ui.returnerBesked("Hvilket hold skal vises resultaterne for?\n1. Junior (under 18 år)\n" +
        "2. Senior (over 18 år)\n");
    svarHold = scan.nextInt();
    disciplinArray.clear();
    scan.nextLine();
    if (svarHold == 1) {

      for (int i = 0; i < junior.size(); i++) {
        if (junior.get(i).getSvømmedisciplin().equals(disciplin)) {
          disciplinArray.add(junior.get(i));
        }
      }
      junior.clear();
      senior.clear();

      if (disciplinArray.size() == 0) {
        ui.returnerBesked("Der er ingen konkurrencesvømmere i junior hold med denne disciplin\n");
        try {
          menu.visMenu();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } else if (svarHold == 2) {
      for (int i = 0; i < senior.size(); i++) {
        if (senior.get(i).getSvømmedisciplin().equals(disciplin)) {
          disciplinArray.add(senior.get(i));
        }
      }
      junior.clear();
      senior.clear();
      if (disciplinArray.size() == 0) {
        ui.returnerBesked("Der er ingen konkurrencesvømmere i senior hold med denne disciplin");
        try {
          menu.visMenu();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public ArrayList<Konkurrencesvømmer> sorterOgUdskrivTop5() {
    String holdJunior = "junior";
    String holdSenior = "senior";
    Collections.sort(disciplinArray, konkurrencesvømmer.sorterResultatEfterDouble);

    int max;
    if (disciplinArray.size() > 5) {
      max = 5;
    } else max = disciplinArray.size();

    // fortæller os hvad for et hold vi har i vores ArrayList
    if(disciplinArray.size() > 0 && disciplinArray.get(0).getAlder() < 18) {
      ui.returnerBesked("\nHer er Top-5 " + holdJunior + "svømmere i disciplinen " + disciplin + "\n");
    } else {
      ui.returnerBesked("\nHer er Top-5 " + holdSenior + "svømmere i disciplinen " + disciplin + "\n");
    }

    System.out.printf("\n %-10s %-15s %-8s %-15s %-12s %-8s \n", "Medlems-", "Navn", "Alder", "Disciplin",
        "Tid", "Resultats-");
    System.out.printf("\033[4m %-10s %-15s %-8s %-15s %-12s %-10s \033[0m\n", "nummer", "", "", "",
        "", "dato");

    for (int i = 0; i < max; i++) {
      System.out.printf("\033[4m %-10s %-15s %-8s %-15s %-12s %-8s \033[0m\n",disciplinArray.get(i).
              getMedlemsnummer(), disciplinArray.get(i).getNavn(), disciplinArray.get(i).getAlder(),
          disciplinArray.get(i).getSvømmedisciplin(), disciplinArray.get(i).getSvømmeresultat(),
          disciplinArray.get(i).getResultatsDato());
    }
    return disciplinArray;
  }
}






