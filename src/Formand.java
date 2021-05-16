import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Formand {

    //----Attributter
    private int alder;
    private String navn;
    private int svarPåAktivitetsstatus;
    private int kontingent;
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
    public int getSvarPåAktivitetsstatus() {
        return svarPåAktivitetsstatus;
    }

    //----Objekter----
    Menu menu = new Menu();

    //----Metoder----
    public void opretMedlem() {
        System.out.println("Hvad er dit navn?: ");
        navn = input.nextLine();
        System.out.println("Hvad er din alder?: ");
        alder = input.nextInt();
        kontingent = medlem.getKontingent();
        medlemmer.add(new Medlem(navn, alder, medlem.getAktivitetsstatus(), medlem.getKontingent()));
    }

    public void seMedlemmer() {
        System.out.println("Ser medlemmer");
        for (int i = 0; i <= medlemmer.size(); i++ ){
            System.out.println(medlemmer.toString());
        }

    }

    @Override
    public String toString() {
        return "Formand{" +
            "medlemmer=" + medlemmer +
            '}';
    }
}


