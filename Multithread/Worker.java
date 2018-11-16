package Multithread;

import java.util.*;

// Worker class implements a thread with prime numbers finding within the given range
class Worker extends Thread {
  private String threadName = "";
  private int startNumber;
  private int endNumber;
  private ArrayList<Integer> lResult = new ArrayList();

  Worker(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }
  
  // private function to determine if the given number is prime
  private boolean isPrimeNumber(int n) {
    for (int i=2; i<n/2; ++i) if (n % i == 0) return false;
    return true;
  }
  
  // Thread body: the prime number finding
  @Override
  public void run() {
    System.out.println("Running " + threadName );
    for (int i=startNumber; i<=endNumber; i++) {
      if (isPrimeNumber(i)) {
        System.out.println("Thread " + this.getName() + " adding number " + i);
        lResult.add(i);
      }
    }
   }
  
  // main method to initialize the thread and start running 
  public ArrayList<Integer> start(int nStart, int nEnd) {
    startNumber = nStart;
    endNumber = nEnd;
    run();
    return lResult;
  }

}
