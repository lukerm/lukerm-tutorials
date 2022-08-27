package timestables;

// Make use of the Scanner class.
import java.util.Scanner;

/*
This is the second of the timestables. It runs through the times table 
up to the value stored in the variable called highest, using a 
double-nested for loop. The user hits enter in order to see the answer. 

There is no way to check the user's answer. This will change in the 
next tutorial.
*/
public class TimesTable2{


  // The main method, everything is controlled from here. 
  public static void main(String[] args){
  
    // !! A title for when the program starts.
    // !! This answers the exercise in the previous tutorial. 
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
  
    // Needed to read the 'response' of the user. 
    Scanner read = new Scanner (System.in); 
    
    // !! This variable is new, and represent where to stop. This is
    // !! soft-coding. We initialise it here to have the value 10. 
    int highest = 10; 
    
    // Here we use the nested for loops. The outer one controls the left-
    // multiplier and runs from 2 up to !! highest inclusive. The inner one controls
    // the right-hand multiplier, and runs from 1 to !! highest inclusive. 
    for(int factor1 = 2; factor1 <= highest; factor1++){
      for(int factor2 = 1; factor2 <= highest; factor2++){
      
	// Ask the question. This will be displayed to the screen. 
	System.out.print("What is " + factor1 + " x " + factor2 + "? "); 
	// Wait for the user to hit enter.
	read.nextLine(); 
	// Give the answer. 
	System.out.println("The answer is " + factor1*factor2 + ".\n");
	
	// This makes the program sleep for a given number of milliseconds.
	// Not expected to understand this yet!
	try{Thread.sleep(1000);}
	catch(InterruptedException e){}
	
      
      } // End of inner for loop.
    
    } // End of outer for loop. 
  
  }

}
