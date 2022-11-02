package com.company.signature_feature;

import com.company.enumeration.RegexPattern;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringStatistic {

  int initialCount = 0;

  public StringStatistic() {
  }

  protected int getAllWordsLength(String text) {
    String[] words = cleanAllPunctuation(text).split(RegexPattern.SINGLE_WHITESPACE.getRegex());
    return Arrays.stream(words).mapToInt(String::length).sum();
  }

  private String cleanAllPunctuation(String text) {
    return text.toLowerCase()
        .replaceAll(RegexPattern.PUNCTUATION.getRegex(), RegexPattern.EMPTY_STRING.getRegex())
        .replaceAll(RegexPattern.NEWLINE.getRegex(), RegexPattern.EMPTY_STRING.getRegex())
        .replaceAll(RegexPattern.CARRIAGE_RETURN.getRegex(),
            RegexPattern.SINGLE_WHITESPACE.getRegex());
  }

  protected int getNumberOfAllWords(String text) {
    String[] words = cleanAllPunctuation(text).trim()
        .split(RegexPattern.SINGLE_WHITESPACE.getRegex());
    return words.length;
  }

  protected int getNumberOfUniqueWords(String text) {
    String[] words = cleanAllPunctuation(text).split(RegexPattern.SINGLE_WHITESPACE.getRegex());
    HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
    return uniqueWords.size();
  }

  protected int getNumberOfNonRecurringWords(String text) {
    String[] words = cleanAllPunctuation(text).split(RegexPattern.SINGLE_WHITESPACE.getRegex());
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
    Locale currentLocale = new Locale(RegexPattern.LANGUAGE.getRegex(),
        RegexPattern.COUNTRY.getRegex());
    BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
    return countSentences(sentenceIterator, text);
  }

  private int countSentences(BreakIterator bi, String source) {
    String newString = source.replaceAll(RegexPattern.BREAK_ITERATOR_REGEX.getRegex(),
        RegexPattern.EMPTY_STRING.getRegex());
    int count = initialCount;
    bi.setText(newString);
    int boundary = bi.first();
    while (boundary != BreakIterator.DONE) {
      ++count;
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
    Pattern myPattern = Pattern.compile(RegexPattern.SENTENCE_SEPARATOR.getRegex());
    return text.replaceAll(RegexPattern.QUOTE_AND_DASH.getRegex(),
        RegexPattern.EMPTY_STRING.getRegex()).split(myPattern.pattern());
  }

  private Matcher phraseSeparatorPattern(String sentence) {
    Pattern pattern = Pattern.compile(RegexPattern.PHRASE_SEPARATOR.getRegex());
    return pattern.matcher(sentence);
  }
}