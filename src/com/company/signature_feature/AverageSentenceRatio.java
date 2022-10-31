package com.company.signature_feature;

public class AverageSentenceRatio implements FeatureCalculator {

  private FeatureDto featureDto;

  public AverageSentenceRatio(FeatureDto featureDto) {
    this.featureDto = featureDto;
  }

  @Override
  public double featureCalculation(String text) {
    return featureDto.getNumberOfAllWords(text) / featureDto.getNumberOfSentencesInText(text);
  }
}
