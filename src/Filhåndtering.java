import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filhåndtering {

  public void uploadMedlemsFil(ArrayList<Medlem> medlemmer) {
    File file = new File("src/Medlemliste.txt");
    try {
      FileWriter fileWriter = new FileWriter(file, true);
      for (int i = 0; i < medlemmer.size(); i++) {
        fileWriter.write(
               medlemmer.get(i).getMedlemsnummer() +
            "\n" + medlemmer.get(i).getNavn() +
            "\n" + medlemmer.get(i).getAlder() +
            "\n" + medlemmer.get(i).getAktivitetsstatus() +
            "\n" + medlemmer.get(i).getAktivitetsForm() +
            "\n" + medlemmer.get(i).getKontingent() +
            "\n" + medlemmer.get(i).getKontingentForRestenAfÅret() +
            "\n" + medlemmer.get(i).getBetalt() +
            "\n");

      }
      fileWriter.close();
      medlemmer.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Medlem> downloadMedlemsliste() {
    ArrayList<Medlem> medlemsFilliste = new ArrayList<>();
    File file = new File("src/Medlemliste.txt");
    try {
      Scanner fileReader = new Scanner(file);
      if (fileReader.hasNextLine()) {
        while (fileReader.hasNextLine()) {
          int medlemsnummer;
          String temp;
          String navn;
          int alder;
          String aktivitetsStatus;
          String aktivitetsForm;
          int kontingent;
          double kontingentForIÅr;
          boolean betalt;

          temp = fileReader.nextLine();
          medlemsnummer = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          navn = temp;

          temp = fileReader.nextLine();
          alder = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          aktivitetsStatus = temp;

          temp = fileReader.nextLine();
          aktivitetsForm = temp;

          temp = fileReader.nextLine();
          kontingent = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          kontingentForIÅr = Double.parseDouble(temp);

          temp = fileReader.nextLine();
          betalt = Boolean.parseBoolean(temp);


          medlemsFilliste.add(new Medlem(medlemsnummer,navn,alder,aktivitetsStatus,aktivitetsForm,kontingent,
              kontingentForIÅr, betalt));
        }
      }
      fileReader.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return medlemsFilliste;
  }

  public void uploadKonkurrencesvømmerResultatFil(ArrayList<Konkurrencesvømmer> konkurrencesvømmerResultat) {

    File file = new File("src/KonkurrencesvømmerResultat.txt");
    try {
      FileWriter fileWriter = new FileWriter(file, true);
      //fileWriter.append("Juniorsvømmer: ");
      for (int i = 0; i < konkurrencesvømmerResultat.size(); i++) {
        fileWriter.write(konkurrencesvømmerResultat.get(i).getMedlemsnummer() +
            "\n" + konkurrencesvømmerResultat.get(i).getNavn() +
            "\n" + konkurrencesvømmerResultat.get(i).getAlder() +
            "\n" + konkurrencesvømmerResultat.get(i).getSvømmedisciplin() +
            "\n" + konkurrencesvømmerResultat.get(i).getSvømmeresultat() +
            "\n" + konkurrencesvømmerResultat.get(i).getResultatsDato() +
            "\n");
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public ArrayList<Konkurrencesvømmer> downloadKonkurrencesvømmerResultatFil() {
    ArrayList<Konkurrencesvømmer> juniorsvømmerResultFil = new ArrayList<>();
    File file = new File("src/KonkurrencesvømmerResultat.txt");
    try {
      Scanner fileReader = new Scanner(file);

      if (fileReader.hasNextLine()) {
        while (fileReader.hasNextLine()) {
          int medlemsnummer;
          String temp;
          String navn;
          int alder;
          String svømmedisciplin;
          double resultat;
          LocalDate resultatsDato;

          temp = fileReader.nextLine();
          medlemsnummer = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          navn = temp;

          temp = fileReader.nextLine();
          alder = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          svømmedisciplin = temp;

          temp = fileReader.nextLine();
          resultat = Double.parseDouble(temp);

          temp = fileReader.nextLine();
          resultatsDato = LocalDate.parse(temp);

          juniorsvømmerResultFil.add(new Konkurrencesvømmer(medlemsnummer, navn, alder, svømmedisciplin, resultat,
              resultatsDato));
        }
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return juniorsvømmerResultFil;
  }


public void uploadStævneFil(ArrayList<Konkurrencesvømmer>stævneResultatTemp) {

    File file = new File("src/StævneResulater.txt");
    try {
      FileWriter fileWriter = new FileWriter(file, true);

      for (int i = 0; i < stævneResultatTemp.size(); i++) {
        fileWriter.write(stævneResultatTemp.get(i).getMedlemsnummer() +
            "\n" + stævneResultatTemp.get(i).getNavn() +
            "\n" + stævneResultatTemp.get(i).getAlder() +
            "\n"+ stævneResultatTemp.get(i).getStævneNavn() +
            "\n"+ stævneResultatTemp.get(i).getStævneDato() +
            "\n"+ stævneResultatTemp.get(i).getSvømmedisciplin() +
            "\n"+ stævneResultatTemp.get(i).getPlacering() +
            "\n"+ stævneResultatTemp.get(i).getSvømmeresultat() +
            "\n");

      }
      fileWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
  /*public ArrayList<Konkurrencesvømmer> downloadKonkurrencesvømmerFil() {
    ArrayList<Konkurrencesvømmer> konkurrencesvømmerFil = new ArrayList<>();
    File file = new File("src/Konkurrencesvømmerliste.txt");
    try {
      Scanner fileReader = new Scanner(file);
      if (fileReader.hasNextLine()) {
        while (fileReader.hasNextLine()) {
          int medlemsnummer;
          String temp;
          String navn;
          int alder;

          temp = fileReader.nextLine();
          medlemsnummer = Integer.parseInt(temp);

          temp = fileReader.nextLine();
          navn = temp;

          temp = fileReader.nextLine();
          alder = Integer.parseInt(temp);

          konkurrencesvømmerFil.add(new Konkurrencesvømmer(medlemsnummer, navn, alder));
        }
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return konkurrencesvømmerFil;
  }*/