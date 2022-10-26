package com.company.text;

public class TextModel {

  private final String textBody;
  private final String textTitle;

  public TextModel(String textTitle, String textBody) {
    this.textTitle = textTitle;
    this.textBody = textBody;
  }

  public String getTextTitle() {
    return textTitle;
  }

  public String getTextBody() {
    return textBody;
  }
}