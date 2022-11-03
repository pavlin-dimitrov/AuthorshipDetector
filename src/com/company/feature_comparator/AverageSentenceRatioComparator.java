package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class AverageSentenceRatioComparator implements FeatureComparator{

  private static AverageSentenceRatioComparator instance;

  private AverageSentenceRatioComparator() {
  }

  public static AverageSentenceRatioComparator getInstance(){
    if (instance == null) {
      instance = new AverageSentenceRatioComparator();
    }
    return instance;
  }

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getAverageSentenceRatio() - textSignature.getAverageSentenceRatio())
        * FeatureType.AVERAGE_SENTENCE_RATIO.featureWeight;
  }
}
