package timestables; 

/*
Concrete subclass of Operator representing subtraction. 
*/
public class Subtraction extends Operator {

  public Subtraction(){
    this.associative = false;
    this.howToUse = "Standard subtraction, e.g. 3 - 4 = -1.";
  }

  public int operate(int operand1, int operand2){
    return operand1 - operand2;
  }
  
  public String toString(){ return "-"; }

}