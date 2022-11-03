package com.company.signature_feature;

public class AverageSentenceRatio implements FeatureCalculator {

  private static AverageSentenceRatio instance;
  private final StringStatistic stringStatistic;

  private AverageSentenceRatio() {
    stringStatistic = StringStatistic.getInstance();
  }

  public static AverageSentenceRatio getInstance() {
    if (instance == null) {
      instance = new AverageSentenceRatio();
    }
    return instance;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfAllWords(text) / stringStatistic.getNumberOfSentencesInText(text);
  }
}