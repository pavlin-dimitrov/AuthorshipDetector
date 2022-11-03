package com.company.signature_feature;

public class AverageSentenceComplexity implements FeatureCalculator {

  private static AverageSentenceComplexity instance;
  private final StringStatistic stringStatistic;

  private AverageSentenceComplexity() {
    stringStatistic = StringStatistic.getInstance();
  }

  public static AverageSentenceComplexity getInstance(){
    if (instance == null) {
      instance = new AverageSentenceComplexity();
    }
    return instance;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getNumberOfPhrasesInText(text)
        / stringStatistic.getNumberOfSentencesInText(text);
  }
}