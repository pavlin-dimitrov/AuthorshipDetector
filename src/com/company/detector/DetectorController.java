package com.company.detector;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.SignatureModel;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import com.company.view.InputFilePath;
import com.company.view.ResultPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DetectorController {

  private static DetectorController instance;
  private final InputFilePath inputFilePath;
  private final TextCollection textCollection;
  private final AuthorSignatureCollection authorSignatureCollection;
  private final AuthorshipDetector authorshipDetector;
  private final ResultPrinter resultPrinter;
  private final DetectorResultParser resultParser;

  private DetectorController() {
    authorSignatureCollection = AuthorSignatureCollection.getInstance();
    inputFilePath = InputFilePath.getInstance();
    textCollection = TextCollection.getInstance();
    authorshipDetector = AuthorshipDetectorImpl.getInstance();
    resultPrinter = ResultPrinter.getInstance();
    resultParser = DetectorResultParser.getInstance();
  }

  public static DetectorController getInstance() {
    if (instance == null) {
      instance = new DetectorController();
    }
    return instance;
  }

  public void run() {
    String filePath = inputFilePath.fileName();
    String[][] resultTable = resultParser.parseCoefficientToPercentage(authorshipDetector.storeComparisonCoefficient(filePath));
    textCollection.insertText(filePath);
    resultPrinter.printResult(rotateTable(resultTable), getAuthorSignatureName(), getTextAuthorName(filePath));
  }

  private String[] getAuthorSignatureName() {
    return authorSignatureCollection.signatures()
        .stream()
        .map(SignatureModel::getName)
        .toArray(String[]::new);
  }

  private String[] getTextAuthorName(String filePath) {
    return textCollection.insertText(filePath)
        .stream()
        .map(TextModel::getTextTitle)
        .toArray(String[]::new);
  }

  private String[][] rotateTable(String[][] coefficient) {
    int column = coefficient.length;
    int row = coefficient[0].length;
    String[][] result = new String[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        result[i][j] = coefficient[j][i];
      }
    }
    return result;
  }
}