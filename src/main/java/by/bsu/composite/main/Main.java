package by.bsu.composite.main;

import by.bsu.composite.actions.SentenceSort;
import by.bsu.composite.actions.TextParser;
import by.bsu.composite.actions.WordSwap;
import by.bsu.composite.entity.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Михаил on 14.12.2015.
 */
public class Main {

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private final static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String path = "text.txt";
        TextParser textParser = new TextParser();
        Composite wholeText = textParser.parse(path);
        wholeText.parse();

        System.out.println("\n----Start of swaping-----");
        WordSwap.swapFirstLast(wholeText);
        System.out.println("\n----End of swaping----");
        System.out.println("\n----Start of ordering sentences-----");
        SentenceSort.sortWordAmount(wholeText);
        System.out.println("\n----End of ordering sentences----");
    }

}
