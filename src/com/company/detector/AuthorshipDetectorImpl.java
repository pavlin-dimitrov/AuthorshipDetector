package com.company.detector;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.Signature;
import com.company.signature.TextSignatureCollection;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import java.util.ArrayList;

public class AuthorshipDetectorImpl implements AuthorshipDetector{

  private final AuthorSignatureCollection authorSignatureCollection = new AuthorSignatureCollection();
  private final TextSignatureCollection textSignatureCollection = new TextSignatureCollection();
  private final TextCollection textCollection = new TextCollection();
  private final ResultPrinter resultPrinter = new ResultPrinter();

  public AuthorshipDetectorImpl() {
  }

  @Override
  public void showTableResult(){
    resultPrinter.printResult(findAuthor());
  }

  @Override
  public String[][] findAuthor() {
    ArrayList<TextModel> textModels = textCollection.insertText();
    ArrayList<Signature> authors = authorSignatureCollection.signatures();
    ArrayList<Signature> textSignatures = textSignatureCollection.textSignatures(textCollection);

    String[][] resultArray = new String[authors.size() + 1][textSignatures.size() + 1];

    for (int i = 0; i < authors.size() + 1; i++) {
      for (int j = 0; j < textSignatures.size() + 1; j++) {
        if (i == 0 && j == 0) {
          resultArray[i][j] = "        *         ";
        } else if (i == 0) {
          resultArray[i][j] = textModels.get(j - 1).getTextTitle();
        } else if (j == 0) {
          resultArray[i][j] = authors.get(i - 1).getName();
        } else {
          resultArray[i][j] = comparator(authors.get(i - 1), textSignatures.get(j - 1));
        }
      }
    }
    return resultArray;
  }

  private String comparator(Signature author, Signature textSignature) {
    return String.valueOf(compareAverageWordLength(author, textSignature)
        + compareTypeTokenRatio(author, textSignature)
        + compareHapaxLegomenaRatio(author, textSignature)
        + compareAverageSentenceRatio(author, textSignature)
        + compareAverageSentenceComplexity(author, textSignature));
  }

  private double compareAverageSentenceComplexity(Signature author, Signature textSignature) {
    return Math.abs(
        author.getAverageSentenceComplexity() - textSignature.getAverageSentenceComplexity())
        * FeatureType.AVERAGE_SENTENCE_COMPLEXITY.featureWeight;
  }

  private double compareAverageSentenceRatio(Signature author, Signature textSignature) {
    return Math.abs(author.getAverageSentenceRatio() - textSignature.getAverageSentenceRatio())
        * FeatureType.AVERAGE_SENTENCE_RATIO.featureWeight;
  }

  private double compareHapaxLegomenaRatio(Signature author, Signature textSignature) {
    return Math.abs(author.getHapaxLegomenaRatio() - textSignature.getHapaxLegomenaRatio())
        * FeatureType.HAPAX_LEGOMENA_RATIO.featureWeight;
  }

  private double compareTypeTokenRatio(Signature author, Signature textSignature) {
    return Math.abs(author.getTypeTokenRatio() - textSignature.getTypeTokenRatio())
        * FeatureType.TYPE_TOKEN_RATIO.featureWeight;
  }

  private double compareAverageWordLength(Signature author, Signature textSignature) {
    return Math.abs(author.getAverageWordLength() - textSignature.getAverageWordLength())
        * FeatureType.AVERAGE_WORD_LENGTH.featureWeight;
  }
}
