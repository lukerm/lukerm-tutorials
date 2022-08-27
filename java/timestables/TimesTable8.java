package timestables;

import java.util.Scanner;
import java.util.Random;

/*
This is the eighth and final of the timestables. 

In this tutorial, we shall look at generalizing the operation 
of the Times Table. At the moment multiplication is used as 
standard, but we shall generalize this to any binary operator 
(which is defined), such as addition. A more appropriate name
is now Operator Table. 

Because of the way we have designed this method in previous
tutorials, aside from giving the object a new Operator instance
member, the only changes that need to be made are to the inner
class QA and creating a method retrieveOperator. 

This tutorial is coupled with several other classes, the most
important being the abstract class Operator, and the subclasses
of it. 

Exercise 1: There is a very subtle bug in this program. It is 
primarily due to the variety of operators that we have at our
disposal now (e.g. exponentiation), though it was still present
under the ordinary Times Tables. Can you work out what the
problem is? If so, how might you go about fixing the bug? 
[Hint: a search engine might help.]

Exercise 2: Generalize the program so that we are not limited to 
just one Operator but a selection of them, as chosen by the user.
This will mean that one question in the quiz will, in general,
have more than one Operator (unless the user chooses only one).
For example, a question might look like

1) What is 5 + 10 x 3 mod 20 + 7 - 2? 

Always warn the user about associativity, but perhaps when the 
program opens. Left-associativity applies. 

*/
public class TimesTable8{

  // !! A variable for storing the chosen operator. 
  Operator op;
  
  // Three variables for storing arguments associated with the
  // TimesTable7 object. 
  int highest, numOperands, numQuestions; 
  
  // Random number generator.  
  Random random = new Random();
  
  // A variable for keeping track of the user's score. 
  int score = 0; 
  
  // The constructors are in charge of acquiring the necessary
  // instance variables. 
  
  // Constructor.
  public TimesTable8(int highest, int numOperands, int numQuestions,
    Operator op) throws IllegalArgumentException{
    
    // !! Assign a value to the Operator. 
    this.op = op;
  
    // This is for robustness. The highest value and number of
    // factors must be at least 2, whilst the number of questions
    // must be at least 1.
    if(highest <= 1 || numOperands <= 1 || numQuestions <= 0)
      throw new IllegalArgumentException(); 
  
    // Initialise the object's instance members by the arguments 
    // supplied to the constructor. 
    this.highest = highest;
    this.numOperands = numOperands;
    this.numQuestions = numQuestions; 
    
  }
  
  // Default constructor: asks the user for their arguments via
  // the various retrieveInt methods. 
  public TimesTable8(){
  
    // !! Assign the Operator, obtained through the method retrieve
    // !! Operator. 
    this.op = retrieveOperator(); 
  
    // !! We ask the user here for the values of the members highest,
    // !! numOperands and numQuestions associated via the retrieveInt
    // !! methods. 
    this.highest = retrieveInt("Highest value of an operand: ", true);
    this.numOperands = retrieveInt("Number of operands: ", true);
    this.numQuestions = retrieveInt("Number of questions: ", true, 1);
      
    System.out.println(); // Line break.
  }
  
  
  // Begin the program by calling this method. Returns the boolean 
  // stating whether the user would like to playAgain.
  public boolean startQuiz(){
  
    // !! Introductory remarks, such as how to use the operator. 
    System.out.println("Operator usage:");
    System.out.println(op.getHowToUse() + "\n");
    
    // !! Warning: if the Operator is not associative, warn the
    // !! user about that fact. Only relevant if there are more
    // !! than two operands.
    if(!op.isAssociative() && numOperands > 2){
      System.out.println("Beware: this operator is not associative. \nApply the "
      + "operators from left to right. That is,\n");
      System.out.println("a " + op + " b " + op + " c = ((a " + op + " b) " + op + " c).\n");
    
    }
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in);  
    
    // Record the start time in milliseconds. 
    long startTime = System.currentTimeMillis();
    
