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
    private String menuOverskrift =  "Svømmeklubben Delfinen:" +  "\n" + "Vælg venligst: \n";
    private String brugerValg;
    private Formand formand = new Formand();
    private Kasserer kasserer = new Kasserer();
    private Træner træner = new Træner();
    private Filhåndtering filhåndtering = new Filhåndtering();
    private final UI ui = new UI();
    private Medlem medlem = new Medlem();
    private  String[] menuChoice = {
            "1. Opret medlem",
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


    //----Konstruktør----
    public Menu(String menuOverskrift, String brugerValg, String[] menuChoice) {
        this.menuOverskrift = menuOverskrift;
        this.brugerValg = brugerValg;
        this.menuChoice = menuChoice;
    }

    //----Override konstrtuktør----
    public Menu() {}

    //----Metoder----
    public void printMenu(){
        for (int i = 0; i < menuChoice.length; i++){
            System.out.println(menuChoice[i]);
        }
    }

    public int readChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        int choice = -1;
        while (!validChoice) {
           // System.out.print(brugerValg); giver null efter menuen
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoice = true;
            } else {
                ui.udskrivMedRød("Fejl, indtast et tal fra menuen");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public void visMenu() throws IOException {
        ui.returnMessage("");
        ui.returnMessage(menuOverskrift);
        printMenu();

        boolean isRunning;
        isRunning = true;
        while (isRunning) {
            int userChoice = readChoice();

            switch (userChoice) {
                case 1:
                    formand.run();
                    visMenu();
                    break;
                case 2:
                    ui.seMedlemmer();
                    visMenu();
                    break;
                case 3:
                    ui.visKontingenter(formand, filhåndtering, medlem);
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
                    træner.registrerSvømmeresultat(medlem);
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
                    //formand.sletMedlem(new Menu());
                    break;
                case 0:
                    isRunning = false;
                    ui.udskrivMedRød("Afslutter...");
                    break;
                default:
                    ui.udskrivMedRød("\nFejl.\nDet indtastede er ugyldigt ");
                    visMenu();

            }

        }
    }

}