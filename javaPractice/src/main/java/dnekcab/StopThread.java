package dnekcab;

import java.util.ArrayList;
import java.util.Scanner;

public class StopThread extends Thread {

  ArrayList<MultiThreadingDemo> threads;
  MultiThreadingDemo2 threadToRun;

  public StopThread (ArrayList<MultiThreadingDemo> threads, MultiThreadingDemo2 thread) {
    System.out.println("Thread stopper is theoretically active!");
    this.threads = threads;
    this.threadToRun = thread;
  }
  public void run() {
    Scanner input = new Scanner(System.in);
    for (int i = 0; i < 10; i++) {
      String line = input.nextLine();
      if (line.isEmpty()) {
        continue;
      }
      if (line.charAt(0) == 'q') {
        // We'll quit if we see the line starting with a 'q'
        for (MultiThreadingDemo thread : threads) {
          thread.interrupt();
        }
        System.out.println("Threads should be stopped now!");
        // This line is from a little further down!
        threadToRun.start();
        return;
      }
    }
  }
}
