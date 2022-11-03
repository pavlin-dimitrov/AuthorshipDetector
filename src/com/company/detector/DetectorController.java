package com.company.detector;

import com.company.signature.AuthorSignatureCollection;
import com.company.text.TextCollection;
import com.company.view.InputFilePath;
import com.company.view.ResultPrinter;

public class DetectorController {

  private static DetectorController instance;
  private final InputFilePath inputFilePath;
  private final TextCollection textCollection;
  private final AuthorshipDetector authorshipDetector;
  private final ResultPrinter resultPrinter;
  private final DetectorResultParser resultParser;

  private DetectorController() {
    inputFilePath = InputFilePath.getInstance();
    textCollection = TextCollection.getInstance();
    authorshipDetector = AuthorshipDetectorImpl.getInstance();
    resultPrinter = ResultPrinter.getInstance();
    resultParser = DetectorResultParser.getInstance();
  }

  public static DetectorController getInstance(){
    if (instance == null){
      instance = new DetectorController();
    }
    return instance;
  }

  public void run() {
    String filePath = inputFilePath.fileName();
    textCollection.insertText(filePath);
    resultPrinter.printResult(resultParser.parseCoefficientToPercentage(
        authorshipDetector.storeComparisonCoefficient(filePath)));
  }
}