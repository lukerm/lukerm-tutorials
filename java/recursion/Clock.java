package recursion;

import java.math.BigInteger;
import static java.math.BigInteger.*;
/*
This class represents objects of type Clock. The Clock can be operated in a
similar fashion to a usual clock -- the last unit ticks until it hits some
value, at which point a new tick will reset the last unit to zero, but
increment the second largest value. Unlike a real Clock, the number of units
and their sizes are arbitrary (as long as all are positive). 

Upon construction, the "time" of the Clock is set to zero (i.e. every unit 
is zero). One cause the clock to "tick" by calling the tick() method -- this
method will ensure that the appropriate internal mechanics happen. One can
use getter methods to obtain specs for the clock, but the main one of 
interest is getCurrentTime() which returns an int[] representing the current
state of all the units in the Clock.

Try the following example code (in a main method -- implemented in ClockTest.java)
to understand the internal mechanics of the Clock:

int[] max = {3,3,3};
Clock clock = new Clock(max);
while(true){
  System.out.println(clock);
  clock.tick();
}

Note that the suprema are really just that -- the corresponding clock unit 
will never reach that supremum, but will go back to zero before it reaches it.
(This is the same as the semantics of a real clock, the seconds never actually
reach 60, but go from 59 to 0.) 
 
Note: by setting each of the suprema to 2, we can iterate through the binary
numbers from 0 to 2^{length of suprema[]}-1 (in base 10).

*/
public class Clock {
    
    private int numberOfUnits; // How many different units the Clock will have
                               // or the number of "hands" (plus 1).
    private int[] suprema; // An array specifying when that unit should return 
                           // back to zero (this is the modulo number). All values
                           // should be greater than or equal to 1, this is a 
                           // precondition. 
    private BigInteger numberOfSeconds; // The size of the clock, counts the total 
                                  // number of "seconds" in a "day". Calculated
                                  // by multiplying together all entries in suprema.
    private int[] currentTime; // The current state/"time" of the Clock, initially
                               // at zero. 
    
    // The argument specifies the both the number of units (its length) and what
    // are the maximum (supremum) values for each unit. E.g. For time in an actual
    // day, this would be {24, 60, 60}. Initialise the clock at time zero, and 
    // calculate the number of "seconds" that this Clock records.
    public Clock(int[] suprema){
        
        this.numberOfUnits = suprema.length;
        this.suprema = suprema;
        // Calculate number of seconds -- multiply together all entries in suprema.
        BigInteger noSeconds = BigInteger.ONE;
        for(int unit=0; unit<suprema.length; unit++) noSeconds = noSeconds.multiply(valueOf(suprema[unit]));
        this.numberOfSeconds = noSeconds;
        // Initialize the currentTime to zero. 
        int[] initialTime = new int[numberOfUnits];
        for(int unit=0; unit < numberOfUnits; unit++) initialTime[unit] = 0;
        this.currentTime = initialTime;
    
    }
    
    // Makes the Clock "tick", thereby incrementing the "time". This is done
    // via the private method tick(int unit). 
    public void tick() throws EndOfDayException {
    
        // ALWAYS call tick(int) with the highest unit index available -- it will
        // handle the other indices itself. This method may throw an EndOfDay-
        // Exception, which we allow to be thrown forward.
        tick(numberOfUnits - 1);
    
    }
    
    // Private method which actually performs the ticking, i.e. incrementing the
    // elements of currentTime in the appropriate way. This method will increment
    // the entry of currentTime equal to unit. However, each unit has an upper
    // bound, and so if it is the case that that upper is hit, then the next entry
    // to the left must also be incremented; and the same description goes for 
    // that entry. This method is very much geared up to recursive use. The method
    // should only be called by the USER with unit equal to the "second", i.e.
    // the unit with the highest index, though it may be called multiple times
    // by itself. Once this method has finished running, the currentTime will 
    // have been updated in the appropriate way. 
    // Note that even if an EndOfDayException is thrown, the clock is still useful,
    // as it will reset itself to zero in this case. 
    private void tick(int unit) throws EndOfDayException {
        
        // This implies that the method has been called recursively and all the 
        // units have hit their suprema, and this event represents the end of the
        // day, so throw the EndOfDayException to represent that. This is the 
        // terminal condition for the recursion. 
        if(unit < 0) {
            for(int i=0; i<numberOfUnits; i++) currentTime[i] = 0; // Reset clock.
            throw new EndOfDayException(); 
        }
    
        // Increment the specified unit by one. If such an action means that the 
        // unit hits its supremum, then it needs to be set to zero, and the unit
        // to its left must be incremented in the same way -- hence the recursive 
        // call to this method, but with a decremented index. 
        currentTime[unit]++;
        if(currentTime[unit] == suprema[unit]){
            currentTime[unit] = 0; tick(unit - 1);
        } 
    }
    
    // A String representation of the current time. 
    @Override
    public String toString(){
      
      String toReturn = "("; 
      // Concatenate each of the units in turn. 
      for(int i=0; i < numberOfUnits-1; i++)
	toReturn += (currentTime[i] + ", "); 
      // Final unit.
      toReturn += (currentTime[numberOfUnits-1] + ")");
    
      return toReturn; // Return statement.
    
    }
    
    // Getters (there are no setters):

    // Gets the current state of the Clock. This is done without making the Clock
    // tick, so tick() should be called first if so desired. We return a COPY
    // of the actual time rather than a pointer to the currentTime variable. Thus,
    // the returned "time" is fixed until it is changed outside of the Clock
    // methods (returning a pointer causes the RuleStrategies to evolve as the 
    // time ticks, which is not the desired phenomenon.
    public int[] getCurrentTime() {
        int[] currentTimeCopy = new int[numberOfUnits];
        for(int i=0; i<numberOfUnits; i++) currentTimeCopy[i] = currentTime[i];
        return currentTimeCopy;
    }

    public BigInteger getNumberOfSeconds() {
        return numberOfSeconds;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public int[] getSuprema() {
        return suprema;
    }
    
    // The Exception that is thrown when all the units have 
    // been maxed out and a tick is required.
    public class EndOfDayException extends RuntimeException{
    
      public EndOfDayException(){ super(); }
    
    }
    
}
