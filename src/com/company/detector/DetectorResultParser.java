package com.company.detector;

import static java.lang.Math.round;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.SignatureModel;
import com.company.signature.TextSignatureCollection;
import com.company.text.TextCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DetectorResultParser {

  private static DetectorResultParser instance;

  private DetectorResultParser() {
  }

  public static DetectorResultParser getInstance() {
    if (instance == null) {
      instance = new DetectorResultParser();
    }
    return instance;
  }

  public String[][] parseCoefficientToPercentage(String[][] comparedSignatureCoefficient) {
    String[][] parsedResult = new String[comparedSignatureCoefficient[0].length][comparedSignatureCoefficient.length];
    Double[][] comparedCoefficientToDouble = convertStringToDoubleArray(
        comparedSignatureCoefficient);

    for (int column = 0; column < comparedCoefficientToDouble[0].length; column++) {
      double maxInColumn = findMaxCoefficientForText(column, comparedCoefficientToDouble);

      for (int row = 0; row < comparedCoefficientToDouble.length; row++) {
        double percentage = comparedCoefficientToDouble[row][column] / (maxInColumn / 100);
        double reversePercentage = (percentage - 100) * (-1);
        parsedResult[column][row] = Double.toString(round(reversePercentage)) + "%";
      }
    }
    return parsedResult;
  }

  public double findMaxCoefficientForText(int column, Double[][] convertStringToDoubleArray) {
    double max = 0;
    for (int i = 0; i < column + 1; i++) {
      max = convertStringToDoubleArray[0][i];
      for (Double[] doubles : convertStringToDoubleArray) {
        if (doubles[i] > max) {
          max = doubles[i];
        }
      }
    }
    return max;
  }

  public Double[][] convertStringToDoubleArray(String[][] comparedOnCoefficient) {
    return Arrays.stream(comparedOnCoefficient).map(a -> Arrays.stream(a).map(
        Double::valueOf).toArray(Double[]::new)).toArray(Double[][]::new);
  }
}