package by.bsu.composite.actions;

import by.bsu.composite.entity.Composite;
import by.bsu.composite.entity.ListingLeaf;
import by.bsu.composite.entity.PunctuationLeaf;
import by.bsu.composite.entity.WordLeaf;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Михаил on 14.12.2015.
 */
public class TextParser {
    private final static Logger LOG = Logger.getLogger(TextParser.class);

    final static String REGEX_PARAGRAPH_WITH_LISTING = "((?s)((\t)[^\t|#{2}]+)|((?<=##//)(.*?)(?=/##)))";
    final static String REGEX_PARAGRAPH = "((\t)[^\t|#{2}]+)";
    final static String REGEX_SENTENCE = "((\\s*)[А-ЯA-Z]((т.п.|т.д.|пр.|etc.)|[^?!.\\(]|\\([^\\)]*\\))*[.?!:])";
    final static String REGEX_LEXEME = "((\\s*)[А-ЯA-Zа-яa-z]*(\\p{Blank}|\\p{Punct})|(\\p{Punct}\\p{Blank}))";
    final static String REGEX_WORD = "(?x)([А-ЯA-Zа-яa-z]*)";
    final static String REGEX_PUNCTUATION = "\\p{Punct}";

    public TextParser(){

    }

    public Composite parse(String path){
        String text = initialization(path);
        Composite wholeText = new Composite();
        wholeText = textToParagraphs(wholeText, text);
        return wholeText;
    }

    private String initialization(final String path) {
        String text = "";
        try {
            FileInputStream inFile = new FileInputStream(path);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            text = new String(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private Composite textToParagraphs(Composite wholeText, String text){
        Composite paragraphList = new Composite();
        ListingLeaf paragraphLeaf;

        Pattern textPatternListingParagraph = Pattern.compile(REGEX_PARAGRAPH_WITH_LISTING);
        Matcher textMatcherListingParagraph = textPatternListingParagraph.matcher(text);

        while (textMatcherListingParagraph.find()){
            String paragraphAndListing = textMatcherListingParagraph.group();
            LOG.info("ParagraphOrListing: " + paragraphAndListing);

            if (Pattern.matches(REGEX_PARAGRAPH, paragraphAndListing)){
                LOG.info("Paragraph: " + paragraphAndListing);
                paragraphList = paragraphToSentences(paragraphList, paragraphAndListing);
            } else {
                paragraphLeaf = new ListingLeaf(paragraphAndListing);
                LOG.info("Listing: " + paragraphLeaf);
                paragraphList.add(paragraphLeaf);
            }
        }
        wholeText.add(paragraphList);
        return wholeText;
    }

    private Composite paragraphToSentences(Composite paragraphList, String paragraph){
        Composite sentenceList = new Composite();
        String sentence;
        Pattern textPatternSentence = Pattern.compile(REGEX_SENTENCE);
        Matcher textMatcherSentence = textPatternSentence.matcher(paragraph);

        while (textMatcherSentence.find()){
            sentence = textMatcherSentence.group();
            LOG.info("Sentence: " + sentence);
            sentenceList = sentenceToLexemes(sentenceList, sentence);
        }

        paragraphList.add(sentenceList);
        return paragraphList;
    }

    private Composite sentenceToLexemes(Composite sentenceList, String sentence){
        Composite lexemeList = new Composite();
        String lexeme;
        WordLeaf wordLeaf;
        PunctuationLeaf signLeaf;
        Pattern textPatternLexeme = Pattern.compile(REGEX_LEXEME);
        Matcher textMatcherLexeme = textPatternLexeme.matcher(sentence);
        Pattern textPatternWord = Pattern.compile(REGEX_WORD);
        Pattern textPatternPunctuation = Pattern.compile(REGEX_PUNCTUATION);

        while (textMatcherLexeme.find()){
            lexeme = textMatcherLexeme.group().trim();
           LOG.info("Lexeme: " + lexeme);
            Matcher textMatcherWord = textPatternWord.matcher(lexeme);
            if (textMatcherWord.find()) {
                String word = textMatcherWord.group();
                wordLeaf = new WordLeaf(word);
                LOG.info("Word: " + wordLeaf);
                lexemeList.add(wordLeaf);
            }
            Matcher textMatcherPunctuation = textPatternPunctuation.matcher(lexeme);
            if (textMatcherPunctuation.find()) {
                String sign = textMatcherPunctuation.group();
                signLeaf = new PunctuationLeaf(sign);
                LOG.info("Sign: " + signLeaf);
                lexemeList.add(signLeaf);
            }
        }

        sentenceList.add(lexemeList);
        return sentenceList;
    }

}
