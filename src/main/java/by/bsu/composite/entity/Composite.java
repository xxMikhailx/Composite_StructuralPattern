package by.bsu.composite.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Михаил on 14.12.2015.
 */
public class Composite implements Component {

    private final static Logger LOG = Logger.getLogger(Composite.class);
    private ArrayList<Component> components = new ArrayList<Component>();

    public void parse(){
        System.out.println("");
        for (Component component : components) {
            component.parse();
        }
    }

    public void add(Component component){
        LOG.info("Composite -> Adding component");
        components.add(component);
    }

    public void remove(Component component){
        LOG.info("Composite -> Removing component");
        components.remove(component);
    }

    public int getSize(){
        return components.size();
    }

    public Object getChild(int index){
        LOG.info("Composite -> Getting component");
        return components.get(index);
    }

}
