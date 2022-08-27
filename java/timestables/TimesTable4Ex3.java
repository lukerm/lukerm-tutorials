package timestables;

import java.util.Scanner;

/*
This is one of the solution classes of the fourth of the timestables. 

In this solution, we answer the following exercise:

Exercise 3: The way we have set up this program, we cannot supply a
value for highest2 without supplying one for highest1. Edit the 
program so that if '-h2' is supplied as a parameter, then the 
argument following it is interpreted as the value for highest2.
Also, program a similar option called '-h1' (and '-l1', '-l2' if
you care to). All other supplied parameters should be ignored. 
[Hint: make use of arrays.]

Quick solution:
  Loop through the args object checking to see if -h1 or -h2
    is present. If so, parse and assign the FOLLOWING 
    argument.

*/
public class TimesTable4Ex3 {

  static int tutorialNo = 4; 

  // The main method, everything is controlled from here. 
  public static void main(String[] args){
  
    // Soft-coded variable for the default highest value.
    int highestDefault = 10;
    // These variables control how high each of the factors go up
    // to. This gives us more flexibility. 
    int highest1 = highestDefault; 
    int highest2 = highestDefault;
    
    // Argument-handling.
    
    // The number of arguments supplied, stored in numArgs. 
    int numArgs = args.length;
    
    // !! If --help is the first argument, the program will exit by displaying 
    // !! the usage method. 
    if(numArgs > 0 && args[0].equals("--help")){
	System.out.println("Usage:");
	System.out.println("java timestables.TimesTable4Ex3 -h1 highest1 ");
	System.out.println("java timestables.TimesTable4Ex3 -h2 highest2 ");
	System.out.println("java timestables.TimesTable4Ex3 -h1 highest1 -h2 highest2");
	System.out.println("java timestables.TimesTable4Ex3 -h2 highest2 -h1 highest1");
	System.exit(0); 
      }
    
    // !! This for loop, if the number of arguments is greater
    // !! than 1, loops through all but the last of the supplied
    // !! arguments. It expects to see either -h1 or -h2 followed
    // !! immediately by an integer. 
    // !! Note: this implementation is not very robust and 
    // !! will fail if an integer does not come after -h1
    // !! or -h2. 
    // !! EXERCISE 3. 
    for(int i=0; i<numArgs-1; i++){
      
      // !! Check to see if the this argument is equal to
      // !! -h1 or -h2, and assigns THE NEXT argument (once
      // !! parsed) to the appropriate highest variable. 
      // !! EXERCISE 3.
      if(args[i].equals("-h1"))      highest1 = Integer.parseInt(args[i+1]);
      else if(args[i].equals("-h2")) highest2 = Integer.parseInt(args[i+1]);
      // !! Otherwise, continue to the next supplied argument. 
    
    }
    
    // Begin the program. 
  
    // A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in); 
    
    // Here we use the nested for loops. The outer one controls the left-
    // multiplier and runs from 2 up to highest inclusive. The inner one 
    // controls the right-hand multiplier, and runs from 1 to highest inclusive. 
    for(int factor1 = 2; factor1 <= highest1; factor1++){
      for(int factor2 = 1; factor2 <= highest2; factor2++){
      
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
	
	// Checks whether the Strings answerStr and response are textually 
	// equal. If they are, the user must have supplied the correct 
	// answer. 
	if(response.equals(answerStr)) System.out.println("Correct!\n");
	else System.out.println("Incorrect. The answer is " + factor1*factor2 + ".\n");
	
	// This makes the program sleep for a given number of milliseconds.
	// Not expected to understand this yet!
	try{Thread.sleep(1000);}
	catch(InterruptedException e){}
	
      
      } // End of inner for loop.
    
    } // End of outer for loop. 
  
  }

}
