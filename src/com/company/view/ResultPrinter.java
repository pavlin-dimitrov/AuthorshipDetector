package com.company.view;

public class ResultPrinter {

  public void printResult(String[][] compared) {
    for (String[] strings : compared) {
      for (int j = 0; j < compared[1].length; j++) {
        System.out.print(strings[j] + " // ");
      }
      System.out.println();
    }
  }

}
