package dnekcab;

public class ThreadingPractice {
  /***
   * Opening statement. What the heck is a thread?
   * Time to go through it, I guess!
   *
   * THE DOCTOR SAID 6 MONTHS!
   * THE CLOCK SAYS 90 SECONDS!
   * FIRST SHOWDOWN!
   * BEGIN!
  ***/


  public static void main (String[] args) {
    /*
    With my 30 seconds of reading, threads are all about doing multiple things at the same time.
    In general if you were to run a Java project, it'd basically just run one file, and only that file.
    You might access other methods in different files, but they'll just get thrown on the stack and be run in order. But if you want to have two things running at the same time, you need to thread stuff.
    And thus here we are.
    */

    /*
    Every thread has a certain lifecycle that has a couple of stages.
    1) New! Welcome to life.
    2) Runnable! This is where we run it, I'm guessing. Started by the "start()" method. This has two cases apparently:
      a) Case 1: Is a running thread
      b) Case 2: Is not a running thread
    3) Running (in the 90's)! When it hits case 1 that means that the thread is actually doing things. How cool. This means the scheduler has selected it to be a thread to run, switching it from a runnable state to a running state.
    4) Blocked. How unfortunate. Means that the scheduler has selected this thread to remain in a runnable state. So it doesn't actually do anything.
    5) Termination. The thread has done its job, and is terminated when the stop() method is called over a thread or the run() method exits.
    */
  }
}
