package com.company.signature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthorSignatureCollection {

  private static AuthorSignatureCollection instance;
  private static final String AUTHOR_SIGNATURE_FEATURE_DELIMITER = "[,]|[\n]";
  private static final String AUTHOR_SIGNATURE = "src/com/company/resources/knownSignatures.txt";

  private AuthorSignatureCollection() {
  }

  public static AuthorSignatureCollection getInstance(){
    if (instance == null){
      instance = new AuthorSignatureCollection();
    }
    return instance;
  }

  public ArrayList<SignatureModel> signatures() {
    try (Scanner input = new Scanner(new File(AUTHOR_SIGNATURE))) {
      input.useDelimiter(AUTHOR_SIGNATURE_FEATURE_DELIMITER);
      ArrayList<SignatureModel> authorSignatureModels = new ArrayList<>();
      while (input.hasNextLine()) {
        String name = input.next();
        String averageWordLength = input.next();
        String typeTokenRatio = input.next();
        String hapaxLegomenaRatio = input.next();
        String averageSentenceRatio = input.next();
        String averageSentenceComplexity = input.next();

        SignatureModel signatureModel = new SignatureModel(name,
            Double.parseDouble(averageWordLength), Double.parseDouble(typeTokenRatio),
            Double.parseDouble(hapaxLegomenaRatio), Double.parseDouble(averageSentenceRatio),
            Double.parseDouble(averageSentenceComplexity));
        authorSignatureModels.add(signatureModel);
      }
      return authorSignatureModels;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
