import java.io.FileNotFoundException;

public class Kasserer {

   // Menu menu = new Menu();
    Medlem medlem = new Medlem();
    Formand formand = new Formand();


    public void seKontingentOversigt() {
        System.out.println("Ser kontingentoversigt");
        for (int i = 0; i < formand.medlemmer.size(); i++){
            System.out.println(formand.medlemmer.toString());
        }


    }
    public void beregnKontingent(){
        if (medlem.getAktivitetsstatus() == 1 && medlem.getAlder() < 18 ){
            medlem.setKontingent(1000);
        } else if (medlem.getAktivitetsstatus() == 1 && 18 <= medlem.getAlder() &&medlem.getAlder() < 60){
            medlem.setKontingent(1600);
        } else if (medlem.getAktivitetsstatus() == 1 && medlem.getAlder() >= 60){
            medlem.setKontingent(1200);
        } else if (medlem.getAktivitetsstatus() == 2) {
            medlem.setKontingent(500);
        }

    }
}