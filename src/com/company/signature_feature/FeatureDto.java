package com.company.signature_feature;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureDto {

  protected int getAllWordsLength(String text){
  int sumOfAllWordsLength = 0;
  String[] words = cleanAllPunctuation(text).split(" ");
    for (String word : words){
    int wordLength = word.length();
    sumOfAllWordsLength += wordLength;
  }
    return sumOfAllWordsLength;
}

  private String cleanAllPunctuation(String text) {
    return text.toLowerCase()
        .replaceAll("\\p{Punct}", "")
        .replaceAll("\n", "")
        .replaceAll("\r", " ")
        .replaceAll("\s", " ");
  }

  protected int getNumberOfAllWords(String text) {
    String[] words = cleanAllPunctuation(text).trim().split(" ");
    return words.length;
  }

  protected int getNumberOfUniqueWords(String text) {
    String[] words = cleanAllPunctuation(text).split(" ");
    HashSet<String> uniqueWords = new HashSet<String>(Arrays.asList(words));
    return uniqueWords.size();
  }

  protected int getNumberOfNonRecurringWords(String text) {
    String[] words = cleanAllPunctuation(text).split(" "); //getStrippedText(text);
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
    Locale currentLocale = new Locale("en", "US");
    BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
    return countSentences(sentenceIterator, text);
  }

  private int countSentences(BreakIterator bi, String source) {
    String newString = source.replaceAll("[A-Za-z]*[\\s][A-Z][.][\\s][A-Z]", "");
    int count = 0;
    bi.setText(newString);
    int boundary = bi.first();
    while (boundary != BreakIterator.DONE) {
      ++count;
      boundary = bi.next();
    }
    return count - 1;
  }

  protected int getNumberOfPhrasesInText(String text) {
    String[] textToSentences = separateTextToSentences(text);
    int countPhraseSeparators = 0;
    int countPhrases = 0;
    for (String sentence : textToSentences) {
      Matcher matcher = phraseSeparatorPattern(sentence);
      while (matcher.find()) {
        countPhraseSeparators++;
      }
      if (countPhraseSeparators > 0) {
        countPhrases = countPhrases + countPhraseSeparators + 1;
      }
      countPhraseSeparators = 0;
    }
    return countPhrases;
  }

  private String[] separateTextToSentences(String text) {
    String pattern = "([.!?])([\\s\\n\\r])";
    Pattern pt = Pattern.compile(pattern);
    return text.replaceAll("[\"]|[\\-]", "").split(pt.pattern());
  }

  private Matcher phraseSeparatorPattern(String sentence) {
    String pattern = "([,:;])([\\s\\n\\r])";
    Pattern pt = Pattern.compile(pattern);
    return pt.matcher(sentence);
  }
}