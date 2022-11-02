package com.company.signature_feature;

public class HapaxLegomenaRatio implements FeatureCalculator{

  private StringStatistic stringStatistic;

  public HapaxLegomenaRatio(StringStatistic stringStatistic) {
    this.stringStatistic = stringStatistic;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfNonRecurringWords(text) / stringStatistic.getNumberOfAllWords(text);
  }
}