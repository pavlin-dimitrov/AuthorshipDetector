package com.company.signature_feature;

public class TypeTokenRatio implements FeatureCalculator{

  private FeatureDto featureDto;

  public TypeTokenRatio(FeatureDto featureDto) {
    this.featureDto = featureDto;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) featureDto.getNumberOfUniqueWords(text) / featureDto.getNumberOfAllWords(text);
  }
}