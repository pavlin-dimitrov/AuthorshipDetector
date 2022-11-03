package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class TypeTokenRatioComparator implements FeatureComparator{

  private static TypeTokenRatioComparator instance;

  private TypeTokenRatioComparator() {
  }

  public static TypeTokenRatioComparator getInstance(){
    if (instance == null){
      instance = new TypeTokenRatioComparator();
    }
    return instance;
  }

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getTypeTokenRatio() - textSignature.getTypeTokenRatio())
        * FeatureType.TYPE_TOKEN_RATIO.featureWeight;
  }
}
