package com.company.signature_feature;

public class TypeTokenRatio implements FeatureCalculator{

  private static TypeTokenRatio instance;
  private final StringStatistic stringStatistic;

  private TypeTokenRatio() {
    stringStatistic = StringStatistic.getInstance();
  }

  public static TypeTokenRatio getInstance(){
    if (instance == null) {
      instance = new TypeTokenRatio();
    }
    return instance;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfUniqueWords(text) / stringStatistic.getNumberOfAllWords(text);
  }
}