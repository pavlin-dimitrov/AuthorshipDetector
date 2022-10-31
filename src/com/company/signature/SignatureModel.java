package com.company.signature;

public class SignatureModel {
  private final String name;
  private final double averageWordLength;
  private final double typeTokenRatio;
  private final double hapaxLegomenaRatio;
  private final double averageSentenceRatio;
  private final double averageSentenceComplexity;

  public SignatureModel(String name, double averageWordLength, double typeTokenRatio,
      double hapaxLegomenaRatio, double averageSentenceRatio, double averageSentenceComplexity) {
    this.name = name;
    this.averageWordLength = averageWordLength;
    this.typeTokenRatio = typeTokenRatio;
    this.hapaxLegomenaRatio = hapaxLegomenaRatio;
    this.averageSentenceRatio = averageSentenceRatio;
    this.averageSentenceComplexity = averageSentenceComplexity;
  }

  public String getName() {
    return name;
  }

  public double getAverageWordLength() {
    return averageWordLength;
  }

  public double getTypeTokenRatio() {
    return typeTokenRatio;
  }

  public double getHapaxLegomenaRatio() {
    return hapaxLegomenaRatio;
  }

  public double getAverageSentenceRatio() {
    return averageSentenceRatio;
  }

  public double getAverageSentenceComplexity() {
    return averageSentenceComplexity;
  }
}