package by.bsu.composite.entity;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Михаил on 14.12.2015.
 */
public class ListingLeaf implements Component {

    private final static Logger LOG = Logger.getLogger(ListingLeaf.class);

    String str;

    public ListingLeaf(String str){
        this.str = str;
    }

    public void parse() {
        System.out.print(str + " ");
    }

    public void add(Component c) {
        LOG.info("ListingLeaf -> add. Doing nothing");
    }

    public void remove(Component c) {
        LOG.info("ListingLeaf -> remove. Doing nothing");
    }

    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return str;
    }
}
