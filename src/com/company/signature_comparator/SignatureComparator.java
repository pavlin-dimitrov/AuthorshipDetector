package com.company.signature_comparator;

import com.company.feature_comparator.AverageSentenceComplexityComparator;
import com.company.feature_comparator.AverageSentenceRatioComparator;
import com.company.feature_comparator.AverageWordLengthComparator;
import com.company.feature_comparator.FeatureComparator;
import com.company.feature_comparator.HapaxLegomenaRatioComparator;
import com.company.feature_comparator.TypeTokenRatioComparator;
import com.company.signature.SignatureModel;
import com.company.signature_feature.AverageSentenceComplexity;
import com.company.signature_feature.AverageSentenceRatio;
import com.company.signature_feature.AverageWordLength;
import com.company.signature_feature.HapaxLegomenaRatio;
import com.company.signature_feature.TypeTokenRatio;

public class SignatureComparator {

  private static SignatureComparator instance;
  private final FeatureComparator averageSentenceComplexity;
  private final FeatureComparator averageSentenceRatio;
  private final FeatureComparator averageWordLength;
  private final FeatureComparator hapaxLegomenaRatio;
  private final FeatureComparator typeTokenRatio;

  private SignatureComparator() {
    averageSentenceComplexity = AverageSentenceComplexityComparator.getInstance();
    averageSentenceRatio = AverageSentenceRatioComparator.getInstance();
    averageWordLength = AverageWordLengthComparator.getInstance();
    hapaxLegomenaRatio = HapaxLegomenaRatioComparator.getInstance();
    typeTokenRatio = TypeTokenRatioComparator.getInstance();
  }

  public static SignatureComparator getInstance(){
    if (instance == null) {
      instance = new SignatureComparator();
    }
    return instance;
  }

  public String compareTwoSignatures(SignatureModel author, SignatureModel textSignature) {
    return String.valueOf(averageWordLength.compareTwoFeatures(author, textSignature)
        + typeTokenRatio.compareTwoFeatures(author, textSignature)
        + hapaxLegomenaRatio.compareTwoFeatures(author, textSignature)
        + averageSentenceRatio.compareTwoFeatures(author, textSignature)
        + averageSentenceComplexity.compareTwoFeatures(author, textSignature));
  }
}