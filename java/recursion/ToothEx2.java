package recursion;

import java.util.Scanner;

/*
This is the solution to Exercise 1 set in the tutorial
Tooth.     
  
Exercise 2: Edit the tooth so that the internal part is 
filled, giving the pyramid look. A single column should 
have only one number. For example, the following tooth:

0
    1
	2
    1
0

would become:

0
0    1
0    1    2
0    1
0
 

*/
public class ToothEx2 {

  public static void main(String[] args){
  
    // Obtain the argument terminal argument from the user.
    
    // Check the correct number of arguments (1) have been
    // supplied. 
    if(args.length != 1){
      System.out.println("Usage: java ToothEx2 terminal");
      System.out.println("There must be only one argument");
      System.exit(1);
    }
  
    // Try to parse the provided argument, and assign it to
    // terminal. 
    int terminal=0;
    try{terminal = Integer.parseInt(args[0]);}
    catch(NumberFormatException e){
      System.out.println("Usage: java ToothEx2 terminal");
      System.out.println("terminal must be a non-negative integer");
      System.exit(1);
    }

    // Terminal must be non-negative, so exit the program if
    // this is violated.
    if(terminal<0){
      System.out.println("Usage: java ToothEx2 terminal");
      System.out.println("Argument must be non-negative");
      System.exit(1);
    }
    // The argument must be good, so access the printTabs method. 
    else printTabs(terminal);

  }
  
  // This is the publicly accessible method called printTabs. 
  // It is essentially a wrapper function to ensure that the
  // the drawing starts at 0 (a negative current argument in
  // the other printTabs method would certainly cause an 
  // Exception to be thrown, which we do not want).
  public static void printTabs(int terminal){ 
    // This method's only task is to call the other printTabs
    // method, starting at zero. 
    printTabs(0, terminal); 
  }
  
  // This method does all of the printing/drawing of the tooth.
  // For a given value of current, n say, it will print n tabs,
  // one after the other, before printing the n itself. Then it
  // will call itself with the next (incremented) value of 
  // current. After this, it will repeat the same n-tab n pattern
  // to create the bottom half of the tooth. 
  private static void printTabs(int current, int terminal){
    
    // Print a number of tabs, equal to current. 
    for(int i=0; i<=current; i++) {
      System.out.print(i); // !! Command added.
      System.out.print("\t");     
    }
    System.out.println(); // Line break.
    
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
    else printTabs(current+1, terminal);
    
    // Repeat the above pattern, to get the reflected look.
    for(int i=0; i<=current; i++) {
      System.out.print(i);
      System.out.print("\t");
    }
    System.out.println();
  
  }

}
