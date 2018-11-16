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
    System.out.println("Creating " + threadName );
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
    return this.lResult;
  }

}

// main class of the application
public class ThreadController {
  
  // max limit for the prime number findings
  static final int nMaxNumber = 1000000;
  
  // size of the chunk per thread
  static final int nStep = 100000;
  
  // pointer to the number of already processed numbers
  static int nNumberCount = 0;
  
  // max number of the threads allowed
  static final int nThreadMax = 3;
  
  // list of prime numbers (end result of the app)
  static ArrayList<Integer> lResult = new ArrayList();
  
  // list of threads
  static ArrayList<Worker> lThreads = new ArrayList();

  // checks if all the threads are finished
  private static boolean allThreadsFinished() {
    for (int i=0; i<nThreadMax; i++) if (lThreads.get(i).isAlive()) return false;
    return true;
  }

  public static void main(String args[]) {
    // initializing lThreads array: creating all the threads up to the max (nThreadMax value)
    for (int i=0; i<nThreadMax; i++) lThreads.add(i, new Worker("Thread-"+i));
    
    while ((nNumberCount<nMaxNumber) && allThreadsFinished()) {
      for (int i=0; i<nThreadMax; i++) {
        if (!lThreads.get(i).isAlive() && (nNumberCount<nMaxNumber)) { 
          lResult.addAll(lThreads.get(i).start(nNumberCount+1, nNumberCount+nStep)); 
          nNumberCount += nStep; 
        }
      }
    }
    System.out.println("All threads finished, prime numbers found: " + lResult.size());
    
  }
  
}
