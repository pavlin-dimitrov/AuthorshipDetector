package com.company.signature;

import com.company.signature_feature.AverageSentenceComplexity;
import com.company.signature_feature.AverageSentenceRatio;
import com.company.signature_feature.AverageWordLength;
import com.company.signature_feature.FeatureCalculator;
import com.company.signature_feature.FeatureDto;
import com.company.signature_feature.HapaxLegomenaRatio;
import com.company.signature_feature.TypeTokenRatio;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import java.util.ArrayList;

public class TextSignatureCollection {

  private final FeatureDto featureDto;
  private final FeatureCalculator averageWordLength;
  private final FeatureCalculator typeTokenRatio;
  private final FeatureCalculator hapaxLegomenaRatioOfText;
  private final FeatureCalculator averageSentenceRatio;
  private final FeatureCalculator averageSentenceComplexity;

  public TextSignatureCollection() {
    this.featureDto = new FeatureDto();
    this.averageWordLength = new AverageWordLength(featureDto);
    this.typeTokenRatio = new TypeTokenRatio(featureDto);
    this.hapaxLegomenaRatioOfText = new HapaxLegomenaRatio(featureDto);
    this.averageSentenceRatio = new AverageSentenceRatio(featureDto);
    this.averageSentenceComplexity = new AverageSentenceComplexity(featureDto);
  }

  public ArrayList<Signature> textSignatures(TextCollection textCollection) {
    ArrayList<TextModel> texts = textCollection.insertText();
    ArrayList<Signature> textSignatures = new ArrayList<>(texts.size());
    for (TextModel text : texts) {
      Signature signature = new Signature(text.getTextTitle(),
          averageWordLength.featureCalculation(text.getTextBody()),
          typeTokenRatio.featureCalculation(text.getTextBody()),
          hapaxLegomenaRatioOfText.featureCalculation(text.getTextBody()),
          averageSentenceRatio.featureCalculation(text.getTextBody()),
          averageSentenceComplexity.featureCalculation(text.getTextBody()));
      textSignatures.add(signature);
    }
    return textSignatures;
  }
}