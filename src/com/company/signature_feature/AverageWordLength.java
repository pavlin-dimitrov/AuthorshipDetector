package com.company.signature_feature;

public class AverageWordLength implements FeatureCalculator{

  private StringStatistic stringStatistic;

  public AverageWordLength(StringStatistic stringStatistic) {
    this.stringStatistic = stringStatistic;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getAllWordsLength(text) / stringStatistic.getNumberOfAllWords(text);
  }
}
