package com.company.detector;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.SignatureModel;
import com.company.signature.TextSignatureCollection;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import com.company.utils.FeatureType;
import java.util.ArrayList;

public class AuthorshipDetectorImpl implements AuthorshipDetector{

  private final AuthorSignatureCollection authorSignatureCollection = new AuthorSignatureCollection();
  private final TextSignatureCollection textSignatureCollection = new TextSignatureCollection();
  private final TextCollection textCollection = new TextCollection();

  public AuthorshipDetectorImpl() {
  }

  @Override
  public String[][] findAuthor(String path) {
    ArrayList<SignatureModel> authorSignature = authorSignatureCollection.signatures();
    ArrayList<SignatureModel> textSignature = textSignatureCollection.textSignatures(textCollection, path);

    String[][] resultArray = new String[authorSignature.size() + 1][textSignature.size() + 1];

    for (int i = 0; i < authorSignature.size() + 1; i++) {
      for (int j = 0; j < textSignature.size() + 1; j++) {
        if (i == 0 && j == 0) {
          resultArray[i][j] = "        *         ";
        } else if (i == 0) {
          resultArray[i][j] = textSignature.get(j - 1).getName();
        } else if (j == 0) {
          resultArray[i][j] = authorSignature.get(i - 1).getName();
        } else {
          resultArray[i][j] = comparator(authorSignature.get(i - 1), textSignature.get(j - 1));
        }
      }
    }
    return resultArray;
  }

  private String comparator(SignatureModel author, SignatureModel textSignature) {
    return String.valueOf(compareAverageWordLength(author, textSignature)
        + compareTypeTokenRatio(author, textSignature)
        + compareHapaxLegomenaRatio(author, textSignature)
        + compareAverageSentenceRatio(author, textSignature)
        + compareAverageSentenceComplexity(author, textSignature));
  }

  private double compareAverageSentenceComplexity(
      SignatureModel author, SignatureModel textSignature) {
    return Math.abs(
        author.getAverageSentenceComplexity() - textSignature.getAverageSentenceComplexity())
        * FeatureType.AVERAGE_SENTENCE_COMPLEXITY.featureWeight;
  }

  private double compareAverageSentenceRatio(
      SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getAverageSentenceRatio() - textSignature.getAverageSentenceRatio())
        * FeatureType.AVERAGE_SENTENCE_RATIO.featureWeight;
  }

  private double compareHapaxLegomenaRatio(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getHapaxLegomenaRatio() - textSignature.getHapaxLegomenaRatio())
        * FeatureType.HAPAX_LEGOMENA_RATIO.featureWeight;
  }

  private double compareTypeTokenRatio(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getTypeTokenRatio() - textSignature.getTypeTokenRatio())
        * FeatureType.TYPE_TOKEN_RATIO.featureWeight;
  }

  private double compareAverageWordLength(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getAverageWordLength() - textSignature.getAverageWordLength())
        * FeatureType.AVERAGE_WORD_LENGTH.featureWeight;
  }
}