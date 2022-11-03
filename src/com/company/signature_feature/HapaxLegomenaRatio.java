package com.company.signature_feature;

public class HapaxLegomenaRatio implements FeatureCalculator{

  private static HapaxLegomenaRatio instance;
  private final StringStatistic stringStatistic;

  private HapaxLegomenaRatio() {
    stringStatistic = StringStatistic.getInstance();
  }

  public static HapaxLegomenaRatio getInstance(){
    if (instance == null) {
      instance = new HapaxLegomenaRatio();
    }
    return instance;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfNonRecurringWords(text) / stringStatistic.getNumberOfAllWords(text);
  }
}