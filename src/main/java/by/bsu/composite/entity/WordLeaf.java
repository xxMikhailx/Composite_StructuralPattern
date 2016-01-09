package by.bsu.composite.entity;

import org.apache.log4j.Logger;

/**
 * Created by Михаил on 16.12.2015.
 */
public class WordLeaf implements Component {

    private final static Logger LOG = Logger.getLogger(WordLeaf.class);

    String str;

    public WordLeaf(String str){
        this.str = str;
    }

    public void parse() {
        System.out.print(str + " ");
    }

    public void add(Component c) {
        LOG.info("WordLeaf -> add. Doing nothing");
    }

    public void remove(Component c) {
        LOG.info("WordLeaf -> remove. Doing nothing");
    }

    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return str;
    }
}
