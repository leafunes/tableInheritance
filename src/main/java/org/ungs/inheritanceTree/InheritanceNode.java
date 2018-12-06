package org.ungs.inheritanceTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.ungs.classifier.Attribute;
import org.ungs.classifier.Classifiable;
import org.ungs.clazz.Clazz;


public class InheritanceNode implements Iterable<InheritanceNode>, Classifiable{

	private List<InheritanceNode> neighborhood;
    private Clazz content;

    public InheritanceNode(List<InheritanceNode> neighborhood, Clazz content) {
        this.neighborhood = neighborhood;
        this.content = content;
    }
    
    public Clazz getContent() {
		return content;
	}
    
    public int getHeight() {
    	
    	if(this.neighborhood.isEmpty())
    		return 1;
    	
    	return 1 + neighborhood.stream().mapToInt(x -> x.getHeight()).max().getAsInt();
    }
    
    public int getAbstractClasses() {
    	
    	int thisAbstract = this.content.isAbstract() ? 1 : 0;
    	
    	if(neighborhood.isEmpty())
        	return thisAbstract;
	 
		return thisAbstract + neighborhood.stream()
					    		.mapToInt(x -> x.getAbstractClasses())
					    		.sum();
    	
    }
    
    public int getExtraFields() {
    	
    	if(neighborhood.isEmpty())
        	return 0;

    	int thisFields = this.content.getAllFields().size();
    	
    	int toRet = 0;
    	
    	for(InheritanceNode n : neighborhood) {
    		toRet += n.getContent().getAllFields().size() - thisFields;
    		toRet += n.getExtraFields();
    	}
    	
    	return toRet;
    	
    	
    }

    @Override
    public Iterator<InheritanceNode> iterator() {
        return neighborhood.iterator();
    }

	@Override
	public Collection<Attribute> getAttributes() {
		
		List<Attribute> toRet = new ArrayList<>();
		
		Attribute heigth = new Attribute("height", (double) getHeight());
		Attribute abstractClasses = new Attribute("abstract", (double) getAbstractClasses());
		Attribute extraFields = new Attribute("fields", (double) getExtraFields());
		
		toRet.add(heigth);
		toRet.add(abstractClasses);
		toRet.add(extraFields);
		
		return toRet;
		
	}
}
