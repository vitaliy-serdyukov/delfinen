import java.io.IOException;
import java.util.Scanner;

public class Menu {

    //----Attributter------
    private String menuOverskrift =  "Svømmeklubben Delfinen:" +  "\n" + "Vælg venligst: \n";
    private String brugerValg;
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
            "0. Afslut"};

    Formand formand = new Formand();
    Kasserer kasserer = new Kasserer();
    Træner træner = new Træner();
    Filhåndtering filhåndtering = new Filhåndtering();
    final UI ui = new UI();
    Medlem medlem = new Medlem();


    //----Konstruktør----
    public Menu(String menuOverskrift, String brugerValg, String[] menuChoice) {
        this.menuOverskrift = menuOverskrift;
        this.brugerValg = brugerValg;
        this.menuChoice = menuChoice;
    }

    //----Override konstrtuktør----
    public Menu() {}


    //----Metoder----
   /* public void printMenu() {
       String printString = menuOverskrift + "\n";
        for (int i = 0; i < menuChoice.length; i++)
            printString += menuChoice[i] + "\n";
        //ui.getString("\n" + printString);*//*
    }*/

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
               // ui.getInt("Fejl, indtast et tal fra menuen");
                System.out.println("Fejl, indtast et tal fra menuen");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public void visMenu() throws IOException {

      //  Menu menu = new Menu("Svømmeklubben Delfinen:", "\nVælg: \n", menuChoice);
        System.out.println();
        System.out.println(menuOverskrift);
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
                    formand.seMedlemmer();
                    visMenu();
                    break;
                case 3:
                    kasserer.visKontingenter(formand, filhåndtering);
                    visMenu();
                    break;
                case 4:
                    kasserer.udregnKontingenter(formand, filhåndtering);
                    visMenu();
                    break;
                case 5:
                    kasserer.findMedlemmerIRestance(formand, filhåndtering);
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
                    træner.runTop5();
                    visMenu();
                    break;
                case 10:
                    træner.registrerStævneresultat();
                    visMenu();
                    break;
                case 11:
                  //  træner.visStævneresultater(); "11. Vis stævneresultater",
                    visMenu();
                    break;
                case 0:
                    isRunning = false;
                    visMenu();
                    break;
                default:
                   ui.getInt("\nFejl.\nDet indtastede er ugyldigt ");
            }

        }
    }

}