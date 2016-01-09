package by.bsu.composite.entity;

/**
 * Created by Михаил on 14.12.2015.
 */
public interface Component {
    void parse();
    void add(Component c);
    void remove(Component c);
    Object getChild(int index);
}
