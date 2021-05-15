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
    Kasserer kasserer = new Kasserer();
    Træner træner = new Træner();
    //PizzaMenu pizzaMenuen = new PizzaMenu();


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

        String[] menuChoice = {
                "1. Opret medlem",
                "2. Se juniorsvømmere fra fil",
                "3. Se seniorsvømmere fra fil",
                "4. Se oversigt over kontingentbetalinger",
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
                    break;
                case 2:
                    træner.visJuniorsvømmerFil(); //Skal være i træner klassen
                    break;
                case 3:
                    træner.visSeniorsvømmerFil(); //Skal være i træner klassen
                    break;
                case 4:
                    kasserer.seKontingentbetalinger(); //Skal være i kasserer klassen
                    break;
                case 9:
                    isRunning = false;
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