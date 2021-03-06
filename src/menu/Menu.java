
package menu;

import ansatte.Formand;
import ansatte.Kasserer;
import ansatte.Træner;
import medlemmer.Medlem;
import ui.Filhåndtering;
import ui.UI;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    //----Attributter------
    private String menuOverskrift =  "\nSvømmeklubben Delfinen:" +  "\n" + "Vælg venligst: \n";
    private Formand formand = new Formand();
    private Kasserer kasserer = new Kasserer();
    private Træner træner = new Træner();
    private Filhåndtering filhåndtering = new Filhåndtering();
    private final UI ui = new UI();
    private Medlem medlem;
    private  String[] menuChoice = {
        "\n1. Opret medlem",
        "2. Medlemmer",
        "3. Kontingentoversigt",
        "4. Forventede kontingentindkomst",
        "5. Medlemmer i restance",
        "6. Konkurrencesvømmere",
        "7. Konkurrencesvømmere med resultat",
        "8. Registrer svømmeresultat",
        "9. Find top 5",
        "10. Registrer stævneresultat",
        "11. Vis stævneresultater",
        "12. Slet medlem",
        "0. Afslut"};

    //----konstrtuktør----
    public Menu() {}

    //----Metoder----
    public void printMenu(){
        for (int i = 0; i < menuChoice.length; i++){
            System.out.println(menuChoice[i]);
        }
    }

    public int læsVælg() {
        Scanner scanner = new Scanner(System.in);
        boolean gyldigtVælg = false;
        int vælg = -1;
        while (!gyldigtVælg) {
            if (scanner.hasNextInt()) {
                vælg = scanner.nextInt();
                gyldigtVælg = true;
                scanner.nextLine();
            } else {
                ui.udskrivMedRød("Fejl, indtast et tal fra menuen");
                scanner.nextLine();
            }
        }
        return vælg;
    }

    public void visMenu() throws IOException {
        ui.returnerBesked("");
        ui.returnerBesked(menuOverskrift);
        printMenu();

        boolean erIgang;
        erIgang = true;
        while (erIgang) {
            int brugerVælg = læsVælg();

            switch (brugerVælg) {
                case 1:
                    formand.run();
                    visMenu();
                    break;
                case 2:
                    formand.seMedlemmer();
                    visMenu();
                    break;
                case 3:
                    kasserer.visKontingenter(formand, filhåndtering, medlem);
                    visMenu();
                    break;
                case 4:
                    kasserer.udregnKontingenter(formand, filhåndtering);
                    visMenu();
                    break;
                case 5:
                    kasserer.findMedlemmerIRestance(formand, filhåndtering, medlem);
                    visMenu();
                    break;
                case 6:
                    træner.printKonkurrencesvømmer(medlem);
                    visMenu();
                    break;
                case 7:
                    træner.printResultatKonkurrencesvømmer();
                    visMenu();
                    break;
                case 8:
                    træner.findSvømmer(medlem);
                    visMenu();
                    break;
                case 9:
                    træner.runTop5(new Menu());
                    visMenu();
                    break;
                case 10:
                    træner.registrerStævneresultat();
                    visMenu();
                    break;
                case 11:
                    træner.printStævne();
                    visMenu();
                    break;
                case 12:
                    formand.sletMedlem();
                    visMenu();
                    break;
                case 0:
                    erIgang = false;
                    ui.udskrivMedRød("Afslutter...");
                    break;
                default:
                    ui.udskrivMedRød("\nFejl.\nDet indtastede er ugyldigt ");
                    visMenu();

            }

        }
    }

}