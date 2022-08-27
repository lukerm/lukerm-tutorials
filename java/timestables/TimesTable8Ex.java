package timestables;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/*
The solutions to the eighth of the timestables. 

In these solution, we shall answer the following exercises:

Exercise 1: There is a very subtle bug in this program. It is 
primarily due to the variety of operators that we have at our
disposal now (e.g. exponentiation), though it was still present
under the ordinary Times Tables. Can you work out what the
problem is? If so, how might you go about fixing the bug? 
[Hint: a search engine might help.]

Solution:

The bug is this: the primitive type int has only limited storage
capacity, 4 bytes in total. As such, the maximal value that it
can store is 2,147,483,647. We can't really expect the user to
be performing mental arithmetic on this scale, but with the 
introduction of exponentiation, breaching the maximum becomes
more likely: for example, 4^4^4 = 4,294,967,296, which is greater
than the maximum. The bug is of course possible under the usual
Times Table, if the number of factors is very large. 

Whilst the user is unlikely to want to attempt arithmetic this 
close to the maximum, a bug is a bug. It could be partially 
fixed by using long instead of int, as they have 8 byte storage
capacity. But the problem would persist, just with a (much)
greater maximum. The real way to get around this problem (and
this is where a search engine might have been useful) is to
use BigInteger objects rather than int. Have a look at the 
JavaDoc for this class. They are a little trickier to use, but
they certainly can handle all the operations we have defined,
but can increase their size to handle arbitrarily large integers. 

However, we shall not implement solution here. It would involve
rewriting the Operator class (and its subclasses) so that the
abstract methods (and their implementations) could handle 
BigIntegers. The solution we implement in this class is rather
sub-optimal: we simply remove the exponentiation as an option,
so that this issue is unlikely to arise! 


Exercise 2: Generalize the program so that we are not limited to 
just one Operator but a selection of them, as chosen by the user.
This will mean that one question in the quiz will, in general,
have more than one Operator (unless the user chooses only one).
For example, a question might look like

1) What is 5 + 10 x 3 mod 20 + 7 - 2? 

Always warn the user about associativity, but perhaps when the 
program opens. Left-associativity applies. 

Note: we have included a new Operator called Division. This 
has to be written and handled more carefully in order to 
preserve integers. 

Quick solution:
  Use Operator[] rather than Operator as an instance member. 
  Edit retrieveOperator to retrieveOperators so that it can
    obtain more than one Operator from the user. 
  Edit the constructor of the QA object so that it can 
    generate questions with various Operators in a 
    single question. Special attention should be paid
    to the Division Operator. 

*/
public class TimesTable8Ex {

