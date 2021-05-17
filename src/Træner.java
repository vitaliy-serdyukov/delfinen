import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Træner {

    Menu menu = new Menu();
    Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();


    public void visJuniorsvømmerFil() {
        ArrayList<String> downloadJuniorfil = new ArrayList<>();
        try {
            File fileRead = new File("src/Juniorsvømmerlisten.txt");

            Scanner fileReader = new Scanner(fileRead);

            while (fileReader.hasNextLine()) {

                downloadJuniorfil.add(fileReader.nextLine() + "\n");

            } //TODO Fiks mellemrum på første linje ved "Medlem" når man printer filen ovenpå
            System.out.println(downloadJuniorfil.toString().replaceAll("\\[", "").replaceAll("]", ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void visSeniorsvømmerFil() {
    }
}