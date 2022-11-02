package com.company.signature_feature;

public class AverageSentenceRatio implements FeatureCalculator {

  private StringStatistic stringStatistic;

  public AverageSentenceRatio(StringStatistic stringStatistic) {
    this.stringStatistic = stringStatistic;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfAllWords(text) / stringStatistic.getNumberOfSentencesInText(text);
  }
}
