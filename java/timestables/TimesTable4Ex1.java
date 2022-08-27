package timestables;

import java.util.Scanner;

/*
This is one of the solution classes of the fourth of the timestables. 

In this solution, we answer the following exercise:

Exercise 1: See if you can boost the robustness by creating an error 
message if a number less than 2 is supplied.

Quick solution:
  Once the arguments have been parsed, add a simple if statement
  to check that both of them are greater than 1. If either are 
  not, exit the program. 

*/
public class TimesTable4Ex1 {

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
    
    // This is a decision tree that causes assignment to the highest
    // variables, or causes an error message if there are too many
    // arguments. 
    if(numArgs == 0){
      // Do nothing, the default values are assumed. 
    }
    else if(numArgs == 1) { 
      // There is one argument, which must be converted from type
      // String, and assigned to highest1. 
      highest1 = Integer.parseInt(args[0].trim()); 
    }
    else if(numArgs == 2) {
      // There are two arguments. As above, but with both values
      // of highest. 
      highest1 = Integer.parseInt(args[0].trim()); 
      highest2 = Integer.parseInt(args[1].trim());
    }
    else {
      // This clause is executed if there are more than two supplied
      // arguments, and causes the program to give a helpful error
      // message before exiting. 
      System.out.println("Too many arguments. Usage: \n");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1 highest1 ");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1 highest1 highest2");
      System.exit(1);
    }  
    
    // !! If either of highest1 or highest2 is less than 2, supply
    // !! an error message and terminate the program. Note that 
    // !! '||' represents the logical or. 
    // !! EXERCISE 1. 
    if(highest1 < 2 || highest2 < 2){
      System.out.println("Arguments must be greater than 1. Usage: \n");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1 highest1 ");
      System.out.println("\t java timestables.TimesTable" + tutorialNo + "Ex1 highest1 highest2");
      System.exit(1);
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
