package recursion;

public class ClockTest {

  public static void main(String[] args){
  
    int[] max = {3,3,3};
    Clock clock = new Clock(max);
    // Prints and ticks the clock until the
    // end of the day is reached. This will 
    // throw an EndOfDayException (which is
    // not caught by this code). 
    while(true){
      System.out.println(clock);
      clock.tick();
    }
  
  }


}
