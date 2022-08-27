package timestables;

import java.util.Scanner;
import java.util.Random;

/*
This is the seventh of the timestables. 

There is something rather awkward about the layout of this class. 
For example, there is a certain lack of code efficiency -- we 
essentially had to repeat the retrieveInt(eger) method twice, once
as a static version, the other as non-static. This stems from the 
fact that the main method collects all the user's arguments (such
as highest, nFactors, nQuestions). 

In this tutorial, we shall tidy up the class for the sake of code
efficiency. We will also solve the annoying problem of not being
allowed to have only question, and look at method overloading in
the process. There will be hardly any difference in functionality,
(the only difference will be the ability to have 1 question), but
the code will be far more readible. We also tidy up the startQuiz
method by introducing an inner class: QA. 

Our main aim in this tutorial is to make full use of the OOP nature
of Java. From now on, a single quiz of Times Table, and all its
functionality, will come from inside the non-static parts of this
class. This removes the dependence on the main method (and other
static parts) which means the class is more versatile.

Note that this method has retained many of the features adapted 
in the solutions of tutorial six. It is strongly advised that
the reader attempt the exercises of tutorial 6, and look at the 
associated solutions before proceeding. 

In the next tutorial, we shall look at generalizing the 
operation of the Times Table. At the moment multiplication is 
used as standard, but we shall generalize this to any binary 
operator (which is defined), such as addition. 

*/
public class TimesTable7{
  
  // !! Three variables for storing arguments associated with the
  // !! TimesTable7 object. There is no need for defaults, the 
  // !! constructors always require them to be provided, in one
  // !! way or another. 
  int highest, numFactors, numQuestions; 
  
  // Random number generator. 
  // !! Must accessible OUTSIDE of the startQuiz method now. 
  Random random = new Random();
  
  // A variable for keeping track of the user's score. 
  int score = 0; 
  
  // !! The constructors are in charge of acquiring the necessary
  // !! instance variables. 
  
  // Constructor.
  public TimesTable7(int highest, int numFactors, int numQuestions)
    throws IllegalArgumentException{
  
    // This is for robustness. The highest value and number of
    // factors must be at least 2, whilst the number of questions
    // must be at least 1.
    if(highest <= 1 || numFactors <= 1 || numQuestions <= 0)
      throw new IllegalArgumentException(); 
  
    // Initialise the object's instance members by the arguments 
    // supplied to the constructor. 
    this.highest = highest;
    this.numFactors = numFactors;
    this.numQuestions = numQuestions; 
    
  }
  
  // !! Default constructor: asks the user for their arguments via
  // !! the various retrieveInt methods. 
  public TimesTable7(){
  
    // !! We ask the user here for the values of the arguments highest,
    // !! numFactors and numQuestions associated with this Object. We 
    // !! do this via the various retrieveInt methods. The robustness
    // !! is achieved by the way these methods are set up. 

    // !! The first two do not need to provide a value of lowest, since
    // !! since the default is 2 (that is, retrieveInt(String, true)
    // !! calls retrieveInt(String, true, 2) ).
    this.highest = retrieveInt("Highest value of a factor: ", true);
    this.numFactors = retrieveInt("Number of factors: ", true);
    // !! The lowest number of questions we allow to be 1, so we have
    // !! to call retrieveInt(String, boolean, int) directly. 
    this.numQuestions = retrieveInt("Number of questions: ", true, 1);
      
    System.out.println(); // Line break.
  }
  
  
  // Begin the program by calling this method. 
  // !! Returns the boolean stating whether the user would like to playAgain.
  public boolean startQuiz(){
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in);  
    
    // Record the start time in milliseconds. 
    long startTime = System.currentTimeMillis();
    
    // Repeat this action for each question, until numQuestions has
    // been reached. 
    for(int question = 1; question <= numQuestions; question++){
    
      // !! Note that we have tidied up the body of this for loop.
      // !! The question generation now takes place in the 
      // !! constructor of the QA object. This makes the code much
      // !! easier to read. 
    
      // !! Constructs a new QA object, which, during construction
      // !! will generate a question and the corresponding answer. 
      QA qa = new QA(); 
      
      // !! Obtain the user's response via the most basic version of 
      // !! retrieveInt (that without the minimum checking). 
      int response = this.retrieveInt(question + ") " + qa.getQuestion()); 
	
      // !! Checks whether the user got the correct answer by comparing
      // !! the variable response to the answer stored in the QA object
      // !! (which may be accessed by the getter method). 
      if(response == qa.getAnswer()) { 
	System.out.println("Correct!\n");
	// Given the user got the correct answer, add one to their score.
	score++;
      }
      // !! Use the QA's getAnswer method to output the answer. 
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
    
    // !! Find out if the user wants to play again, and return this value.
    return retrieveBool("Would you like to play again? [y/n]: ");
  
  } // End of startQuiz method. 
  
