package com.company.detector;

import static java.lang.Math.round;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.SignatureModel;
import com.company.signature.TextSignatureCollection;

public class DetectorResultParser {

  public DetectorResultParser() {
  }

  public String[][] parseCoefficientToPercentage(String[][] comparedOnCoefficient) {
    int headingColumn = 1;
    int headingRow = 1;
    String[][] parsedResult = new String[comparedOnCoefficient[0].length][comparedOnCoefficient.length];
    double[][] arr = convertStringToDoubleArray(comparedOnCoefficient);

    for (int column = 0; column < arr[0].length + headingColumn; column++) {
      double maxInColumn = findMaxCoefficientForText(column, arr);

      for (int row = 0; row < arr.length + headingRow; row++) {
        double percentage = arr[row][column] / (maxInColumn / 100);
        double reversePercentage = (percentage - 100) * (-1);
        parsedResult[column][row] = Double.toString(round(reversePercentage));
      }
    }
    return parsedResult;
  }

  public double findMaxCoefficientForText(int column, double[][] convertStringToDoubleArray) {
    double max = 0;
    for (int i = 0; i < column + 1; i++) {
      max = convertStringToDoubleArray[0][i];
      for (double[] doubles : convertStringToDoubleArray) {
        if (doubles[i] > max) {
          max = doubles[i];
        }
      }
    }
    return max;
  }

  public double[][] convertStringToDoubleArray(String[][] comparedOnCoefficient) {
    double[][] parsedResult = new double[comparedOnCoefficient.length][comparedOnCoefficient[1].length];
    for (int row = 0; row < comparedOnCoefficient.length; row++) {
      for (int column = 0; column < comparedOnCoefficient[0].length; column++) {
        parsedResult[row][column] = Double.parseDouble(comparedOnCoefficient[row][column]);
      }
    }
    System.out.println(
        "doubleArr[" + parsedResult.length + "]" + "[" + parsedResult[0].length + "]");
    return parsedResult;
  }

  private int findMaxAuthorName(AuthorSignatureCollection authorSignatureCollection) {
    return authorSignatureCollection.signatures().stream()
        .map(SignatureModel::getName).map(String::length).max(Integer::compareTo).get();
  }
}