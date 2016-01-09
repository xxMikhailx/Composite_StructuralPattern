package by.bsu.composite.actions;

import by.bsu.composite.entity.Component;
import by.bsu.composite.entity.Composite;
import by.bsu.composite.entity.PunctuationLeaf;
import by.bsu.composite.entity.WordLeaf;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Михаил on 28.12.2015.
 */
public class SentenceSort {

    private final static Logger LOG = Logger.getLogger(SentenceSort.class);

    public static void sortWordAmount(Composite wholeText){
        ArrayList<ArrayList<Component>> componentList = new ArrayList<ArrayList<Component>>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Composite text = (Composite) wholeText.getChild(0);
        Composite paragraph = null;
        int countSentence = 0;

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
                LOG.debug("paragraph size: " + paragraph.getSize());
                Composite sentence = (Composite) paragraph.getChild(j);
                LOG.debug("sentence size: " + sentence.getSize());
                ArrayList<Component> sentenceList = new ArrayList<Component>();
                countSentence++;
                int countWord = 0;
                for (int k = 0; k < sentence.getSize(); k++) {
                    if (sentence.getChild(k).getClass().getSimpleName().equals("WordLeaf")) {
                        Component word = (WordLeaf) sentence.getChild(k);
                        if (!("").equals(word.toString())) {
                            countWord++;
                            sentenceList.add(word);
                        }
                    } else {
                        Component sign = (PunctuationLeaf) sentence.getChild(k);
                        if (!("").equals(sign.toString())) {
                            sentenceList.add(sign);
                        }
                    }
                }
                map.put(countSentence, countWord);
                componentList.add(sentenceList);
            }
        }

        SortedSet<Map.Entry<Integer, Integer>> treeSet = entriesSortedByValues(map);

        for (Map.Entry<Integer, Integer> entry: treeSet) {
            System.out.println();
            for (Component sentence : componentList.get(entry.getKey()-1)) {
                System.out.print(sentence);
                System.out.print(" ");
            }
        }
    }

    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
