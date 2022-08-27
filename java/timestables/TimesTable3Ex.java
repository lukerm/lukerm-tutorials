package timestables;

// Make use of the Scanner class.
import java.util.Scanner;

/*
This is the solution to the third of the timestables. 

In this solution, we answer the following exercise. 

Exercise 1: Edit this program so that if the user types one of: 'q', 'Q',
'quit', 'Quit', 'QUIT', etc., at any stage, then program exits with a
goodbye message. [Hint: Make use of the command 'System.exit(0);', and
the function 'equalsIgnoreCase'.]
*/
public class TimesTable3Ex {


  // The main method, everything is controlled from here. 
  public static void main(String[] args){
  
    // A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in); 
    
    // This variable is new, and represent where to stop. This is
    // soft-coding. We initialise it here to have the value 10. 
    int highest = 10; 
    
    // Here we use the nested for loops. The outer one controls the left-
    // multiplier and runs from 2 up to highest inclusive. The inner one 
    // controls the right-hand multiplier, and runs from 1 to highest inclusive. 
    for(int factor1 = 2; factor1 <= highest; factor1++){
      for(int factor2 = 1; factor2 <= highest; factor2++){
      
	// Ask the question. This will be displayed to the screen. 
	System.out.print("What is " + factor1 + " x " + factor2 + "? "); 
	
	// Wait for the user to enter their response, which is then
	// assigned to the variable response. (The trim method takes
	// away any blank spaces at the start or end of the response.) 
	String response = read.nextLine().trim(); 
	
	// Calculates the answer here, and stored as type Integer (not int,
	// this means we can make use of the toString function). 
	Integer answer = new Integer(factor1*factor2);
	// Use the toString method of the Integer-object answer to convert
	// it into a String representation of it, stored in the String-type
	// variable answerStr.
	String answerStr = answer.toString();
	
	// !! If the user types 'q' or 'quit' (or any upper case variants
	// !! of these) the program closes prematurely. 
	// !! EXERCISE 1. 
	if(response.equalsIgnoreCase("q") || response.equalsIgnoreCase("quit")){
	  System.exit(0);
	}
	// Checks whether the Strings answerStr and response are textually 
	// equal. If they are, the user must have supplied the correct 
	// answer. 
	else if(response.equals(answerStr)) System.out.println("Correct!\n");
	else System.out.println("Incorrect. The answer is " + factor1*factor2 + ".\n");
	
	// Note: Here we have chosen to convert the actual answer (which is
	// an Integer) to a String, rather than the other way around. The 
	// other way is actually preferable, but requires more complex methods
	// covered by exception handling. This will be covered later. 
	
	// This makes the program sleep for a given number of milliseconds.
	// Not expected to understand this yet!
	try{Thread.sleep(1000);}
	catch(InterruptedException e){}
	
      
      } // End of inner for loop.
    
    } // End of outer for loop. 
  
  }

}
