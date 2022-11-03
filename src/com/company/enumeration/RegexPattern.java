package com.company.enumeration;

public enum RegexPattern {

  PUNCTUATION("\\p{Punct}"),
  NEWLINE("\n"),
  CARRIAGE_RETURN("\r"),
  SINGLE_WHITESPACE(" "),
  LANGUAGE("en"),
  COUNTRY("US"),
  BREAK_ITERATOR_REGEX("[A-Za-z]*[\\s][A-Z][.][\\s][A-Z]"),
  SENTENCE_SEPARATOR("([.!?])([\\s\\n\\r])"),
  PHRASE_SEPARATOR("([,:;])([\\s\\n\\r])"),
  EMPTY_STRING(""),
  QUOTE_AND_DASH("[\"]|[\\-]"),
  AUTHOR_SIGNATURE_FEATURE_DELIMITER("[,]|[\n]"),
  AUTHOR_SIGNATURE("src/com/company/resources/knownSignatures.txt");
  private final String regex;

  RegexPattern(String regex) {
    this.regex = regex;
  }

  public String getRegex() {
    return regex;
  }
}
