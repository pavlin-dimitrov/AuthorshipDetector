package com.company.view;

public class ResultPrinter {

  public void printResult(String[][] compared) {
    for (String[] strings : compared) {
      for (int j = 0; j < compared[0].length; j++) {
        System.out.print(strings[j] + " // ");
      }
      System.out.println();
    }
  }

  //TODO PRINT TABLE WITH HEADINGS
//      for (int i = 0; i < authorSignature.size() + 1; i++) {
//    for (int j = 0; j < textSignature.size() + 1; j++) {
//      if (i == 0 && j == 0) {
//        resultArray[i][j] = "        *         ";
//      } else if (i == 0) {
//        resultArray[i][j] = textSignature.get(j - 1).getName();
//      } else if (j == 0) {
//        resultArray[i][j] = authorSignature.get(i - 1).getName();
//      } else {
//        resultArray[i][j] = comparator(authorSignature.get(i - 1), textSignature.get(j - 1));
//      }
//    }
//  }
}
