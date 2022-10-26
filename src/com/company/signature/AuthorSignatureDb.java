package com.company.signature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthorSignatureDb {

  public ArrayList<Signature> signatures() {
    try (Scanner input = new Scanner(new File("src/resources/knownSignatures.txt"))) {
      input.useDelimiter(",|\n");
      ArrayList<Signature> authorSignatures = new ArrayList<>();
      while (input.hasNextLine()) {
        String name = input.next();
        double averageWordLength = input.nextDouble();
        double typeTokenRatio = input.nextDouble();
        double hapaxLegomenaRatio = input.nextDouble();
        double averageSentenceRatio = input.nextDouble();
        double averageSentenceComplexity = input.nextDouble();

        Signature signature = new Signature(name, averageWordLength, typeTokenRatio,
            hapaxLegomenaRatio, averageSentenceRatio, averageSentenceComplexity);
        authorSignatures.add(signature);
      }
      return authorSignatures;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
