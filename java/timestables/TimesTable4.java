package timestables;

import java.util.Scanner;

/*
This is the fourth of the timestables. 

There are now two values of highest that can be assigned -- highest1
for the first factor, and highest2 for the second. 

In this tutorial we deal with supplying parameters to the program,
so that the user can specify the value of highest (for both factors
if (s)he so chooses).

In the next tutorial, we shall see how to turn the Times Table into
an object in such a way that suits Object Oriented Programming. We
will also keep track of the user's score. 

Exercise 1: See if you can boost the robustness by creating an error 
message if a number less than 2 is supplied.

Exercise 2: See if you can create a similar system, but the lowest 
values can be supplied in addition to the highest ones.

Exercise 3: The way we have set up this program, we cannot supply a
value for highest2 without supplying one for highest1. Edit the 
program so that if '-h2' is supplied as a parameter, then the 
argument following it is interpreted as the value for highest2.
Also, program a similar option called '-h1' (and '-l1', '-l2' if
you care to). All other supplied parameters should be ignored. 
[Hint: make use of arrays.]

*/
public class TimesTable4{

  static int tutorialNo = 4; 

  // The main method, everything is controlled from here. 
  public static void main(String[] args){
  
    // !! Soft-coded variable for the default highest value.
    int highestDefault = 10;
    // !! These variables control how high each of the factors go up
    // !! to. This gives us more flexibility. 
    int highest1 = highestDefault; 
    int highest2 = highestDefault;
    
    // !! Argument-handling. If there are no supplied arguments, then
    // !! the defaults are used. If only one is supplied, then this is
    // !! taken to be the value for highest1, and highest2 takes its
    // !! default. If two are supplied, then the first is assigned to 
    // !! highest1, and the second to highest2. Finally, if there are
    // !! three or more, the program exits in error. 
    
    // !! The number of arguments supplied, stored in numArgs. 
    // !! The variable args (of type String[], which is an array of 
    // !! Strings) tells the program what arguments have been given.
    // !! (Note that Java uses a 0-based indexing system, so the 
    // !! "first" entry has index 0, the second has 1, etc.)
    int numArgs = args.length;
    
    // !! This is a decision tree that causes assignment to the highest
    // !! variables, or causes an error message if there are too many
    // !! arguments. 
    if(numArgs == 0){
      // !! Do nothing, the default values are assumed. 
    }
    else if(numArgs == 1) { 
      // !! There is one argument, which must be converted from type
      // !! String, and assigned to highest1. 
      highest1 = Integer.parseInt(args[0].trim()); 
    }
    else if(numArgs == 2) {
      // !! There are two arguments. As above, but with both values
      // !! of highest. 
      highest1 = Integer.parseInt(args[0].trim()); 
      highest2 = Integer.parseInt(args[1].trim());
    }
    else if(numArgs >= 3) {
      // !! This clause is executed if there are more than two supplied
      // !! arguments, and causes the program to give a helpful error
      // !! message before exiting. 
      System.out.println("Too many arguments. Usage: \n");
      System.out.println("\t java timestables.TimesTable" + tutorialNo);
      System.out.println("\t java timestables.TimesTable" + tutorialNo + " highest1 ");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + " highest1 highest2");
      System.exit(1);
    }
    // !! Note: We could have, alternatively, used just 'else' { rather
    // !! 'else if(numArgs >= 3) {'. They are semantically equivalent.
    
    // !! Note: This system is not robust. Try out what happens if you 
    // !! supply a non-integer argument (e.g. 'f').    
    
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
