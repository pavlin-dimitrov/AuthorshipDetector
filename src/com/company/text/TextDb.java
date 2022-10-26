package com.company.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.company.view.InputFilePath;

public class TextDb {

  private final InputFilePath filePath = new InputFilePath();
  private final String path = filePath.fileName();

  public ArrayList<TextModel> insertedText() {
    try (Scanner input = new Scanner(new File(path))) {
      input.useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
      ArrayList<TextModel> textModels = new ArrayList<>();

      while (input.hasNext()) {
        String text = input.next();
        TextModel newText = new TextModel(getTextTitle(text), getRidOfFirstEmptyRow(text));
        textModels.add(newText);
      }
      return textModels;
    } catch (FileNotFoundException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  private String getTextTitle(String text) {
    return text.trim()
        .replaceFirst("\n", "")
        .lines()
        .findFirst()
        .stream()
        .findFirst().orElse(null);
  }

  private String getRidOfFirstEmptyRow(String text){
    return text.trim().replaceFirst("\n", "");
  }
}