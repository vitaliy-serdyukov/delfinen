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
        konkurrencesvømmer.getJuniorsvømmerListe().clear();
        try {
            File fileRead = new File("src/Juniorsvømmerliste.txt");
            Scanner fileReader = new Scanner(fileRead);

            while (fileReader.hasNextLine()) {
                //filListe.add(fileReader.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void visSeniorsvømmerFil() {
        konkurrencesvømmer.getJuniorsvømmerListe().clear();
        try {
            File fileRead = new File("src/Seniorsvømmerliste.txt");
            Scanner fileReader = new Scanner(fileRead);

            while (fileReader.hasNextLine()) {
                //filListe.add(fileReader.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}