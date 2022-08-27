package timestables;

import java.util.Scanner;
import java.util.Random;

/*
This is the sixth of the timestables. 

In the this tutorial, we shall improve the interface with the user,
introducing Exception objects and the concept of throwing and 
catching Exceptions. In particular, we will only welcome the user
once (rather than twice in the previous one), and allow the user 
to play for as long as they like, with arguments of their choice. 
The important editions to the code are in the main method. 

In the next tutorial, we look at ways to make our code more efficient
and tidy. This will promote compartmentalization into small units, 
achieved through methods. 

Exercise 1: What happens when you, as the user, input a bad answer for 
the number of questions, say? Notice that a bad answer includes a 
trailing or preceding space. Can you fix this? You might also be able
to include a robustness procedure for the size of the values that the
user asks for in your solution. 
[The solution could include a method to streamline the code.]
[Use the trim() method.]

Exercise 2: Notice that when you do not enter a valid integer to a 
question, the program just interprets this as incorrect. Change 
the appropriate part of code so that, upon doing this, the program
reprompts the user for a valid answer. 

Exercise 3: Include a timer function. That is, time how long it takes 
the user to get through a quiz, and give the result when the quiz
is finished. [Hint: you will want to make use of the method
System.currentTimeMillis(). ]

*/
public class TimesTable6{

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
  public TimesTable6(int highest, int numFactors, int numQuestions){
  
    // Initialise the object's instance members by the arguments 
    // supplied to the constructor. 
    this.highest = highest;
    this.numFactors = numFactors;
    this.numQuestions = numQuestions; 
    
    // We have not provided any robustness methods here.
    // Each of these values should be integers GREATER THAN 1.
    
  }
  
  // Default constructor.
  public TimesTable6(){}
  
  // Begin the program by calling this method. 
  public void startQuiz(){
  
    // !! The title has been removed from this method, and placed
    // !! in the main method. 
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in); 
    
    // Random number generator. 
    Random random = new Random(); 
    
    // Repeat this action for each question, until numQuestions has
    // been reached. 
    for(int question = 1; question <= numQuestions; question++){
    
      // This is the answer before it has been calculated. It 
      // will be updated below. 
      Integer answer = new Integer(1);
      
      // Output the start of the question to the user. 
      System.out.print(question + ") What is ");
      
      // Iteratively, obtain the factors, calculate the answer,
      // and output the question to the user. 
      for(int factor = 1; factor <= numFactors; factor++){
      
	// The nextInt(highest) function returns an integer
	// between 0 and (highest - 1), so add 1 to put it 
	// in the correct interval. 
	int nextFactor = random.nextInt(highest) + 1;
	// Update the answer by multiplying it by the new factor. 
	answer = answer*nextFactor;
	
	// Continue to output the question to the user. 
	if(factor < numFactors) System.out.print(nextFactor + " x ");
	else                    System.out.print(nextFactor + "? "); 
      
      }
      
      // Wait for the user to enter their response, which is then
      // assigned to the variable response. (The trim method takes
      // away any blank spaces at the start or end of the response.) 
      String response = read.nextLine().trim(); 
      
      // Use the toString method of the Integer-object answer to convert
      // it into a String representation of it, stored in the String-type
      // variable answerStr.
      String answerStr = answer.toString();
	
      // Checks whether the Strings answerStr and response are textually 
      // equal. If they are, the user must have supplied the correct 
      // answer, and gets 1 added to their score. 
      if(response.equals(answerStr)) { 
	System.out.println("Correct!\n");
	
	// Given the user got the correct answer, add one to their score.
	score++;
	
      }
      else System.out.println("Incorrect. The answer is " + answer + ".\n"); 
	
      // This makes the program sleep for a given number of milliseconds.
      // Not expected to understand this yet!
      try{Thread.sleep(1000);}
      catch(InterruptedException e){}
    
    } // End of questions for loop. 
    
