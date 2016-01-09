package by.bsu.composite.actions;

import by.bsu.composite.entity.Component;
import by.bsu.composite.entity.Composite;
import by.bsu.composite.entity.PunctuationLeaf;
import by.bsu.composite.entity.WordLeaf;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Михаил on 24.12.2015.
 */
public class WordSwap {

    private final static Logger LOG = Logger.getLogger(WordSwap.class);

    public static void swapFirstLast(Composite wholeText){
        Composite text = (Composite) wholeText.getChild(0);
        Composite paragraph = null;

        LOG.debug("text size: " + text.getSize());
        for (int i = 0; i < text.getSize(); i++) {
            LOG.debug(text.getChild(i).getClass().getSimpleName());
            if (text.getChild(i).getClass().getSimpleName().equals("ListingLeaf")) {
                continue;
            }
            else {
                paragraph = (Composite) text.getChild(i);
            }
            LOG.debug(paragraph.getClass().getSimpleName());
            for (int j = 0; j < paragraph.getSize(); j++) {
                ArrayList<Component> sentenceList = new ArrayList<Component>();
                LOG.debug("paragraph size: " + paragraph.getSize());
                Composite sentence = (Composite) paragraph.getChild(j);
                LOG.debug("sentence size: " + sentence.getSize());
                for (int k = 0; k < sentence.getSize(); k++) {
                    if (sentence.getChild(k).getClass().getSimpleName().equals("WordLeaf")) {
                        Component word = (WordLeaf) sentence.getChild(k);
                        if (!("").equals(word.toString())) {
                            sentenceList.add(word);
                        }
                    } else {
                        Component sign = (PunctuationLeaf) sentence.getChild(k);
                        if (!("").equals(sign.toString())) {
                            sentenceList.add(sign);
                        }
                    }
                }
                for (int n = sentenceList.size()-1; n > 0; n--) {
                    if (sentenceList.get(n).getClass().getSimpleName().equals("WordLeaf")){
                        Collections.swap(sentenceList, 0, n);
                        break;
                    }
                }
                System.out.println();
                for (Component x: sentenceList) {
                    System.out.print(x);
                    System.out.print(" ");
                }
            }
        }
    }

}
