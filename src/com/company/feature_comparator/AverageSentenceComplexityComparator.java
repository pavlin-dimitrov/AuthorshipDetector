package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class AverageSentenceComplexityComparator implements FeatureComparator{

  private static AverageSentenceComplexityComparator instance;

  private AverageSentenceComplexityComparator() {
  }

  public static AverageSentenceComplexityComparator getInstance(){
    if (instance == null) {
      instance = new AverageSentenceComplexityComparator();
    }
    return instance;
  }

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(
        author.getAverageSentenceComplexity() - textSignature.getAverageSentenceComplexity())
        * FeatureType.AVERAGE_SENTENCE_COMPLEXITY.featureWeight;
  }
}
