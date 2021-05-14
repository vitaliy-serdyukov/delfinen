import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {
    private String navn;
    private int alder;
    private int aktivitetsstatus;

    public Medlem(String navn, int alder, int aktivitetsstatus){
        this.navn = navn;
        this.alder = alder;
        this.aktivitetsstatus = aktivitetsstatus;
    }

    public Medlem(){
    }

    public String getNavn(){
        return navn;
    }

    public int getAlder(){
        return alder;
    }
}
