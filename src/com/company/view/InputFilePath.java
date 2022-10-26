package com.company.view;

import java.util.Scanner;

public class InputFilePath {
  public String fileName() {
    System.out.println("Enter file name here: ");
    Scanner filePath = new Scanner(System.in);
    return filePath.nextLine();
  }
}
