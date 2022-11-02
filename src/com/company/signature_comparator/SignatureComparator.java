package com.company.signature_comparator;

import com.company.feature_comparator.AverageSentenceComplexityComparator;
import com.company.feature_comparator.AverageSentenceRatioComparator;
import com.company.feature_comparator.AverageWordLengthComparator;
import com.company.feature_comparator.FeatureComparator;
import com.company.feature_comparator.HapaxLegomenaRatioComparator;
import com.company.feature_comparator.TypeTokenRatioComparator;
import com.company.signature.SignatureModel;

public class SignatureComparator {

  private final FeatureComparator averageSentenceComplexity = new AverageSentenceComplexityComparator();
  private final FeatureComparator averageSentenceRatio = new AverageSentenceRatioComparator();
  private final FeatureComparator averageWordLength = new AverageWordLengthComparator();
  private final FeatureComparator hapaxLegomenaRatio = new HapaxLegomenaRatioComparator();
  private final FeatureComparator typeTokenRatio = new TypeTokenRatioComparator();

  public SignatureComparator() {
  }

  public String compareTwoSignatures(SignatureModel author, SignatureModel textSignature) {
    return String.valueOf(averageWordLength.compareTwoFeatures(author, textSignature)
        + typeTokenRatio.compareTwoFeatures(author, textSignature)
        + hapaxLegomenaRatio.compareTwoFeatures(author, textSignature)
        + averageSentenceRatio.compareTwoFeatures(author, textSignature)
        + averageSentenceComplexity.compareTwoFeatures(author, textSignature));
  }
}