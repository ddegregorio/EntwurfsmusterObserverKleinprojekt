package DemoApp;

import ASL.AServiceLayer;

import java.util.Scanner;

/**
 * Class that demonstrates the features of the class AServiceLayer
 */
public class Main {
    private static AServiceLayer asl;

    public static void main(String[] args) {

        if(checkIfUserIsAdmin()){
            System.out.println("Found user!");
            startDemo();
        }
        else{
            System.out.println("You are not allowed to use the system");
        }


        //a.setDiscountOnArticle(1000);
    }

    /**
     * Checks if the user is an admin
     * If the user is an admin, the field asl will
     * have an instance of the AServiceLayer stored in it.
     *
     * @return boolean true if the user is an admin
     * boolean false if the user is not an admin
     */
    private static boolean checkIfUserIsAdmin(){
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Please provide your username:");

        username = sc.nextLine();
        System.out.println("Please provide your password");
        password = sc.nextLine();

        asl = AServiceLayer.getASLInstance(username, password);

        if(asl != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Starts a demo to demonstrate the Class AServiceLayer
     */
    private static void startDemo(){
        Parser parser = new Parser();

        System.out.println("What do you want to do?");

        Command command;

        boolean userWantsToQuit = false;

        while(! userWantsToQuit)
        {
            command = parser.getCommand();

            switch(command) {
                case HELP:
                    printHelpText();
                    break;

                case SHOW:
                    showProducts();
                    break;

                case QUIT:
                    userWantsToQuit = true;
                    break;

                case SETSALE:
                    setProductOnSale();
                    break;

                case UNKNOWN:
                    System.out.println("I did not understand the command...");
                    break;
            }

            if(! userWantsToQuit){
                System.out.println();
                System.out.println("Can I do anything else for you?");
            }
        }

    }

    /**
     * Sets a product on sale by using an AServiceLayer object
     */
    private static void setProductOnSale() {
        Scanner sc = new Scanner(System.in);
        int articleNr;

        System.out.println("Please type the Nr. of the product you want to put on sale.");

        if(sc.hasNextInt()){
            articleNr = sc.nextInt();
            asl.setDiscountOnArticle(articleNr);
            sc.nextLine();
        }
    }

    /**
     * Prints all the commands that the user can use
     */
    private static void printHelpText() {
        System.out.println("You have the following Options:\n" +
                "show: shows products\n" +
        "quit: to quit the program\n" +
        "setSale: to set a product on sale");
    }

    /**
     * Shows all the products stored in the database by using
     * an AServiceLayer object
     */
    private static void showProducts(){
        asl.printAllArticlesFromDB();
    }


}
