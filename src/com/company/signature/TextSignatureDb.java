package com.company.signature;

import com.company.signatureFeature.FeatureCalculator;
import com.company.text.TextDb;
import com.company.text.TextModel;
import java.util.ArrayList;

public class TextSignatureDb {

  private final FeatureCalculator featureCalculator = new FeatureCalculator();

  public TextSignatureDb() {
  }

  public ArrayList<Signature> textSignatures(TextDb textDb){
    ArrayList<TextModel> texts = textDb.insertedText();
    ArrayList<Signature> textSignatures = new ArrayList<>(texts.size());
    for (TextModel text : texts){
      Signature signature = new Signature(text.getTextTitle(),
      featureCalculator.setAverageWordLength(text.getTextBody()),
      featureCalculator.setTypeTokenRatio(text.getTextBody()),
      featureCalculator.setHapaxLegomenaRatioOfText(text.getTextBody()),
      featureCalculator.setAverageSentenceRatio(text.getTextBody()),
      featureCalculator.setAverageSentenceComplexity(text.getTextBody()));
      textSignatures.add(signature);
    }
    return textSignatures;
  }
}
