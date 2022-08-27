package timestables;

import java.util.Scanner;
import java.util.Random;

/*
This is the solution to the exercises from the sixth of the timestables. 

Note that these solutions will be persistent in the tutorials after this
one. In these solutions, we shall answer the following exercises:

Exercise 1: What happens when you, as the user, input a bad answer for 
the number of questions, say? Notice that a bad answer includes a 
trailing or preceding space. Can you fix this? You might also be able
to include a robustness procedure for the size of the values that the
user asks for in your solution. 

Quick Solution(s): 
  You may have to repeat an answer that you gave if, afterwards,
    you provide an invalid answer to the next question. 
  Use the static methods retrieveInt() and retrieveBool().
  Use the trim() after read.nextLine(). 
  For robustness use an appropriate try..catch in the main ()
    method (and the constructor, if you like!).

Exercise 2: Notice that when you do not enter a valid integer to a 
question, the program just interprets this as incorrect. Change 
the appropriate part of code so that, upon doing this, the program
reprompts the user for a valid answer. 

Quick Solution: 
  Use the try..catch within while loop construct that we met in 
  tutorial 6. The appropriate part of the code is approximately
  where the old answerStr variable was. 

Exercise 3: Include a timer function. That is, time how long it takes 
the user to get through a quiz, and give the result when the quiz
is finished. [Hint: you will want to make use of the method
System.currentTimeMillis(). ]

Quick Solution:
  Use currentTimeMillis at the start and end of startQuiz method,
  calculate the difference and report to the user. 

*/
public class TimesTable6Ex{

  static int tutorialNo = 6; 
  
  // Soft-coded instance member for the default global highest value.
  int highest = 10;
  // Soft-coded instance member for the default number of factors. 
  int numFactors = 2;
  // A soft-coded instance member for the number of questions that
  // the user will be asked. 
  int numQuestions = 15; 
  
  // A variable for keeping track of the user's score. 
  int score = 0; 
  
  // This is constructor, which "builds" the TimesTable object. 
  public TimesTable6Ex(int highest, int numFactors, int numQuestions)
    throws IllegalArgumentException{
  
    // !! This is for robustness. The highest value and number of
    // !! factors must be at least 2, whilst the number of questions
    // !! must be at least 1. The '||' means or. If any of these 
    // !! criteria are broken, we throw an appropriate Exception 
    // !! (an IllegalArgumentException). 
    // !! EXERCISE 1.
    if(highest <= 1 || numFactors <= 1 || numQuestions <= 0)
      throw new IllegalArgumentException(); 
  
    // Initialise the object's instance members by the arguments 
    // supplied to the constructor. 
    this.highest = highest;
    this.numFactors = numFactors;
    this.numQuestions = numQuestions; 
    
  }
  
  // Default constructor.
  public TimesTable6Ex(){}
  
  // Begin the program by calling this method. 
  public void startQuiz(){
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in); 
    
    // Random number generator. 
    Random random = new Random(); 
    
    // !! Record the start time in milliseconds. The variable type 
    // !! long is like int, but can hold much larger values. 
    // !! EXERCISE 3. 
    long startTime = System.currentTimeMillis();
    
