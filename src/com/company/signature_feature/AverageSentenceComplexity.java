package com.company.signature_feature;

public class AverageSentenceComplexity implements FeatureCalculator{

private FeatureDto featureDto;

  public AverageSentenceComplexity(FeatureDto featureDto) {
    this.featureDto = featureDto;
  }

  @Override
  public double featureCalculation(String text) {
    return (double) featureDto.getNumberOfPhrasesInText(text)
        / featureDto.getNumberOfSentencesInText(text);
  }
}
