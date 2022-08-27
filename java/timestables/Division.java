package timestables; 

import java.util.ArrayList;

/*
Concrete subclass of Operator representing division.
We are primarily concerned with integer arithmetic,
so this class defines a function which, upon receiving
an integer, will return all its positive factors. 
*/
public class Division extends Operator {

  public Division(){
    this.associative = false;
    this.howToUse = "Standard division, e.g. 6%2 = 3 or -6%2 = -3.";
  }

  public int operate(int operand1, int operand2){
    return operand1/operand2;
  }
  
  public String toString(){ return "%"; }
  
  // !! This method is bespoke for the Division class.
  // !! Since we are interested in integer arithmetic,
  // !! we want to divide a number by one of its factors
  // !! so that the answer is also an integer. This 
  // !! will return all the POSITIVE factors of value. 
  // !! We do not include 1, but do include |value|,
  // !! meaning that the returned array will always 
  // !! have at least one factor. The returned array
  // !! will NOT be ordered. 
  public ArrayList<Integer> getFactors(int value){
  
    // !! Use a dynamic array, because, a priori, we 
    // !! don't know how many factors there will be. 
    // !! Note: we have to use Integer rather than
    // !! int because primitive types are not accepted. 
    ArrayList<Integer> toReturn = new ArrayList<Integer>();
  
    // !! In the unique case that value == 0, return 
    // !! only the value 1. 
    if(value == 0) {
      toReturn.add(1); return toReturn; 
    }
    // !! Otherwise, add the absolute value of value
    // !! to the list. 
    else toReturn.add(Math.abs(value));
    
    // !! This loops from 2 until half of value. Each
    // !! i is tested to see if it divides value (using
    // !! the modulo (%) command). If it is, then it is
    // !! added to the toReturn list. 
    for(int i=2; i<=value/2; i++){
      if((Math.abs(value) % i) == 0) toReturn.add(i);
    }
    
    return toReturn; // Return statement. 
  
  }

}