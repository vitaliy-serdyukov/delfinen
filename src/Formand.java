import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private int alder;
    private String navn;

    public int getSvarPåAktivitetsstatus() {
        return svarPåAktivitetsstatus;
    }

    private int svarPåAktivitetsstatus;
    //private Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
    Scanner input = new Scanner(System.in);



    //----Konstruktøren----
    public Formand() {
    }

    public int getAlder() {
        return alder;
    }

    //----Objekter----
    Medlem medlem = new Medlem();

    //----Lister----
    ArrayList<Medlem> medlemmer = new ArrayList<>();

    //----Gettere----
    public String getNavn() {
        return navn;
    }

    //----Objekter----
    Menu menu = new Menu();

    //----Metoder----
    public void opretMedlem() throws FileNotFoundException {
        menu.printMenu();
        System.out.println("Hvad er dit navn?: ");
        navn = input.nextLine();
        System.out.println("Hvad er din alder?: ");
        alder = input.nextInt();
        medlemmer.add(new Medlem(navn, alder, svarPåAktivitetsstatus));

    }
}
