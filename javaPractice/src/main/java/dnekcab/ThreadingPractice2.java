package dnekcab;

import java.util.Arrays;

public class ThreadingPractice2 {
  public static void main(String[] args) throws InterruptedException {
    // The way we've called threads so far and actually made them do things is with the start() method.
    // But we're actually calling the run() method via start(). So what happens if we were to just call run()?
    MultiThreadingDemo run = new MultiThreadingDemo();
    MultiThreadingDemo start = new MultiThreadingDemo();
    System.out.println("-----");
    run.setName("Run Method Thread");
    start.setName("Start Method Thread");
    run.run();
    start.start();
    System.out.println("-----");
    // By checking the output, we see that the thread "run" is actually named "main". This means that it's not actually creating a new thread if we use the run() method. How interesting!

    // However, run() does have some benefits. You can't call start() more than once per object, but you can do it multiple times with run().
    try {
      start.start();
    } catch (IllegalThreadStateException e) {
      System.out.println("This is the error's stack trace:");
      System.out.println(Arrays.toString(e.getStackTrace()));
    }

    // Now how can we delay a thread internally? With Thread.sleep()!
    // We'll do this with the main method.

    // This is the speed without a sleep() between each print
    for (int i = 0; i < 50; i++) {
      System.out.println(i);
    }
    for (int i = 0; i < 50; i++) {
      Thread.sleep(100);
      System.out.println(i);
    }
    // Even though the "start" thread does print something in the middle of the second loops output, it's clear to see that there's a big difference in timing between the two loops.


    // Finally, what's probably the most important part. The Daemon thread! 🔥🔥

    // Daemon threads are low priority threads that don't actually restrict the JVM. Usually the JVM will terminate when all the threads have finished execution. However, Daemon threads don't restrict the JVM from terminating.
    // This means that once all NON Daemon threads finished execution, any remaining Daemon threads will be terminated instantly.
    // You set a Daemon thread with .setDaemon(), but can't setDaemon() after the thread has already started.
  }
}
