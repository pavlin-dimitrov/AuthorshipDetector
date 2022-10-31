package com.company.view;

import java.util.Scanner;

public class InputFilePath {
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
