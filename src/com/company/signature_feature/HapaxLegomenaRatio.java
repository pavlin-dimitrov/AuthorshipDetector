package com.company.signature_feature;

public class HapaxLegomenaRatio implements FeatureCalculator{

  private FeatureDto featureDto;

  public HapaxLegomenaRatio(FeatureDto featureDto) {
    this.featureDto = featureDto;
  }

  @Override
  public double featureCalculation(String text) {
    return featureDto.getNumberOfNonRecurringWords(text) / featureDto.getNumberOfAllWords(text);
  }
}