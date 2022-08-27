package recursion;

import java.util.Scanner;

/*
This is the solution to Exercise 1 set in the tutorial
Tooth. 

Exercise 1: The tooth is fixed to the left-hand border.
Edit the program so that you have a "floating tooth" (and
by this we mean that it EXACTLY the same as the tooth, 
except that the numbers 0 to k are invisible, for
0 <= k < terminal). 

So, the following tooth:

0
    1
        2
    1
0

with k=0, would change to:
 
    1
        2
    1
 

*/
public class ToothEx1 {

  public static void main(String[] args){
  
    // Obtain the arguments invisible and  terminal argument 
    // from the user.
    
    // Check the correct number of arguments (2) have been
    // supplied. 
    if(args.length != 2){
      System.out.println("Usage: java ToothEx1 invisible terminal");
      System.exit(1);
    }
  
    // Try to parse the provided arguments, and assign them to
    // invisible and terminal respectively.  
    int invisible = 0, terminal=0;
    try{
      invisible = Integer.parseInt(args[0]);
      terminal = Integer.parseInt(args[1]);
      
    }
    catch(NumberFormatException e){
      System.out.println("Usage: java ToothEx1 invisible terminal");
      System.out.println("invisible, terminal must be non-negative integers");
      System.exit(1);
    }

    // Arguments invisible and terminal must be non-negative, so 
    // exit the program if this is violated.
    if(invisible<0 || terminal<0){
      System.out.println("Usage: java ToothEx1 invisible terminal");
      System.out.println("Arguments must be non-negative");
      System.exit(1);
    }
    // Semantically, invisible should be no greater than terminal.
    // If this is not the case, make it so by assuming that the 
    // user wanted everything to be invisible.
    else if(invisible > terminal){
      printTabs(terminal, terminal); 
    }
    // The arguments must be good, so access the printTabs method. 
    else printTabs(invisible, terminal);

  }
  
  // This is the publicly accessible method called printTabs.
  // It is essentially a wrapper function to ensure that the
  // the drawing starts at 0 (a negative current argument in
  // the other printTabs method would certainly cause an 
  // Exception to be thrown, which we do not want).
  public static void printTabs(int invisible, int terminal){ 
    // This method's only task is to call the other printTabs
    // method, starting at zero. 
    printTabs(0, invisible, terminal); 
  }
  
  // This method does all of the printing/drawing of the tooth.
  // For a given value of current, n say, it will print n tabs,
  // one after the other, before printing the n itself, conditional
  // on whether it is "visible" or not (i.e. > invisible). Then it
  // will call itself with the next (incremented) value of 
  // current. After this, it will repeat the same n-tab n pattern
  // to create the bottom half of the tooth. 
  private static void printTabs(int current, int invisible, int terminal){
    
    // Print a number of tabs, equal to current. 
    for(int i=1; i<=current; i++) System.out.print("\t");
    // Print the value of current only if it has become "visible",
    // that is, it is larger than invisible. 
    if(current > invisible) System.out.println(current);
    // We need a line break in any case. 
    else System.out.println();
    
    // Check the terminal condition. If we have reached the 
    // terminal value, then simply return from the function,
    // rather than continuing. Note: if this condition were 
    // not here, the recursion would carry on infinitely, and
    // the program would have to be forced closed by the OS. 
    if(current >= terminal) return;
    // If we are at a sub-terminal value, call this function
    // in a recursive fashion. Note that if we did not 
    // increment the value of current, and terminal > 0, then
    // this would also create an infinite number of recursive
    // calls to this method. 
    else printTabs(current+1, invisible, terminal);
    
    // Repeat the above pattern, to get the reflected look.
    for(int i=1; i<=current; i++) System.out.print("\t");
    if(current > invisible) System.out.println(current);
    else System.out.println();
  
  }

}
