package timestables;

// Make use of the Scanner class.
import java.util.Scanner;

/*
This is the first of the timestables. It is the most basic version
that one could think  of. It runs through the times table up to 
twelve using a double-nested for loop. The user hits enter in order 
to see the answer. There is no way to check the user's answer. 

The upper bound of 12 is hard-coded. This will change in the next
tutorial. 

Exercise: Add a title, so that there is a welcome message when the 
program begins. The solution to this can be found in the next 
tutorial. 
*/
public class TimesTable1{


  // The main method, everything is controlled from here. 
  public static void main(String[] args){
  
    // Needed to read the 'response' of the user. 
    Scanner read = new Scanner (System.in); 
    
    // Here we use the nested for loops. The outer one controls the left-
    // multiplier and runs from 2 up to 12 inclusive. The inner one controls
    // the right-hand multiplier, and runs from 1 to 12 inclusive. 
    for(int factor1 = 2; factor1 <= 12; factor1++){
      for(int factor2 = 1; factor2 <= 12; factor2++){
      
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