    // Repeat this action for each question, until numQuestions has
    // been reached. 
    for(int question = 1; question <= numQuestions; question++){
    
      // This is the answer before it has been calculated. It 
      // will be updated below. 
      Integer answer = new Integer(1);
      
      // !! Store the actual question as a String. This is only
      // !! the start of it, we will build it up gradually. 
      // !! Note: We do this so that we can use it as an argument
      // !! in the retrieveInteger method. 
      String questionStr = question + ") What is ";
      
      // Iteratively, obtain the factors, calculate the answer,
      // and output the question to the user. 
      for(int factor = 1; factor <= numFactors; factor++){
      
	// The nextInt(highest) function returns an integer
	// between 0 and (highest - 1), so add 1 to put it 
	// in the correct interval. 
	int nextFactor = random.nextInt(highest) + 1;
	// Update the answer by multiplying it by the new factor. 
	// !! Note the use of the operator *=. This command is
	// !! equivalent to 'answer = answer*nextFactor;'.
	answer *= nextFactor;
	
	// Continue to output the question to the user. 
	// !! Note the use of the operator += which concatenates
	// !! and reassigns the value of questionStr. Very succint
	// !! syntax. 
	if(factor < numFactors) questionStr += (nextFactor + " x ");
	else                    questionStr += (nextFactor + "? "); 
      
      }
      
      // !! Obtain the user's response via the newly-created retrieve
      // !! Integer method.  
      int response = this.retrieveInteger(questionStr); 
	
      // !! Checks whether the user got the correct answer by comparing
      // !! the variables answer and response. This is a more robust way
      // !! of getting the answer. 
      // !! EXERCISE 2. 
      if(response == answer) { 
	System.out.println("Correct!\n");
	
	// Given the user got the correct answer, add one to their score.
	score++;
	
      }
      else System.out.println("Incorrect. The answer is " + answer + ".\n"); 
	
      // !! If we are timing the user, it no longer makes sense to halt
      // !! between each question. This part of the code has been removed.
      // !! EXERCISE 3. 
    
    } // End of questions for loop. 
    
    // !! When all the questions have been answered by the user,
    // !! record the time in milliseconds. 
    // !! EXERCISE 3. 
    long endTime = System.currentTimeMillis();
    
