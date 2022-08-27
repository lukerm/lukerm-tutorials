package timestables;

/*
Concrete subclass of Operator representing multiplication. 
*/
public class Multiplication extends Operator {

  public Multiplication(){
    this.associative = true; 
    this.howToUse = "Standard multiplication, e.g. 3 x 4 = 12.";
  } 

  public int operate(int operand1, int operand2){
    return operand1*operand2;
  }
  
  public String toString(){ return "x"; }

}