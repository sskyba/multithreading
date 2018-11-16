package Multithread;

import java.util.*;

class Worker extends Thread {
  private String threadName = "";

  Worker(String name) {
    threadName = name;
    System.out.println("Creating " + threadName );
  }
  
  private boolean isPrimeNumber(int n) {
    for (int i=2; i<n/2; ++i) {
      if (n % i == 0) return false;
    }
    return true;
  }
  
  public ArrayList<Integer> start(int nStart, int nEnd) {
    ArrayList<Integer> lResult = new ArrayList();
    for (int i=nStart; i<=nEnd; i++) {
      if (isPrimeNumber(i)) {
        System.out.println("Thread " + this.getName() + " adding number " + i);
        lResult.add(i);
      }
    }
    return lResult;
  }

}

public class ThreadController {
  
  static final int nMaxNumber = 1000000;
  static final int nStep = 100000;
  static int nNumberCount = 0;
  static final int nThreadMax = 3;
  static ArrayList<Integer> lResult = new ArrayList();
  static ArrayList<Worker> lThreads = new ArrayList();

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