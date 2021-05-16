import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {
    private String navn;
    private int alder;
    private int aktivitetsstatus;
    private int kontingent;

    public Medlem(String navn, int alder, int aktivitetsstatus, int kontingent){
        this.navn = navn;
        this.alder = alder;
        this.aktivitetsstatus = aktivitetsstatus;
        this.kontingent = kontingent;
    }

    public Medlem(){
    }

    // ------Gettere----------

    public String getNavn(){
        return navn;
    }

    public int getAlder(){
        return alder;
    }

    public int getAktivitetsstatus() {return aktivitetsstatus;}

    public int getKontingent() {
        return kontingent;
    }


    // -------Settere----------

    public void setAktivitetsstatus(int aktivitetsstatus) {
        this.aktivitetsstatus = aktivitetsstatus;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }

    @Override
    public String toString() {
        return navn + alder + aktivitetsstatus + kontingent + " kr." ;
    }
}


