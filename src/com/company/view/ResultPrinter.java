package com.company.view;

import java.util.Arrays;

public class ResultPrinter {

  private static ResultPrinter instance;

  private ResultPrinter() {
  }

  private static final String TABLE_INDEX = "////////////////////// ";
  private static final String NAME_SEPARATOR = "  ";

  public static ResultPrinter getInstance() {
    if (instance == null) {
      instance = new ResultPrinter();
    }
    return instance;
  }

  public void printResult(String[][] compared, String[] authorSignatureName, String[] textAuthorName) {
    int maxStringLength = maxStringLength(textAuthorName, authorSignatureName);
    
    System.out.print(TABLE_INDEX);
    printAllAuthors(textAuthorName, authorSignatureName);
    System.out.println();

    for (int i = 0; i < compared.length; i++) {
      System.out.print(authorSignatureName[i]);
      printEmptySpaces(maxStringLength - authorSignatureName[i].length());
      System.out.print(NAME_SEPARATOR);
      for (int j = 0; j < compared[0].length; j++) {
        System.out.print(compared[i][j]);
        printEmptySpaces(maxStringLength - compared[i][j].length());
      }
      System.out.println();
    }
  }

  private void printAllAuthors(String[] authorNames, String[] authorSignatureName) {
    for (String authorName : authorNames) {
      int charsToPrint = maxStringLength(authorNames, authorSignatureName) - authorName.length();
      System.out.print(authorName);
      for (int j = 0; j < charsToPrint; j++) {
        System.out.print(" ");
      }
    }
  }

  private void printEmptySpaces(int numberOfEmptySpaces){
    for (int i = 0; i < numberOfEmptySpaces; i++) {
      System.out.print(" ");
    }
  }

  private int maxStringLength(String[] textAuthorName, String[] authorSignatureName){
    int maxAuthorName = Arrays.stream(textAuthorName).map(String::length).max(Integer::compareTo).get();
    int maxAuthorStringName = Arrays.stream(authorSignatureName).map(String::length).max(Integer::compareTo).get();
    return Math.max(maxAuthorName, maxAuthorStringName);
  }
}