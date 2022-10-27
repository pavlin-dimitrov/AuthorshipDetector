package com.company.signature;

import com.company.signature_feature.BASE_Feature;
import com.company.text.TextCollection;
import com.company.text.TextModel;
import java.util.ArrayList;

public class TextSignatureCollection {

  private final BASE_Feature BASEFeature = new BASE_Feature();

  public TextSignatureCollection() {
  }

  public ArrayList<Signature> textSignatures(TextCollection textCollection){
    ArrayList<TextModel> texts = textCollection.insertText();
    ArrayList<Signature> textSignatures = new ArrayList<>(texts.size());
    for (TextModel text : texts){
      Signature signature = new Signature(text.getTextTitle(),
      BASEFeature.setAverageWordLength(text.getTextBody()),
      BASEFeature.setTypeTokenRatio(text.getTextBody()),
      BASEFeature.setHapaxLegomenaRatioOfText(text.getTextBody()),
      BASEFeature.setAverageSentenceRatio(text.getTextBody()),
      BASEFeature.setAverageSentenceComplexity(text.getTextBody()));
      textSignatures.add(signature);
    }
    return textSignatures;
  }
}
