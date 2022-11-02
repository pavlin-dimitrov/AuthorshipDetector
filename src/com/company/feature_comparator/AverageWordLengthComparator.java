package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class AverageWordLengthComparator implements FeatureComparator{

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getAverageWordLength() - textSignature.getAverageWordLength())
        * FeatureType.AVERAGE_WORD_LENGTH.featureWeight;
  }
}
