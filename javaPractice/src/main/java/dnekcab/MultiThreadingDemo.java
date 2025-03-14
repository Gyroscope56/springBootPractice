package dnekcab;

import java.util.concurrent.TimeUnit;

public class MultiThreadingDemo extends Thread {
  public void run() {
    try {
      TimeUnit.SECONDS.sleep(5);
      System.out.println (Thread.currentThread().getId() + " is running with priority " + Thread.currentThread().getPriority());
      TimeUnit.SECONDS.sleep(5);
      System.out.println("\n" + System.currentTimeMillis());
    } catch (Exception e) {
      System.out.println("Thread run failed :(");
    }
  }
}
