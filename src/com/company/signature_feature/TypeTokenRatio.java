package com.company.signature_feature;

public class TypeTokenRatio implements FeatureCalculator{

  private StringStatistic stringStatistic;

  public TypeTokenRatio(StringStatistic stringStatistic) {
    this.stringStatistic = stringStatistic;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfUniqueWords(text) / stringStatistic.getNumberOfAllWords(text);
  }
}