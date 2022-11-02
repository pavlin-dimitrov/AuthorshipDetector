package com.company.detector;

import com.company.signature.AuthorSignatureCollection;
import com.company.signature.SignatureModel;
import com.company.signature.TextSignatureCollection;
import com.company.signature_comparator.SignatureComparator;
import com.company.text.TextCollection;
import com.company.enumeration.FeatureType;
import java.util.ArrayList;

public class AuthorshipDetectorImpl implements AuthorshipDetector {

  private final AuthorSignatureCollection authorSignatureCollection = new AuthorSignatureCollection();
  private final TextSignatureCollection textSignatureCollection = new TextSignatureCollection();
  private final TextCollection textCollection = new TextCollection();
  private final SignatureComparator signatureComparator = new SignatureComparator();

  public AuthorshipDetectorImpl() {
  }

  @Override
  public String[][] storeComparisonCoefficient(String path) {
    ArrayList<SignatureModel> authorSignature = authorSignatureCollection.signatures();
    ArrayList<SignatureModel> textSignature = textSignatureCollection.textSignatures(textCollection,
        path);
    String[][] resultArray = new String[authorSignature.size()][textSignature.size()];
    for (int i = 0; i < authorSignature.size(); i++) {
      for (int j = 0; j < textSignature.size(); j++) {
        resultArray[i][j] = signatureComparator.compareTwoSignatures(authorSignature.get(i),
            textSignature.get(j));
      }
    }
    return resultArray;
  }

//  @Override
//  public String[][] table(String[][] percentageResult, String path) {
//    String[][] table = new String[percentageResult.length + 1][percentageResult[0].length + 1];
//    ArrayList<SignatureModel> authorSignature = authorSignatureCollection.signatures();
//    ArrayList<SignatureModel> textSignature = textSignatureCollection.textSignatures(textCollection,
//        path);
//    for (int i = 0; i < percentageResult.length; i++) {
//      for (int j = 0; j < percentageResult[0].length; j++) {
//        if (i == 0 && j == 0) {
//          table[i][j] = "        *         ";
//        } else if (i == 0) {
//          table[i][j] = authorSignature.get(i - 1).getName();
//        } else if (j == 0) {
//          table[i][j] = textSignature.get(j - 1).getName();
//        } else {
//          table[i][j] = percentageResult[i - 1][j - 1];
//        }
//      }
//    }
//    return table;
//  }
}