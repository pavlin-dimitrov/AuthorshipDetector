package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class HapaxLegomenaRatioComparator implements FeatureComparator{

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getHapaxLegomenaRatio() - textSignature.getHapaxLegomenaRatio())
        * FeatureType.HAPAX_LEGOMENA_RATIO.featureWeight;
  }
}
