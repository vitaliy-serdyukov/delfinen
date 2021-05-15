import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    //----Attributter------
    //---Lavet af Christopher------
    private String menuOverskrift;
    private String brugerValg;
    private String[] pizzaMenu;
    final UI ui = new UI();

    //----Konstruktør----
    //---Lavet af Christopher------
    public Menu(String menuOverskrift, String brugerValg, String[] pizzaMenu) {
        this.menuOverskrift = menuOverskrift;
        this.brugerValg = brugerValg;
        this.pizzaMenu = pizzaMenu;
    }

    //----Override konstrtuktør----
    public Menu() {
    }

    //----Instantiering----
    Formand formand = new Formand();


    //----Metoder----
    //----Lavet af Anna-Christopher---
    public void printMenu() {
        String printString = menuOverskrift + "\n";
        for (int i = 0; i < pizzaMenu.length; i++)
            printString += pizzaMenu[i] + "\n";
        ui.getString("\n" + printString);
    }

    //----Lavet af Anna-Christopher---
    public int readChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        int choice = -1;
        while (!validChoice) {
            System.out.print(brugerValg);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoice = true;
            } else {
                ui.getInt("Fejl, indtast et tal fra menuen");
                scanner.nextLine();
            }
        }
        return choice;
    }

    //----Lavet af Anna-Christopher---
    public void visMenu() throws FileNotFoundException {
        Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
        Formand formand = new Formand();
        Kasserer kasserer = new Kasserer();

        String[] menuChoice = {
                "1. Opret medlem",
                "2. Kontingentbetalinger",
                "3. Se medlemmer",
                "4. Se juniorsvømmere",
                "5. Se seniorsvømmere",
                "9. Afslut"};

        Menu menu = new Menu("Svømmeklubben Delfinen:", "\nVælg: \n", menuChoice);

        menu.printMenu();
        boolean isRunning;
        isRunning = true;
        while (isRunning) {
            int userChoice = menu.readChoice();

            switch (userChoice) {
                case 1:
                    formand.opretMedlem();
                    visMenu();
                    break;
                case 2:
                    kasserer.seKontingentOversigt();
                    visMenu();
                    break;
                case 3:
                    formand.seMedlemmer();
                    visMenu();
                    break;
                case 4:
                    konkurrencesvømmer.downloadJuniorsvømmerFil();
                    visMenu();
                    break;
                case 5:
                    konkurrencesvømmer.downloadSeniorsvømmerFil();
                    visMenu();
                case 9:
                    isRunning = false;
                    visMenu();
                    break;
                default:
                    ui.getInt("\nFejl.\nDet indtastede er ugyldigt ");
            }
           /*for (int i = 0; i < pizzaMenuen.getPizzaMenu().size(); i++) {

                if (pizzaMenuen.getPizzaMenu().get(i).equals(userChoice)) ;
            }*/
        }
    }

}