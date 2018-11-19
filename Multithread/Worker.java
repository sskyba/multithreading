package Multithread;

import java.util.*;

// Worker class implements a thread with prime numbers finding within the given range
class Worker extends Thread {
  private final String threadName;
  private final int startNumber;
  private final int endNumber;
  private final ArrayList<Integer> lResults = new ArrayList();

  Worker(String name, int nStart, int nEnd) {
    startNumber = nStart;
    endNumber = nEnd;
    threadName = name;
    System.out.println("New thread: " + threadName);
    this.start();
  }
  
  // private function to determine if the given number is prime
  private boolean isPrimeNumber(int n) {
    for (int i=2; i<n/2; ++i) if (n % i == 0) return false;
    return true;
  }
  
  public ArrayList<Integer> getResults() {
    return lResults;
  }
  
  // Thread body: the prime number finding
  @Override
  public void run() {
    for (int i=startNumber; i<=endNumber; i++) {
      if (isPrimeNumber(i)) {
// uncomment the next line to view all the numbers added
//        System.out.println("Thread " + threadName + " adding number " + i);
        lResults.add(i);
      }
    }
    ThreadController.addResults(lResults); 
    System.out.println(threadName + " exiting, added " + lResults.size() + " numbers to the result");
  }

}
