package com.company.signature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthorSignatureCollection {

  public ArrayList<SignatureModel> signatures() {
    try (Scanner input = new Scanner(new File("src/com/company/resources/knownSignatures.txt"))) {
      input.useDelimiter("[,]|[\n]");
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
