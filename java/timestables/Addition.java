package timestables; 

/*
Concrete subclass of Operator representing addition. 
*/
public class Addition extends Operator {

  public Addition(){
    this.associative = true;
    this.howToUse = "Standard addition, e.g. 3 + 4 = 7.";
  }

  public int operate(int operand1, int operand2){
    return operand1 + operand2;
  }
  
  public String toString(){ return "+"; }

}