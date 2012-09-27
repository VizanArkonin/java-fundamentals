package org.zeroturnaround.jf2012.concurrency.cancel.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BlockingIO3 extends Thread {

  private final InputStream in;

  public BlockingIO3(InputStream in) {
    this.in = in;
  }

  public static void main(String[] args) throws Exception {
    BlockingIO3 thread = new BlockingIO3(System.in);
    thread.start();
    thread.join(1000);
    System.out.println("Interrupting...");
    thread.interrupt();
  }

  @Override
  public void run() {
    try {
      System.out.print("What's your name: ");
      String name = new BufferedReader(new InputStreamReader(in)).readLine();
      System.out.printf("Hi, %s!%n", name);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void interrupt() {
    System.out.println("Closing the input stream...");
    try {
      in.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    super.interrupt();
  }

}