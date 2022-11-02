package com.company.signature_feature;

public class AverageSentenceComplexity implements FeatureCalculator {

  private final StringStatistic stringStatistic;

  public AverageSentenceComplexity(StringStatistic stringStatistic) {
    this.stringStatistic = stringStatistic;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfPhrasesInText(text)
        / stringStatistic.getNumberOfSentencesInText(text);
  }
}
