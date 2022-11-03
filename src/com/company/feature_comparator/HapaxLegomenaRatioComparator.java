package com.company.feature_comparator;

import com.company.enumeration.FeatureType;
import com.company.signature.SignatureModel;

public class HapaxLegomenaRatioComparator implements FeatureComparator{

  private static HapaxLegomenaRatioComparator instance;

  private HapaxLegomenaRatioComparator() {
  }

  public static HapaxLegomenaRatioComparator getInstance(){
    if (instance == null){
      instance = new HapaxLegomenaRatioComparator();
    }
    return instance;
  }

  @Override
  public double compareTwoFeatures(SignatureModel author, SignatureModel textSignature) {
    return Math.abs(author.getHapaxLegomenaRatio() - textSignature.getHapaxLegomenaRatio())
        * FeatureType.HAPAX_LEGOMENA_RATIO.featureWeight;
  }
}