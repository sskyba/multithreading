package Multithread;

import java.util.*;

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
