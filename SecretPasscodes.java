
/**
 * Generates a random password based on criteria specified by the user
 *
 * @author Curtice Gough
 * @version 10/17/18
 */
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
public class SecretPasscodes
{
    public static int random(int lowerLimit, int upperLimit)
    {
        return (int)(Math.random() * (upperLimit - lowerLimit + 1)) + lowerLimit;
    }
    public static void main(String[] args) throws IOException
    {
        // construct objects
        Scanner in = new Scanner(System.in);
        PrintWriter outFile = new PrintWriter( new File("passwords.txt"));
        
        // initialize local variables
        int menuSelection = 0;
        int passwdLength = 0;
        int passwdNum = 1;
        char character = 0;
        
        // display character menu
        System.out.println("\t\t\tPassword Generator Menu");
        System.out.println("***********************************************************************");
        System.out.println("*  [1] Lowercase Letters                                              *");
        System.out.println("*  [2] Uppercase Letters                                              *");
        System.out.println("*  [3] Numbers                                                        *");
        System.out.println("*  [4] Letters and Numbers                                            *");
        System.out.println("*  [5] Finish                                                         *");
        System.out.println("***********************************************************************");
        
        // prompt user input
        while(menuSelection != 5) { //terminate when user selects 5
            System.out.print("Enter Selection (1-5): ");
            menuSelection = Integer.parseInt(in.next());
            
            // verify user input
            while(menuSelection != 1 && menuSelection != 2 && menuSelection != 3 && menuSelection != 4 && menuSelection != 5) {
                System.out.println(" Invalid option.  Please try again.\n");
                System.out.print("Enter Selection (1-5): ");
                menuSelection = Integer.parseInt(in.next());
            }
            
            if(menuSelection != 5) { //don't ask for length if user enters 5
                System.out.print("Password Length (6 or more): ");
                passwdLength = Integer.parseInt(in.next());
            }
            
            // verify user input
            while(passwdLength < 6 && menuSelection != 5) { //don't verify if user enters 5
                System.out.println(" Password length too short.  Please try again.");
                System.out.print("Password length (6 or more): ");
                passwdLength = Integer.parseInt(in.next());
            }
            System.out.println();
            
            // generate password based on user criteria
            // lowercase letters
            if(menuSelection == 1) {
                for(int i = 0; i < passwdLength; i++) {
                    character = (char)random(97,122);
                    outFile.print(character);
                }
                outFile.println();
            }
            
            // uppercase letters
            if(menuSelection == 2) {
                for(int i = 0; i < passwdLength; i++) {
                    character = (char)random(65,90);
                    outFile.print(character);
                }
                outFile.println();
            }
            
            // numbers
            if(menuSelection == 3) {
                for(int i = 0; i < passwdLength; i++) {
                    character = (char)random(48,57);
                    outFile.print(character);
                }
                outFile.println();
            }
            
            // letters and numbers
            if(menuSelection == 4) {
                for(int i = 0; i < passwdLength; i++) {
                    if(random(1,2) == 1) { //if character should be a letter
                        if(random(1,2) == 1) { //if letter should be uppercase
                            character = (char)random(65,90);
                            outFile.print(character);
                        }
                        else { //if letter should be lowercase
                            character = (char)random(97,122);
                            outFile.print(character);
                        }
                    }
                    else { //if character should be a number
                        character = (char)random(48,57);
                        outFile.print(character);
                    }
                }
                outFile.println();
            }
        }
        outFile.close();
        
        // construct inFile
        Scanner inFile = new Scanner(new File("passwords.txt"));
        
        // display results
        System.out.println("Thank you for using the Pass Code Generator.\n");
        System.out.println("Here are your randomly generated codes:");
        
        while(inFile.hasNext()) {
            System.out.println("  " + passwdNum + "\t" + inFile.next());
            passwdNum ++;
        }
        inFile.close();
    }
}
