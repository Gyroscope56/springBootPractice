package dnekcab;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ThreadingPractice {
  /***
   * Opening statement. What the heck is a thread?
   * Time to go through it, I guess!
   *
   *
   * Let's boogie
  ***/


  public static void main (String[] args) throws InterruptedException {
    /*
    With my 30 seconds of reading, threads are all about doing multiple things at the same time.
    In general if you were to run a Java project, it'd basically just run one file, and only that file.
    You might access other methods in different files, but they'll just get thrown on the stack and be run in order. But if you want to have two things running at the same time, you need to thread stuff.
    And thus here we are.
    */


    /***
     * Section 1!
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

    // This ArrayList is here due to section 2. Just ignore it until you hit section 2 :)
    ArrayList<MultiThreadingDemo> threads = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      // One cool thing here. I put in a sleep call for five seconds in the run() method.
      // My thought process was that it would execute one, then wait for 5 seconds, then execute the next one, and so on and so forth.
      // But the result prints all of them and then they all wait for five seconds.
      // Additionally, putting a sleep call for five seconds BEFORE the print statement leads to them all printing at the same time!
      // Meaning they're all essentially concurrent, with barely any delay between them. How cool!
      MultiThreadingDemo temp = new MultiThreadingDemo();
      threads.add(temp);
      temp.setName("Thread number " + i);
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

    // Interestingly enough, by putting the main Thread outside that for loop, it runs in concurrence with everything in that loop!

    // Anyway, you can change its name, see priority, and more!
    System.out.println(main.getPriority());

    // It looks like all threads start at a priority of 5. What is this priority though?

    // Priority indicates how important the thread is relative to other threads. Threads with higher priority is more likely to be executed before a thread with a lower priority.
    // Min is 1, and Max is 10. Most threads are initialized with a priority of 5.

    // If two threads have the same priority we can't tell which one will be executed first.
    // For example, with a bit of tweaking, the 10 threads initialized in the loop above printed out in this order:
    /*
    Thread number 1 is running with priority 5
    Thread number 7 is running with priority 5
    Thread number 6 is running with priority 5
    Thread number 0 is running with priority 5
    Thread number 8 is running with priority 5
    Thread number 2 is running with priority 5
    Thread number 3 is running with priority 5
    Thread number 4 is running with priority 5
    Thread number 5 is running with priority 5
    Thread number 9 is running with priority 5
    * */
    // You'd expect it to go 0, then 1, then 2, etc etc. But it has thread 1 first, then thread 7, and so on.
    // This is the second runthrough of the code:

    /*
    Thread number 5 is running with priority 5
    Thread number 6 is running with priority 5
    Thread number 0 is running with priority 5
    Thread number 2 is running with priority 5
    Thread number 1 is running with priority 5
    Thread number 7 is running with priority 5
    Thread number 4 is running with priority 5
    Thread number 3 is running with priority 5
    Thread number 9 is running with priority 5
    Thread number 8 is running with priority 5
     */

    // So a difference in the execution order! Pretty cool stuff

    // You can also name threads quite easily with .setName()
    // Not very crazy

    /***
     * Section 2!
     */

    // Let's do some recap of everything we've seen so far.
    Scanner input = new Scanner(System.in);
    // Set a timeout so that the threads from section 1 can fully finish before we start section 2 (Is now not used due to having a thread that'll stop the other threads)
    // TimeUnit.SECONDS.sleep(15);
    // System.out.println("\n--- Waiting for input to go to stage 2 ---");

    // Now here's a cool idea using threads. Obviously you can use threads to do a ton of calculations in parallel. But what if we decide that it's taking too long, and we just want to test something further down in the file (like this lol)
    // Well what if we do a bit of shenanigans? I'll start by putting all of those threads from section 1 into a singular ArrayList.


    // This is from a little further down.
    MultiThreadingDemo2 multiThreadingDemo2 = new MultiThreadingDemo2(10);

    // Let's make a StopThread class
    StopThread stopper = new StopThread(threads, multiThreadingDemo2);
    stopper.start();

    // And with a bit of testing, our stopper does indeed work!

    // But let's say I want to run a thread of type MultiThreadingDemo2 after those original threads we made are stopped. How would I do that?

    // We can't just say multiThreadingDemo2.start() immediately, since I want this to run AFTER the previous set of threads is done, not at the same time. So what we can do instead is this!
    // Let's put another argument in the StopThread constructor, changing the invocation from "new StopThread(threads)" to "new StopThread(threads, multiThreadingDemo2)"

    // So basically, I've put the thread I want to run as an argument in the constructor of the StopThread. Then, when I execute the code to stop the first set of threads, I'll start the thread I want to run.
    // Is there a better method than this? Probably, but I think this is a clear and effective way to do it.


    // Also am now moving to a separate file (ThreadingPractice2.java)
  }
}
