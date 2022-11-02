package com.company.enumeration;

public enum RegexPattern {

  PUNCTUATION("\\p{Punct}"),
  NEWLINE("\n"),
  CARRIAGE_RETURN("\r"),
  SINGLE_WHITESPACE(" "),
  LANGUAGE("en"),
  COUNTRY("US"),
  BREAK_ITERATOR_REGEX("[A-Za-z]*[\\s][A-Z][.][\\s][A-Z][\\u001a]"),
  SENTENCE_SEPARATOR("([.!?])([\\s\\n\\r])"),
  PHRASE_SEPARATOR("([,:;])([\\s\\n\\r])"),
  EMPTY_STRING(""),
  QUOTE_AND_DASH("[\"]|[\\-]");
  private final String regex;

  RegexPattern(String regex) {
    this.regex = regex;
  }

  public String getRegex() {
    return regex;
  }
}
