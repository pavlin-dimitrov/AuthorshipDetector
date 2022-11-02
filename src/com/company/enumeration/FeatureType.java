package com.company.enumeration;

public enum FeatureType {
  AVERAGE_WORD_LENGTH (11),
  TYPE_TOKEN_RATIO (33),
  HAPAX_LEGOMENA_RATIO (50),
  AVERAGE_SENTENCE_RATIO (0.4),
  AVERAGE_SENTENCE_COMPLEXITY (4);

  public final double featureWeight;

  FeatureType(double featureWeight) {
    this.featureWeight = featureWeight;
  }
}
