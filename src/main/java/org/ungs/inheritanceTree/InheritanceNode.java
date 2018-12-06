package org.ungs.inheritanceTree;

import java.util.Iterator;
import java.util.List;

import org.ungs.clazz.Clazz;

public class InheritanceNode implements Iterable<InheritanceNode>{

	private List<InheritanceNode> neighborhood;
    private Clazz content;

    public InheritanceNode(List<InheritanceNode> neighborhood, Clazz content) {
        this.neighborhood = neighborhood;
        this.content = content;
    }
    
    public Clazz getContent() {
		return content;
	}

    @Override
    public Iterator<InheritanceNode> iterator() {
        return neighborhood.iterator();
    }
}
