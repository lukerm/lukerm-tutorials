package timestables;

/*
Concrete subclass of Operator representing the operation of
greatest common divisor. 
*/
public class GreatestCommonDivisor extends Operator {

  public GreatestCommonDivisor(){
    this.associative = true; 
    this.howToUse = "The greatest common divisor "
                  + "of two integers. E.g., 6 gcd 9 = 3.";
  } 

  // This method implements Euclid's algorithm for finding
  // the gcd of operand1 and operand2. 
  public int operate(int operand1, int operand2){
  
    int p,q; 
    
    // We must apply Euclid's algorithm to the ORDERED pair. 
    // So let p > q. 
    if(operand1 > operand2){ p = operand1; q = operand2; }
    else if(operand1 < operand2){ p = operand2; q = operand1; }
    else return operand1; 
    
    // Apply Euclid's algorithm. Code found at:
    // http://www.vogella.com/articles/JavaAlgorithmsEuclid/article.html
    if (q == 0) return p;
    // Elegant use of recursion. 
    return operate(q, p % q);
  }
  
  public String toString(){ return "gcd"; }

}