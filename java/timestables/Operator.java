package timestables;

/*
This is an ABSTRACT class for representing types of 
binary operations. For example, multiplication. 
Objects of this type cannot be constructed, as this
class has the modifier abstract. Only concrete 
subclasses of this class may be constructed. 

There are two abstract methods: 

int operate(int, int),
String toString(),

which must be overridden in concrete subclasses. The
first actually performs the operation (so if we are
representing multiplication, operate(3,4) will return
12, whereas for addition, it would return 7), whilst
the second is a String representation of the operator
(e.g. multiplication: "x", addition: "+").

The reason we have made this class abstract is because,
on its own, there is no notion of operation. We could
not define the operate method in this class, because 
how would we define it? Instead, we leave it to concrete
subclasses to override.
*/
public abstract class Operator {

  // A boolean representing whether or not this Operator
  // is associative. It should be overridden in the 
  // constructor of a concrete subclass. A binary 
  // Operator $ is associative if, for all triples (a,b,c),
  //
  // ((a $ b) $ c) = (a $ (b $ c)).
  //
  protected boolean associative;
  
  // This String should explain how to use this Operator.
  // It can be a definition or an example, as long as it
  // gives enough information to explain the use of it. 
  // Should be overridden in concrete subclasses' constructors. 
  protected String howToUse = "";
  
  // Getter for associative. 
  public boolean isAssociative(){ return associative; }
  // Getter for howToUse. 
  public String getHowToUse(){ return howToUse; }

  // Abstract method for representing the actual operation.
  // This must be overridden in concrete subclasses, or a
  // compilation error will occur. It returns an integer 
  // which is the result of a $ b, where $ is the Operator
  // and a,b are integers. 
  public abstract int operate(int operand1, int operand2); 
  
  // Abstract method for the String representation of this
  // Operator, e.g. "x" for multiplication, or "+" for
  // addition. 
  public abstract String toString();

}