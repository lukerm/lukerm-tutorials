package timestables; 

/*
Concrete subclass of Operator representing exponentiation. 
*/
public class Exponentiation extends Operator {

  boolean associative = false; 
  
  private String howToUse = "Exponentiation, e.g. 3^4 = 81.";

  public int operate(int operand1, int operand2){
    return ((int) Math.pow(operand1, operand2));
  }
  
  public String toString(){ return "^"; }

}