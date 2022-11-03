package com.company.signature_feature;

public class AverageWordLength implements FeatureCalculator{

  private static AverageWordLength instance;
  private final StringStatistic stringStatistic;

  private AverageWordLength() {
    stringStatistic = StringStatistic.getInstance();
  }

  public static AverageWordLength getInstance(){
    if (instance == null){
      instance = new AverageWordLength();
    }
    return instance;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) stringStatistic.getAllWordsLength(text) / stringStatistic.getNumberOfAllWords(text);
  }
}