    // Repeat this action for each question, until numQuestions has
    // been reached. 
    for(int question = 1; question <= numQuestions; question++){
    
      // Constructs a new QA object, which, during construction
      // will generate a question and the corresponding answer. 
      QA qa = new QA(); 
      
      // Obtain the user's guess at the answer. 
      int response = this.retrieveInt(question + ") " + qa.getQuestion()); 
	
      // Checks whether the user got the correct answer by comparing
      // the variable response to the answer stored in the QA object.
      if(response == qa.getAnswer()) { 
	System.out.println("Correct!\n");
	// Given the user got the correct answer, add one to their score.
	score++;
      }
      // Use the QA's getAnswer method to output the answer. 
      else System.out.println("Incorrect. The answer is " + qa.getAnswer() + ".\n"); 
    
    } // End of questions for loop. 
    
    // End of quiz operations. 
    
    // When all the questions have been answered by the user,
    // record the time in milliseconds. 
    long endTime = System.currentTimeMillis();
    
    // The quiz has concluded, inform the user of their score. 
    System.out.println("End of quiz. You scored " + score + " out of " + numQuestions + ".");
    // Report the user's time to complete the quiz. 
    System.out.println("It took you " + Math.round((endTime - startTime)/1000) + " seconds.\n");
    
    // Find out if the user wants to play again, and return this value.
    return retrieveBool("Would you like to play again? [y/n]: ");
  
  } // End of startQuiz method. 
  
  // Most basic method for retrieving an integer: it simply calls the
  // retrieveInt (String, boolean) method. Calling this NEVER invokes
  // minimum value checking (default: check == false). 
  public int retrieveInt(String prompt){ 
    return this.retrieveInt(prompt, false); 
  }
  
  // This is the second most basic method for retrieving an integer. 
  // It CAN carry out minimum value checking if check is set to 
  // true. The default value for the lowest allowable value is 2.
  // If check == false, this value of lowest is redundant. 
  public int retrieveInt(String prompt, boolean check){ 
    return this.retrieveInt(prompt, check, 2); 
  }
  
  // The method for retrieving an integer which admits most control. 
  // The prompt argument is what is printed to the standard out when
  // prompting the user. The check argument is to used to state whether
  // minimum checking should be invoked or not, when the user gives
  // their response. The argument lowest is to be used to validate the
  // user's response: if check == true and the user enters a value
  // less than lowest, (s)he will be reprompted for another value. 
  // If check == false, the argument lowest is redundant. 
  public int retrieveInt(String prompt, boolean check, int lowest){
  
    // For reading the user's responses. 
    Scanner read = new Scanner(System.in);
    
    // A variable that will eventually store the user's response. 
    int toReturn;
  
    // Robust control to obtain the user's response. 
    while(true){
    
      try{ 
	// Prompt the user for a value. 
	System.out.print(prompt);
	
	// Get the user's response and parse it. 
	toReturn = Integer.parseInt(read.nextLine().trim());
	
	// If check == true, this clause checks whether the user's
	// response is less than lowest. If this is the case, then
	// the user is reprompted. If check == false, this clause 
	// will be ignored. 
	if(check && toReturn < lowest) {
	  System.out.println("Must be greater than " + (lowest-1) + ".");
	  continue; 	
	}
	// The value was successfully obtained, so break
	// from the while loop. 
	break;
      }
      // Catch any Exceptions generated from the parseInt
      // method above. 
      catch(NumberFormatException e){
	System.out.println("Invalid number.");
      }
      
    } // End of while. 
    
    // Return statement.
    return toReturn; 
  
  }
  
  // This is semantically similar to the retrieveInt method
  // above, except we obtain a boolean from the user's 
  // response rather than an int. 
  public boolean retrieveBool(String prompt){
    
    // To read the user's response. 
    Scanner read = new Scanner (System.in);
    
    while(true){
      
	// Prompt the user for a response.
	System.out.print(prompt);
	String resp = read.nextLine().trim(); 
	
	// Decide what to do based on this response. If the user
	// has entered y or Y, (s)he wants to play again, so 
	// return true. 
	if(resp.equalsIgnoreCase("y")) return true; 
	// Conversely, if the user enters n or N, (s)he does not 
	// want to play again, so return false.
	else if(resp.equalsIgnoreCase("n")) return false; 
	// If the user puts anything else (aside from y,Y,n or N), 
	// this counts as an illegal command, so we give a suitable
	// message and return to the top of the while loop. 
	else System.out.println("Invalid response. Please type y or n.");
      
    }
    
  }
  
  // !! Another retriever method for obtaining the Operator
  // !! for this quiz. Very similar to retrieveBool. This 
  // !! should be updated (in two places) if more Operators
  // !! are introduced. 
  public Operator retrieveOperator(){
  
    // To read the user's response. 
    Scanner read = new Scanner (System.in);
    
    while(true){
	// Prompt the user for a response.
	System.out.print("Choose from +, -, x, ^, gcd, mod: ");
	String resp = read.nextLine().trim(); 
 	System.out.println();
	
	// !! Decide what to do based on this response. The program
	// !! looks for specific characters, such as '+', '^', etc. 
	if(resp.equals("+"))        return new Addition(); 
	else if(resp.equals("-"))   return new Subtraction();
	else if(resp.equals("x"))   return new Multiplication(); 
	else if(resp.equals("^"))   return new Exponentiation(); 
	else if(resp.equals("gcd")) return new GreatestCommonDivisor();
	else if(resp.equals("mod"))   return new Modulo(); 
	// The user's response is invalid, so return to
	// the top of the while loop.
	else System.out.println("Invalid response.");
    }
  }
    
  // The purpose of this private inner class is to provide an
  // object which encompasses both a question and its corresponding 
  // answer. The constructor generates the question and answer.  
  // Note: QA stands for Question-Answer. 
  private class QA {
    
    // Question before it is generated. 
    String question = "";
    
    // !! The answer is currently equal to the first (random)
    // !! operand. This differs from the previous implementation:
    // !! we have to change because 1 is not the identity for
    // !! every operator. 
    int answer = random.nextInt(highest) + 1;
    
    // The constructor is used to generate the question (in
    // the form of a String) and the corresponding answer.
    // These will be assigned to the relevant instance 
    // members, and available to the getter methods post-
    // construction. 
    public QA(){ 
      
      // Store the actual question as a String. This is only
      // the start of it, we will build it up gradually. 
      // !! First operand is currently answer. 
      // !! We have to use the toString() method of op, 
      // !! which is implicitely called during String 
      // !! concatenation. 
      question += ("What is " + answer + " " + op + " ");
      
      // Iteratively, starting from the second, obtain the operands, 
      // calculate the answer, and update question. 
      for(int oper = 2; oper <= numOperands; oper++){
      
	// The nextInt(highest) function returns an integer
	// between 0 and (highest - 1), so add 1 to put it 
	// in the correct interval. 
	int nextOper = random.nextInt(highest) + 1;
	// !! Update the answer by applying the next operand,
	// !! via the Operator's operate method. 
	answer = op.operate(answer, nextOper);
	
	// !! Finish the question variable. Use the toString
	// !! method of op. 
	if(oper < numOperands)  question += (nextOper + " " + op + " ");
	else                    question += (nextOper + "? "); 
      
      }
    }
    
    // Getters. 
    public String getQuestion(){ return question; }
    public int getAnswer(){ return answer; }
  
  
  }
  
  // The main method. 
  public static void main(String[] args){
  
    // A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Operator Table! ##\n\n");
    
    // This is a variable which records whether the user wants 
    // to play again once the quiz has finished. 
    boolean playAgain;
    
    // Here we use a do..while loop to control the flow. Each 
    // cycle will perform one quiz. The while condition is 
    // based on the boolean variable playAgain. 
    do{
      
      // Construct and run the quiz via the default constructor. 
      TimesTable8 quiz = new TimesTable8();
      // playAgain is returned from the startQuiz method.
      playAgain = quiz.startQuiz();           
    
    // Use the playAgain variable to decide whether to continue the loop
    // (i.e. play another quiz) or not. 
    } while(playAgain);
    
    // Goodbye message. This is displayed if the user typed n or N, i.e.
    // did not want to play again. 
    System.out.println("\n\n    ## Thanks for playing Operator Table! ##\n\n");
  
  }

}
