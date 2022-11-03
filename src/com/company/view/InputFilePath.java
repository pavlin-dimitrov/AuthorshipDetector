package com.company.view;

import java.util.Scanner;

public class InputFilePath {

  private static InputFilePath instance;

  private InputFilePath() {
  }

  public static InputFilePath getInstance(){
    if (instance == null){
      instance = new InputFilePath();
    }
    return instance;
  }

  public String fileName() {
    printMessage("Enter file name here: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  private void printMessage(String text){
    System.out.println(text);
  }

  private String scanner(){
    return new Scanner(System.in).nextLine();
  }
}
