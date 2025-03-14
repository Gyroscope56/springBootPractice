package dnekcab;

public class ThreadingPractice {
  /***
   * Opening statement. What the heck is a thread?
   * Time to go through it, I guess!
   *
   *
   * Let's boogie
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
    for (int i = 0; i < 10; i++) {
      // One cool thing here. I put in a sleep call for five seconds in the run() method.
      // My thought process was that it would execute one, then wait for 5 seconds, then execute the next one, and so on and so forth.
      // But the result prints all of them and then they all wait for five seconds.
      // Additionally, putting a sleep call for five seconds BEFORE the print statement leads to them all printing at the same time!
      // Meaning they're all essentially concurrent, with barely any delay between them. How cool!
      MultiThreadingDemo temp = new MultiThreadingDemo();
      temp.start();
      // Using System.currentTimeMillis() shows that the first and last threads are off by a solid 6 milliseconds. Very VERY slight!

      // Since Thread is a class, you can't extend both it and another class, restricting the class slightly, but there's an interface called Runnable which does the same thing basically.
    }
    // There's this concept of a main thread. And logically, this checks out. It does make sense that when you run a java program, you'd basically be initializing a new Thread which runs the program itself.
    // That thread with the program on it is called the main thread.
    // Child threads can spawn from this main thread.
    // And oftentimes, it's the last thread to finish execution because it performs various shutdown actions


    // How does one control this main thread though?
    // Well you can access it through the main method with Thread.currentThread()

    Thread main = Thread.currentThread();
    System.out.println(main.getName() + ", " + main.getId());

    // Interestingly enough, by putting the main Thread outside that for loop, it in concurrence with everything in that loop!

    // Anyway, you can change it's name, see priority, and more!
    System.out.println(main.getPriority());

    // It looks like all threads start at a priority of 5. What is this priority though?

    // Priority indicates how important the thread is relative to other threads. Threads with higher priority is more likely to be executed before a thread with a lower priority.
    // Min is 1, and Max is 10. Most threads are initialized with a priority of 5.
  }
}
