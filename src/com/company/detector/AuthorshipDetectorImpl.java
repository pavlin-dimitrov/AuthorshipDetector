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
        resultArray[i][j] = signatureComparator.compareTwoSignatures(authorSignature.get(i), textSignature.get(j));
      }
    }
    return resultArray;
  }
}