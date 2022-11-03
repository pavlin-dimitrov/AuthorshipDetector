package com.company.signature;

import com.company.signature_feature.AverageSentenceComplexity;
import com.company.signature_feature.AverageSentenceRatio;
import com.company.signature_feature.AverageWordLength;
import com.company.signature_feature.FeatureCalculator;
import com.company.signature_feature.StringStatistic;
import com.company.signature_feature.HapaxLegomenaRatio;
import com.company.signature_feature.TypeTokenRatio;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import java.util.ArrayList;

public class TextSignatureCollection {

  private static TextSignatureCollection instance;
  private final StringStatistic stringStatistic;
  private final FeatureCalculator averageWordLength;
  private final FeatureCalculator typeTokenRatio;
  private final FeatureCalculator hapaxLegomenaRatioOfText;
  private final FeatureCalculator averageSentenceRatio;
  private final FeatureCalculator averageSentenceComplexity;

  private TextSignatureCollection() {
    stringStatistic = StringStatistic.getInstance();
    averageWordLength = AverageWordLength.getInstance();
    typeTokenRatio = TypeTokenRatio.getInstance();
    hapaxLegomenaRatioOfText = HapaxLegomenaRatio.getInstance();
    averageSentenceRatio = AverageSentenceRatio.getInstance();
    averageSentenceComplexity = AverageSentenceComplexity.getInstance();
  }

  public static TextSignatureCollection getInstance(){
    if (instance == null){
      instance = new TextSignatureCollection();
    }
    return instance;
  }

  public ArrayList<SignatureModel> textSignatures(TextCollection textCollection, String path) {
    ArrayList<TextModel> texts = textCollection.insertText(path);
    ArrayList<SignatureModel> textSignatureModels = new ArrayList<>(texts.size());
    for (TextModel text : texts) {
      SignatureModel signatureModel = new SignatureModel(text.getTextTitle(),
          averageWordLength.featureCalculation(text.getTextBody()),
          typeTokenRatio.featureCalculation(text.getTextBody()),
          hapaxLegomenaRatioOfText.featureCalculation(text.getTextBody()),
          averageSentenceRatio.featureCalculation(text.getTextBody()),
          averageSentenceComplexity.featureCalculation(text.getTextBody()));
      textSignatureModels.add(signatureModel);
    }
    return textSignatureModels;
  }
}