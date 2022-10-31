package com.company.signature_feature;

public class AverageWordLength implements FeatureCalculator{

  private FeatureDto featureDto;

  public AverageWordLength(FeatureDto featureDto) {
    this.featureDto = featureDto;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) featureDto.getAllWordsLength(text) / featureDto.getNumberOfAllWords(text);
  }
}
