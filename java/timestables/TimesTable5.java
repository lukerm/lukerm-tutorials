package timestables;

import java.util.Scanner;
import java.util.Random;

/*
This is the fifth of the timestables. 

In this tutorial, we shall see how to turn the Times Table into
an object in such a way that suits Object Oriented Programming. We
will also keep track of the user's score. 

To make things more interesting, we now allow more than two factors.
The number of factors, along with the global highest number, can
be specified using a constructor. Unlike before, this allows us to
to create more than just one Times Table in a single program run. 
Each of the factors will be chosen at random, using a random number
generator object Random. 

In the next tutorial, we shall improve the interface with the user,
introducing Exception objects and the concept of throwing and 
catching Exceptions. 
*/
public class TimesTable5{
  
  // Soft-coded instance member for the default highest value.
  // !! Now that we can have more than two factors (see below),
  // !! we now revert back to the global highest variable, as
  // !! in the first tutorial. 
  int highest = 10;
  // !! Soft-coded instance member for the default number of factors.
  // !! This can now be greater than two. 
  int numFactors = 2;
  // !! A soft-coded instance member for the number of questions that
  // !! the user will be asked. 
  int numQuestions = 15; 
  
  // !! A variable for keeping track of the user's score. 
  int score = 0; 
  
  // !! This is a constructor, which "builds" the TimesTable object. 
  public TimesTable5(int highest, int numFactors, int numQuestions){
  
    // Initialise the object's instance members by the arguments 
    // supplied to the constructor. 
    this.highest = highest;
    this.numFactors = numFactors;
    this.numQuestions = numQuestions; 
    
    // !! We have not provided any robustness methods here. Really,
    // !! each of these values should be integers GREATER THAN 1, 
    // !! but there is nothing stopping the program from supplying 
    // !! values such as the non-sensical value -1 for numFactors!
    // !! If this were to happen, the program would almost certainly
    // !! break down. For the moment, we assume that reasonable values 
    // !! will be supplied. 
    
  }
  
  // !! Default constructor. This constructs an object with the 
  // !! default parameters. 
  public TimesTable5(){}
  
  // !! Begin the program by calling this method. 
  public void startQuiz(){
  
    // A title for when the program starts.
    System.out.println("\n\n    ## Welcome to the Times Table! ##\n\n");
  
    // Needed to read the response of the user. 
    Scanner read = new Scanner (System.in); 
    
    // !! This is the Random object that generates the random numbers.
    Random random = new Random(); 
    
    // !! Repeat this action for each question, until numQuestions has
    // !! been reached. 
    for(int question = 1; question <= numQuestions; question++){
    
      // !! This is the answer before it has been calculated. It 
      // !! will be updated below. 
      Integer answer = new Integer(1);
      
      // !! Output the start of the question to the user. 
      System.out.print(question + ") What is ");
      
      // !! Iteratively, obtain the factors, calculate the answer,
      // !! and output the question to the user. 
      for(int factor = 1; factor <= numFactors; factor++){
      
	// !! The nextInt(highest) function returns an integer
	// !! between 0 and (highest - 1), so add 1 to put it 
	// !! in the correct interval. 
	int nextFactor = random.nextInt(highest) + 1;
	// !! Update the answer by multiplying it by the new factor. 
	answer = answer*nextFactor;
	
	// !! Continue to output the question to the user. 
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
	
	// !! Given the user got the correct answer, add one to their score.
	// !! (Note: this command is the comment "increment" command, and is
	// !! equivalent to 'score++', seen in the for loop syntax.)
	score = score+1;
	
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
    
    // !! Now we can create different TimesTable5 OBJECTS within 
    // !! the same program. Moreover, we can run the startQuiz
    // !! method in those objects to get interactive quiz. 
    
    // !! These commands set up two TimesTable5 objects, each with 
    // !! different parameters, and runs the quizes, one after 
    // !! another. The first is the easiest, with two factors, 
    // !! each of which can be at most 10. The second is more
    // !! difficult, with three factors, each of which can be at
    // !! most 12.
    TimesTable5 quiz1 = new TimesTable5(10, 2, 10);
    quiz1.startQuiz();
    
    TimesTable5 quiz2 = new TimesTable5(12, 3, 10);
    quiz2.startQuiz();
    
  
  }

}
