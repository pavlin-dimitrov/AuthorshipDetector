package com.company.detector;

import com.company.text.TextCollection;
import com.company.view.InputFilePath;
import com.company.view.ResultPrinter;

public class DetectorController {

  private final InputFilePath inputFilePath = new InputFilePath();
  private final TextCollection textCollection = new TextCollection();
  private final AuthorshipDetector authorshipDetector = new AuthorshipDetectorImpl();
  private final ResultPrinter resultPrinter = new ResultPrinter();
  private final DetectorResultParser resultParser = new DetectorResultParser();

  public DetectorController() {
  }

  public void run() {
    String filePath = inputFilePath.fileName();
    textCollection.insertText(filePath);
    resultPrinter.printResult(resultParser.parseCoefficientToPercentage(
        authorshipDetector.storeComparisonCoefficient(filePath)));
  }
}