    // The quiz has concluded, inform the user of their score. 
    System.out.println("End of quiz. You scored " + score + " out of " + numQuestions + ".\n"); 
  
  } // End of startQuiz method. 
  

  // The main method, the program is controlled from here. 
  public static void main(String[] args){
  
    // !! A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
    
    // !! This is Scanner object for reading the user's responses.
    // !! Note: We have defined (so it would seem) the very same 
    // !! variable in the startQuiz method: a Scanner named read.
    // !! How can this be? It is because the main() and startQuiz()
    // !! methods have different scopes. So, when startQuiz is 
    // !! called from within main, the Scanner in startQuiz HIDES
    // !! the variable in main. This continues to happen until 
    // !! the control returns to main (i.e. startQuiz has finished)
    // !! when this read is visible again. 
    Scanner read = new Scanner(System.in);
    
    // !! This is a variable which records whether the user wants 
    // !! to play again once the quiz has finished. 
    boolean playAgain;
    
    // !! Here we use a do..while loop to control the flow. Each 
    // !! cycle will perform one quiz. The while condition is 
    // !! based on the boolean variable playAgain. 
    // !! The reason we use a do..while rather than just a while
    // !! loop is because we want the program to always play at
    // !! least one game.
    do{
    
      // !! Obtain the arguments for this quiz from the user. 
    
      // !! Variables that will store the users desired number
      // !! of factors, highest value and number of questions
      // !! respectively, for this quiz. 
      int nFactors, highest, nQuestions; 
      
      // !! In order to obtain the arguments in a robust way,
      // !! we need quite a complicated control system. 
      
      // !! Firstly, note that we have a while loop within 
      // !! another while loop. 
      
      // !! Secondly, we have used 'true' in the while 
      // !! condition, which means that it will ALWAYS
      // !! evaluate to true (i.e. run forever). The only
      // !! way of escaping this loop in this scenario is
      // !! to encounter a 'break' command, which immediately
      // !! exits the while loop. 
      
      // !! Thirdly, we have a try..catch statement inside the 
      // !! while loop. There is a chance that that user will
      // !! input a bad number (such as 'f'), which will cause
      // !! the parseInt method to fail and THROW an Exception
      // !! (in particular, a NumberFormatException -- see the 
      // !! Javadoc for Integer). If this were to happen and 
      // !! that command were not in an appropriate try..catch
      // !! statement, the Exception would override all code 
      // !! after it until it reached the end of the program, 
      // !! causing it to terminate. However, having it within
      // !! a try{} means that only the code in the rest of that
      // !! try block will be overriden, until the Exception is
      // !! caught in the catch{} block, whereupon the code there
      // !! will be executed. 
      
      // !! Fourthly, if a NumberFormatException is thrown, it
      // !! will be caught, and since the catch{} block does not
      // !! have the 'break' command in it, the control will 
      // !! return to the start of the while loop, and the process
      // !! starts afresh. 
      
      // !! This while loop is intended to obtain the arguments
      // !! for this quiz from the user in a robust way, using 
      // !! a try..catch statement. 
      while(true){
	
	// !! The code in this try block will be immune to 
	// !! any NumberFormatExceptions that are thrown 
	// !! therein (which will necessarily be caused by
	// !! the user entering a bad number). 
	try{
	  
	  // !! Obtain the three arguments by, one by one,
	  // !! prompting and parsing the user's String 
	  // !! response to an integer. This will fail if 
	  // !! the user's response is not an integer, or
	  // !! has any trailing or preceding whitespace. 
	  
	  System.out.print("Number of factors: ");
	  nFactors = Integer.parseInt(read.nextLine());
	  
	  System.out.print("Highest value of a factor: ");
	  highest = Integer.parseInt(read.nextLine());
	  
	  System.out.print("Number of questions: ");
	  nQuestions = Integer.parseInt(read.nextLine());
	  
	  System.out.println(); // Line break.
	  
	  // !! The arguments have been obtained, so we 
	  // !! break from the while loop. We need this
	  // !! statement otherwise the inner while loop
	  // !! would run forever!
	  break; 
	  
	}
	// !! This code is executed if a NumberFormatException
	// !! is thrown during runtime. It tells the user that
	// !! their response is illegal and returns to the top
	// !! of the while loop (which is what 'continue' does). 
	// !! Note: The 'continue' command is not really 
	// !! required here, it would have happened anyway. But
	// !! do note that 'continue' is a reserved word, and 
	// !! may not be used as a variable or method name. 
	catch(NumberFormatException e){ 
	  System.out.println("Invalid number."); continue; 
	}
      
      }
      
      // !! Start the quiz. 
      
      // !! Construct and run the quiz. 
      TimesTable6 quiz = new TimesTable6(highest, nFactors, nQuestions);
      quiz.startQuiz();     
      
      
      // !! Decide whether to play again or not.
      
      // !! This while loop is semantically very similar to the inner
      // !! while loop above. This time we do not need a try..catch
      // !! statement within because we do not parse any integers -- 
      // !! we can just use the String response as is in order to 
      // !! judge what playAgain should be, or if an illegal response
      // !! has been entered. 
      while(true){
      
	// !! Prompt the user for a response.
	System.out.print("Would you like to play again? [y/n]: ");
	String resp = read.nextLine(); 
	System.out.println(); // Line break.
	
	// !! Decide what to do based on this response. If the user
	// !! has entered y or Y, (s)he wants to play again, so set
	// !! playAgain to true. 
	if(resp.equalsIgnoreCase("y")){
	  playAgain = true; 
	  // !! Break from the while loop!
	  break; 
	}
	// !! Conversely, if the user enters n or N, (s)he does not 
	// !! want to play again, so set playAgain to false.
	else if(resp.equalsIgnoreCase("n")){
	  playAgain = false; 
	  // !! Break from the while loop!
	  break;
	}
	// !! If the user puts anything else (aside from y,Y,n or N), 
	// !! this counts as an illegal command, so we give a suitable
	// !! message and return to the top of the while loop. 
	else System.out.println("Invalid response. Please type y or n.");
      
      }  
    
    // !! Use the playAgain variable to decide whether to continue the loop
    // !! (i.e. play another quiz) or not. Note that we don't actually need 
    // !! the '== true' part. 
    } while(playAgain == true);
    
    // !! Goodbye message. This is displayed if the user typed n or N, i.e.
    // !! did not want to play again. 
    System.out.println("\n\n    ## Thanks for playing Times Table! ##\n\n");
  
  }

}
