import java.io.IOException;
import java.util.Scanner;

public class Menu {

    //----Attributter------

    private String menuOverskrift =  "Svømmeklubben Delfinen:" +  "\n" + "Vælg venligst: \n";
    private String brugerValg;
    private  String[] menuChoice = {
        "1. Opret medlem",
        "2. Kontingentbetalinger",
        "3. Se medlemmer",
        "4. Se juniorsvømmere",
        "5. Se seniorsvømmere",
        "9. Afslut"};

    Formand formand = new Formand();




    final UI ui = new UI();

    //----Konstruktør----

    public Menu(String menuOverskrift, String brugerValg, String[] menuChoice) {
        this.menuOverskrift = menuOverskrift;
        this.brugerValg = brugerValg;
        this.menuChoice = menuChoice;
    }

    //----Override konstrtuktør----
    public Menu() {
    }

    //----Instantiering----
    //Formand formand = new Formand();



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
        Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();

        Kasserer kasserer = new Kasserer();
        Træner træner = new Træner();



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
                  //  kasserer.seKontingentOversigt();
                    visMenu();
                     break;
                case 3:
                    formand.seMedlemmer();
                    visMenu();
                    break;
                case 4:
                    træner.visJuniorsvømmerFil();
                    visMenu();
                    break;
                case 5:
                    træner.visSeniorsvømmerFil();
                    visMenu();
                    break;
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