  // !! Here we introduce three overloaded methods called retrieveInt. The
  // !! purpose of doing this is to avoid repeating too much code. We need
  // !! to be able to retrieve integers both WITH and WITHOUT checking their
  // !! values. We can do this by writing two different methods (which will
  // !! have essentially the same code, except for the checking part), or,
  // !! as we have done here, introducing a boolean 'check' argument and
  // !! three methods, two of which are short, the other containing the 
  // !! bulk of the code. Their semantics are equivalent. 
  // !! 
  // !! In fact, having the two short methods is a bit of a luxury -- we 
  // !! do without them, as their only purpose is to call another method
  // !! with the same name. We have included them here to reduce the 
  // !! number of arguments required when invoking these methods, and to
  // !! demonstrate overloaded methods. 
  
  // !! Most basic method for retrieving an integer: it simply calls the
  // !! retrieveInt (String, boolean) method. Calling this NEVER invokes
  // !! minimum value checking (default: check == false). 
  public int retrieveInt(String prompt){ 
    return this.retrieveInt(prompt, false); 
  }
  
  // !! This is the second most basic method for retrieving an integer. 
  // !! It CAN carry out minimum value checking if check is set to 
  // !! true. The default value for the lowest allowable value is 2.
  // !! If check == false, this value of lowest is redundant. 
  public int retrieveInt(String prompt, boolean check){ 
    return this.retrieveInt(prompt, check, 2); 
  }
  
  // !! The method for retrieving an integer which admits most control. 
  // !! The prompt argument is what is printed to the standard out when
  // !! prompting the user. The check argument is to used to state whether
  // !! minimum checking should be invoked or not, when the user gives
  // !! their response. The argument lowest is to be used to validate the
  // !! user's response: if check == true and the user enters a value
  // !! less than lowest, (s)he will be reprompted for another value. 
  // !! If check == false, the argument lowest is redundant. 
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
	
	// !! If check == true, this clause checks whether the user's
	// !! response is less than lowest. If this is the case, then
	// !! the user is reprompted (by returning to the top of the 
	// !! while loop). If check == false, this clause will be 
	// !! ignored. 
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
  // !! This method is no longer static -- it can now be 
  // !! accessed from this object's startQuiz method. 
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
  
  // !! This is a private inner class. It can only be accessed from
  // !! within the non-static portions of this class. Its purpose is
  // !! to provide a neat object which encompasses both a question
  // !! and its corresponding answer. The constructor generates the
  // !! question and answer, using code very similar to the body of 
  // !! the for loop within startQuiz in previous Times Tables. 
  // !! An inner class enjoys the priveledge of being able to access
  // !! the instance members and methods contained within this 
  // !! TimesTable7 object. 
  // !! Note: QA stands for Question-Answer. 
  private class QA {
    
    // !! Question before it is generated. 
    String question = "";
    // !! Answer before it is calculated. It will be updated
    // !! in the constructor below. 
    int answer = 1; 
    
    // !! The constructor is used to generate the question (in
    // !! the form of a String) and the corresponding answer.
    // !! These will be assigned to the relevant instance 
    // !! members, and available to the getter methods post-
    // !! construction. 
    public QA(){
    
      // !! Note: the code here has changed very minimally
      // !! from when it was part of the for loop in the 
      // !! startQuiz method. 
      
      // Store the actual question as a String. This is only
      // the start of it, we will build it up gradually. 
      question += "What is ";
      
      // Iteratively, obtain the factors, calculate the answer,
      // and output the question to the user. 
      for(int factor = 1; factor <= numFactors; factor++){
      
	// The nextInt(highest) function returns an integer
	// between 0 and (highest - 1), so add 1 to put it 
	// in the correct interval. 
	int nextFactor = random.nextInt(highest) + 1;
	// Update the answer by multiplying it by the new factor.
	answer *= nextFactor;
	
	// Finish the question variable. 
	if(factor < numFactors) question += (nextFactor + " x ");
	else                    question += (nextFactor + "? "); 
      
      }
    }
    
    // !! Simple getters for accessing the instance members belonging
    // !! to this QA object.
    public String getQuestion(){ return question; }
    public int getAnswer(){ return answer; }
  
  
  }
  
  // The main method, the program is controlled from here. 
  // !! By moving the functionality into this Object's startQuiz
  // !! method, we have made the main method much tidier. 
  public static void main(String[] args){
  
    // A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
    
    // This is a variable which records whether the user wants 
    // to play again once the quiz has finished. 
    boolean playAgain;
    
    // Here we use a do..while loop to control the flow. Each 
    // cycle will perform one quiz. The while condition is 
    // based on the boolean variable playAgain. 
    do{
      
      // Construct and run the quiz. 
      // !! Use the default constructor. 
      TimesTable7 quiz = new TimesTable7();
      // !! Now the boolean playAgain is returned from the startQuiz method! 
      playAgain = quiz.startQuiz();           
    
    // Use the playAgain variable to decide whether to continue the loop
    // (i.e. play another quiz) or not. 
    } while(playAgain);
    
    // Goodbye message. This is displayed if the user typed n or N, i.e.
    // did not want to play again. 
    System.out.println("\n\n    ## Thanks for playing Times Table! ##\n\n");
  
  }

}
