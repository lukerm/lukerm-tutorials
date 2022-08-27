package timestables; 

/*
Concrete subclass of Operator representing subtraction. 
*/
public class Modulo extends Operator {

  public Modulo(){
    this.associative = false;
    this.howToUse = "a mod b is the remainder after dividing a by b.\n"
                  + "The answer must be in the range 0,1,...,(b - 1).\n"
                  + "E.g., 7 mod 3 = 1 (since 7 = (2 x 3) + 1).";
  }

  public int operate(int operand1, int operand2){
    return operand1 % operand2;
  }
  
  public String toString(){ return "mod"; }

}