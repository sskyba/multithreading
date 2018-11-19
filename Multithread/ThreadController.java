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
  static ArrayList<Integer> lResults = new ArrayList();
  
  // list of threads
  static ArrayList<Worker> lThreads = new ArrayList();

  // checks if all the threads are finished
  private static boolean allThreadsFinished() {
    for (int i=0; i<nThreadMax; i++) if ((lThreads.size()>i) && lThreads.get(i).isAlive()) return false;
    return true;
  }
  
  public static void addResults(ArrayList<Integer> l) {
    lResults.addAll(l);
  }

  public static void main(String args[]) {
    
    while ((nNumberCount<nMaxNumber) && allThreadsFinished()) {
      for (int i=0; i<nThreadMax; i++) {
        if ((nNumberCount<nMaxNumber) && ((lThreads.size()<=i) || !lThreads.get(i).isAlive())) { 
          // initiate a new thread and push it to the pool
          lThreads.add(i, new Worker("Thread-"+i, nNumberCount+1, nNumberCount+nStep));
          nNumberCount += nStep; 
        }
      }
      try {
        for (int i=0; i<nThreadMax; i++) {
          lThreads.get(i).join();
        }
      } catch(InterruptedException e) { System.err.println("Thread interrupted, error message: " + e.getMessage()); }
    }
    System.out.println("All threads finished, prime numbers found: " + lResults.size());
  }
  
}
