package dnekcab;

import java.util.Scanner;

public class MultiThreadingDemo2 extends Thread{
  int a;
  public MultiThreadingDemo2(int a) {
    this.a = a;
  }
  public void run () {
    try {
      System.out.println("DemoThread ( " + this.getId() + ") " + this.getName() + " is running with priority " + this.getPriority());
      System.out.print("(" + this.getId() + ") Now changing priority to: ");
      Scanner input = new Scanner(System.in);
      this.setPriority(input.nextInt());
      System.out.println("(" + this.getId() + ") New priority: " + this.getPriority());
    } catch (Exception e) {
      System.out.println("Something went wrong!");
      System.out.println(e.getMessage());
    }
  }
}
