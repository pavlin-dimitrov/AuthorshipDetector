package com.company.feature_comparator;

import com.company.signature.SignatureModel;

public interface FeatureComparator {

  double compareTwoFeatures(SignatureModel author, SignatureModel textSignature);

}