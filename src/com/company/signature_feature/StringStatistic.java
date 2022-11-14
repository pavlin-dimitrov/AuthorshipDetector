package com.company.signature_feature;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringStatistic {

  private static final String PUNCTUATION = "\\p{Punct}";
  private static final String EMPTY_STRING = "";
  private static final String NEWLINE = "\n";
  private static final String CARRIAGE_RETURN = "\r";
  private static final String WHITESPACE = " ";
  private static final String LANGUAGE = "en";
  private static final String COUNTRY = "US";
  private static final String BREAK_ITERATOR_REGEX = "[A-Za-z]*[\\s][A-Z][.][\\s][A-Z]";
  private static final String SENTENCE_SEPARATOR = "([.!?])([\\s\\n\\r])";
  private static final String PHRASE_SEPARATOR = "([,:;])([\\s\\n\\r])";
  private static final String QUOTE_AND_DASH = "[\"]|[\\-]";

  private static StringStatistic instance;
  private final int initialCount = 0;

  private StringStatistic() {
  }

  public static StringStatistic getInstance() {
    if (instance == null) {
      instance = new StringStatistic();
    }
    return instance;
  }

  protected int getAllWordsLength(String text) {
    String[] words = cleanAllPunctuation(text).split(WHITESPACE);
    return Arrays.stream(words).mapToInt(String::length).sum();
  }

  private String cleanAllPunctuation(String text) {
    return text.toLowerCase()
        .replaceAll(PUNCTUATION, EMPTY_STRING)
        .replaceAll(NEWLINE, EMPTY_STRING)
        .replaceAll(CARRIAGE_RETURN,
            WHITESPACE);
  }

  protected int getNumberOfAllWords(String text) {
    String[] words = cleanAllPunctuation(text).trim()
        .split(WHITESPACE);
    return words.length;
  }

  protected int getNumberOfUniqueWords(String text) {
    String[] words = cleanAllPunctuation(text).split(WHITESPACE);
    HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
    return uniqueWords.size();
  }

  protected int getNumberOfNonRecurringWords(String text) {
    String[] words = cleanAllPunctuation(text).split(WHITESPACE);
    Set<String> allUsedWords = new HashSet<>();
    Set<String> duplicatedWords = new HashSet<>();
    for (String i : words) {
      if (!allUsedWords.add(i)) {
        duplicatedWords.add(i);
      }
    }
    return (allUsedWords.size() - duplicatedWords.size());
  }

  protected int getNumberOfSentencesInText(String text) {
    Locale currentLocale = new Locale(LANGUAGE, COUNTRY);
    BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
    return countSentences(sentenceIterator, text);
  }

  private int countSentences(BreakIterator bi, String source) {
    String newString = source.replaceAll(BREAK_ITERATOR_REGEX, EMPTY_STRING);
    int count = initialCount;
    bi.setText(newString);
    int boundary = bi.next();
    while (boundary != BreakIterator.DONE) {
      count++;
      boundary = bi.next();
    }
    return count;
  }

  protected int getNumberOfPhrasesInText(String text) {
    String[] textToSentences = separateTextToSentences(text);
    int countPhraseSeparators = initialCount;
    int countPhrases = initialCount;
    for (String sentence : textToSentences) {
      Matcher matcher = phraseSeparatorPattern(sentence);
      while (matcher.find()) {
        countPhraseSeparators++;
      }
      if (countPhraseSeparators > 0) {
        countPhrases = countPhrases + countPhraseSeparators + 1;
      }
      countPhraseSeparators = initialCount;
    }
    return countPhrases;
  }

  private String[] separateTextToSentences(String text) {
    Pattern myPattern = Pattern.compile(SENTENCE_SEPARATOR);
    return text.replaceAll(QUOTE_AND_DASH, EMPTY_STRING)
        .split(myPattern.pattern());
  }

  private Matcher phraseSeparatorPattern(String sentence) {
    Pattern pattern = Pattern.compile(PHRASE_SEPARATOR);
    return pattern.matcher(sentence);
  }
}