  // !! An ARRAY for storing the chosen operatorS. 
  // !! EXERCISE 2. 
  Operator[] ops;
  
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
  public TimesTable8Ex(int highest, int numOperands, int numQuestions,
    Operator[] ops) throws IllegalArgumentException{
    
    // !! Assign a value to the Operator. 
    // !! EXERCISE 2. 
    this.ops = ops;
  
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
  public TimesTable8Ex(){
  
    // !! Assign to ops, obtained through the method retrieve
    // !! Operators.
    // !! EXERCISE 2.
    this.ops = retrieveOperators(); 
  
    // We ask the user here for the values of the members highest,
    // numOperands and numQuestions associated via the retrieveInt
    // methods. 
    this.highest = retrieveInt("Highest value of an operand: ", true);
    this.numOperands = retrieveInt("Number of operands: ", true);
    this.numQuestions = retrieveInt("Number of questions: ", true, 1);
      
    System.out.println(); // Line break.
  }
  
  
  // Begin the program by calling this method. Returns the boolean 
  // stating whether the user would like to playAgain.
  public boolean startQuiz(){
  
    // !! We have moved the introductory remarks/instructions to 
    // !! the start of the program, rather than at the start of
    // !! each quiz. 
  
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
  
  // !! A method for retrieving the Operators for this quiz. In
  // !! particular there can be more than one! The return type 
  // !! is now Operator[] rather than Operator. 
  // !! EXERCISE 2. 
  public Operator[] retrieveOperators(){
  
    Operator[] toReturn; // !! For storing the chosen Operators. 
  
    // To read the user's response. 
    Scanner read = new Scanner (System.in);
    
    // !! We have labelled the while loop with the label 'W'.
    // !! This will be explained below. 
    W: while(true){
	// !! Prompt the user for a response. Extra comments are
	// !! required this time. 
	System.out.println("Please choose your operators from the following:\n"
	+ "+, -, x, %, gcd, mod .");
	System.out.println("Your choices can be in any order, separated by commas, e.g.\n"
	+ "x, -, mod, +\nwould give you addition, subtraction, multiplication and modulo arithmetic.\n");
	String resp = read.nextLine().trim(); 
 	System.out.println();
 	
 	// !! Split the response about the Operator-separator
 	// !! which is a comma. This returns a String[] whose
 	// !! elements are the operator codes. 
 	// !! EXERCISE 2.
 	String[] choices = resp.split(",");
 	// !! We now know how large this array needs to be, the
 	// !! size of the choices array. 
 	// !! EXERCISE 2.
 	toReturn = new Operator[choices.length];
 	
 	// !! Loop through the elements of choices, checking
 	// !! whether the individual codes are legal. If they
 	// !! are (that is, one of +, -, x, %, gcd, mod) then
 	// !! create an appropriate Operator subobject, and 
 	// !! store it in that element of toReturn. 
 	// !! EXERCISE 2.
 	for(int i=0; i<choices.length; i++){
 	
	  // !! Note that Division is now a legal Operator.
 	
	  // !! Decide what to do based on this response. The program
	  // !! looks for specific characters, such as '+', '%', etc. 
	  // !! EXERCISE 2.
	  if(choices[i].trim().equals("+"))        toReturn[i] = new Addition(); 
	  else if(choices[i].trim().equals("-"))   toReturn[i] = new Subtraction();
	  else if(choices[i].trim().equals("x"))   toReturn[i] = new Multiplication(); 
	  else if(choices[i].trim().equals("%"))   toReturn[i] = new Division(); 
	  else if(choices[i].trim().equals("gcd")) toReturn[i] = new GreatestCommonDivisor();
	  else if(choices[i].trim().equals("mod")) toReturn[i] = new Modulo(); 
	  // !! The user's response is invalid, so return to the top 
	  // !! of the while loop, but not before informing the user
	  // !! of their mistake. 
	  else { 
	    System.out.println("Invalid response. Could not recognize '" + choices[i] + "'.\n");
	    // !! In this set-up, we have a for loop within a while
	    // !! loop. Once a mistake has been found in the user's
	    // !! choice, we wish to continue to the top of the while
	    // !! loop. However, if we just had 'continue;', we would
	    // !! be taken to the top of the for loop! To get round 
	    // !! this matter, we label the while loop 'W', and use
	    // !! this label to identify which loop we actually want
	    // !! to continue. 
	    // !! EXERCISE 2.
	    continue W; 
	  }
	  
 	}
 	
 	// !! When the for loop finishes, break from the while
 	// !! loop (labelled 'W'). We do not actually need the 
 	// !! 'W' here as there is no ambiguity. 
 	// !! EXERCISE 2.
 	break W; 
	
    }
    
    return toReturn; // Return statement. 
  }
    
  // The purpose of this private inner class is to provide an
  // object which encompasses both a question and its corresponding 
  // answer. The constructor generates the question and answer.  
  // Note: QA stands for Question-Answer. 
  // !! The constructor now generates questions which have 
  // !! different Operators in it.
  // !! EXERCISE 2.
  private class QA {
    
    // Question before it is generated. 
    String question = "";
    
    // The answer is currently equal to the first (random)
    // operand. 
    int answer = random.nextInt(highest) + 1;
    
    // The constructor is used to generate the question (in
    // the form of a String) and the corresponding answer.
    // These will be assigned to the relevant instance 
    // members, and available to the getter methods post-
    // construction. 
    // !! This constructor has been updated to allow more
    // !! than one Operator in a single question.
    // !! EXERCISE 2.
    public QA(){ 
      
      // !! Store the actual question as a String. This is only
      // !! the start of it, we will build it up gradually. 
      question += ("What is " + answer + " ");
      
      // Iteratively, starting from the second, obtain the operands, 
      // calculate the answer, and update question. 
      for(int operand = 2; operand <= numOperands; operand++){
      
	// !! Choose the Operator randomly from those in ops. 
	// !! EXERCISE 2.
	Operator op = ops[random.nextInt(ops.length)];
	
	// !! Update the question. 
	question += (op + " ");
	
	int nextOperand;
	
	// !! Need Division explicitely in order to access its
	// !! getFactors method. 
	// !! EXERCISE 2.
	try{
	  // !! Try to cast the Operator op into a Division
	  // !! object. If it works then the Operator was
	  // !! indeed Division and we can access its get
	  // !! Factors method. Otherwise, this command
	  // !! will throw a ClassCastException, meaning 
	  // !! that the Operator is NOT Division. 
	  Division div = (Division) op;
	  // !! Obtain the possible factors that we can divide
	  // !! answer by in order to preserve it as an integer. 
	  ArrayList<Integer> factors = div.getFactors(answer); 
	  // !! Obtain a randomly chosen factor from factors
	  // !! and assign it to nextOperand. 
	  nextOperand = factors.get(random.nextInt(factors.size()));
	  
	  // !! Note: there is a chance that the nextOperand
	  // !! will be greater than highest. Unfortunately 
	  // !! we cannot guard against this. For example, if
	  // !! answer were a prime number greater than highest,
	  // !! then the only factor is answer, so we must 
	  // !! choose this. 
	
	}
	// !! If this clause is executed the chosen Operator 
	// !! was not Division, so we can choose the next
	// !! operand as usual. 
	catch(ClassCastException e){
	
	  // !! Randomly choose the next operand from the
	  // !! values 1, 2, ... , highest.
	  nextOperand = random.nextInt(highest) + 1;
	
	}
	
	// Update the answer by applying the next operand,
	// via the Operator's operate method. 
	// !! This is defined for ALL Operators, even Division. 
	answer = op.operate(answer, nextOperand);
	
	// !! Update or finish the question. Use the toString
	// !! method of op. 
	if(operand < numOperands)  question += (nextOperand + " ");
	else                       question += (nextOperand + "? "); 
      
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
    
    // !! Introductory remarks, such as how to use the operators. 
    System.out.println("Operator usage:\n");
    System.out.println("+: " + (new Addition()).getHowToUse());
    System.out.println("-: " + (new Subtraction()).getHowToUse());
    System.out.println("x: " + (new Multiplication()).getHowToUse());
    System.out.println("%: " + (new Division()).getHowToUse() + "\n");
    System.out.println("gcd: " + (new GreatestCommonDivisor()).getHowToUse() + "\n");
    System.out.println("mod: " + (new Modulo()).getHowToUse() + "\n");
    
    // !! Warning about associativity. 
    System.out.println("Beware that not all operators are associative. \nApply the "
    + "operators from left to right. That is, if * is the operator,\n");
    System.out.println("a * b * c = ((a * b) * c).\n");
    
    // This is a variable which records whether the user wants 
    // to play again once the quiz has finished. 
    boolean playAgain;
    
    // Here we use a do..while loop to control the flow. Each 
    // cycle will perform one quiz. The while condition is 
    // based on the boolean variable playAgain. 
    do{
      
      // Construct and run the quiz via the default constructor. 
      TimesTable8Ex quiz = new TimesTable8Ex();
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