    // The quiz has concluded, inform the user of their score. 
    System.out.println("End of quiz. You scored " + score + " out of " + numQuestions + ".");
    // !! Report the user's time to complete the quiz. 
    // !! Calculate the total time (in seconds) by taking the 
    // !! difference of endTime and startTime, and divide by
    // !! 1000. Round this number. This can all be done within
    // !! the String concatenation. 
    // !! EXERCISE 3. 
    System.out.println("It took you " + Math.round((endTime - startTime)/1000) + " seconds.\n");
  
  } // End of startQuiz method. 
  
  
  // !! This method is the non-static version of retrieveInt -- 
  // !! we have to give it a different name (or a different
  // !! set of arguments). As it is very similar to that 
  // !! method, the comments have been suppressed. 
  public int retrieveInteger(String prompt){
   
    Scanner read = new Scanner(System.in);
    int toReturn;
   
    while(true){
      try{ 
	System.out.print(prompt);	 
	toReturn = Integer.parseInt(read.nextLine().trim());
	// !! Note: we have removed the safety check. 
	break;
      } 
      catch(NumberFormatException e){
	System.out.println("Invalid number.");
      }
    }  
    return toReturn; 
  }
  

  // The main method, the program is controlled from here. 
  // !! We have put much of the code into various STATIC
  // !! methods, thereby making this main method much 
  // !! tidier, and therefore easier to read. 
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
    
      // !! We could have just written three while loops containing
      // !! try..catch statements to solve EXERCISE 1. But, this 
      // !! would have been bad programming style because each of 
      // !! the loops would have performed a very similar task. In
      // !! fact, the tasks are so similar that we can create a 
      // !! SINGLE method (retrieveInt) to carry out all three 
      // !! tasks for us. This has the added benefit of tidying up
      // !! the code, and making it much easier to read. 
    
      // !! Obtain the arguments for this quiz from the user, via
      // !! the retrieveInt method. Each time we provide an
      // !! appropriate prompt to the method. 
      // !! Note: We declare and initialise the relevant variables
      // !! in the same command. 
      // !! EXERCISE 1. 
      int highest = retrieveInt("Highest value of a factor: ");
      int nFactors = retrieveInt("Number of factors: ");
      int nQuestions = retrieveInt("Number of questions: ");
      
      System.out.println(); // Line break.

      // Start the quiz. 
      
      // Construct and run the quiz. 
      TimesTable6Ex quiz = new TimesTable6Ex(highest, nFactors, nQuestions);
      quiz.startQuiz();           
      
      // !! Decide whether to play again or not. Obtain the playAgain 
      // !! boolean via the method retrieveBool. We have done this to
      // !! tidy up the code -- it was not required in any of the 
      // !! exercises. 
      playAgain = retrieveBool("Would you like to play again? [y/n]: ");
    
    // Use the playAgain variable to decide whether to continue the loop
    // (i.e. play another quiz) or not. 
    } while(playAgain);
    
    // Goodbye message. This is displayed if the user typed n or N, i.e.
    // did not want to play again. 
    System.out.println("\n\n    ## Thanks for playing Times Table! ##\n\n");
  
  }
  
  // !! A static method which obtains in a single integer from the 
  // !! user in a robust way. The String argument prompt tells the
  // !! user what number they are being asked for. 
  // !! EXERCISE 1. 
  public static int retrieveInt(String prompt){
  
    // !! This is Scanner object for reading the user's responses. 
    Scanner read = new Scanner(System.in);
    
    // !! A variable that will eventually store the user's response. 
    int toReturn;
  
    // !! This is precisely the control flow that we had in the 
    // !! previous tutorial, except that only one number is being
    // !! asked for, instead of three. This has the effect that
    // !! no repetition of questions will occur. 
    while(true){
    
      try{ 
	// !! Prompt the user for the value needed
	System.out.print(prompt);
	
	// !! Get the user's response and parse it. If it is 
	// !! invalid, the catch block below will catch the 
	// !! NumberFormatException, and reprompt the user. 
	// !! Note that the trim() method has been used to 
	// !! avoid extra whitespace causing an Exception.
	// !! EXERCISE 1. 
	toReturn = Integer.parseInt(read.nextLine().trim());
	
	// !! Robustness.
	// !! This ensures that all answers are greater than 1,
	// !! and that non-sensical values like -1 are not 
	// !! accepted.  
	if(toReturn <= 1) {
	  System.out.println("Must be greater than 1.");
	  continue; 	
	}
	
	// !! Note that this blocks nQuestion from being 1 -- 
	// !! could create a slightly more general method to
	// !! fix this. This will be addressed in the next
	// !! tutorial.
	
	// !! The value was successfully obtained, so break
	// !! from the while loop. 
	break;
	
      }
      // !! Catch any Exceptions generated from the parseInt
      // !! method above. 
      catch(NumberFormatException e){
	System.out.println("Invalid number.");
      }
      
    } // End of while. 
    
    // !! Return statement.
    return toReturn; 
  
  }
  
  // !! This is semantically similar to the retrieveInt method
  // !! above, except we obtain a boolean from the user's 
  // !! response rather than an int. The control flow is 
  // !! exactly the same as it was when it was in the main
  // !! main method.
  public static boolean retrieveBool(String prompt){ 
    
    // !! Scanner to read the user's response. 
    Scanner read = new Scanner (System.in);
    
    while(true){
      
	// !! Prompt the user for a response.
	System.out.print(prompt);
	String resp = read.nextLine().trim(); 
	
	// !! Decide what to do based on this response. If the user
	// !! has entered y or Y, (s)he wants to play again, so
	// !! return true. 
	if(resp.equalsIgnoreCase("y")){
	  // !! This automatically breaks from the while loop, 
	  // !! AND returns the value true. 
	  return true; 
	  // !! No need for 'break;'. 
	}
	// !! Conversely, if the user enters n or N, (s)he does not 
	// !! want to play again, so return false. 
	else if(resp.equalsIgnoreCase("n")){
	  // !! This automatically breaks from the while loop, 
	  // !! AND returns the value false. 
	  return false; 
	  // !! No need for 'break;'
	}
	// !! If the user puts anything else (aside from y,Y,n or N), 
	// !! this counts as an illegal command, so we give a suitable
	// !! message and return to the top of the while loop. 
	else System.out.println("Invalid response. Please type y or n.");
      
    }
    
    // !! We do not need a return statement here, as the 
    // !! method is guaranteed to have returned a boolean
    // !! by now. 
    
  }

}
