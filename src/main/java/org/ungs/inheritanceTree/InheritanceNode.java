package org.ungs.inheritanceTree;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.ungs.classifier.Attribute;
import org.ungs.classifier.Classifiable;


public class InheritanceNode implements Iterable<InheritanceNode>, Classifiable{

	private List<InheritanceNode> sons;
    private Class clazz;

    public InheritanceNode(List<InheritanceNode> sons, Class content) {
        this.sons = sons;
        this.clazz = content;
    }
    
    public Class getClazz() {
		return clazz;
	}
    
    private int getHeight() {
    	
    	if(this.sons.isEmpty())
    		return 1;
    	
    	return 1 + sons.stream().mapToInt(x -> x.getHeight()).max().getAsInt();
    }
    
    private int getCountAbstractClasses() {
    	
    	int thisClazzAbstractWeight = Modifier.isAbstract(this.clazz.getModifiers()) ? 1 : 0;
    	
    	if(sons.isEmpty())
        	return thisClazzAbstractWeight;
	 
		return thisClazzAbstractWeight + sons.stream()
					    		.mapToInt(x -> x.getCountAbstractClasses())
					    		.sum();
    	
    }
    
    private int getCountExtraFields() {
    	
    	if(sons.isEmpty())
        	return 0;

    	int toRet = 0;
		
    	for(InheritanceNode n : sons) {
    		toRet += n.getClazz().getDeclaredFields().length;
    		toRet += n.getCountExtraFields();
    	}
    	
    	return toRet;
    	
    }

    @Override
    public Iterator<InheritanceNode> iterator() {
        return sons.iterator();
    }

	@Override
	public Collection<Attribute> getAttributes() {
		
		List<Attribute> toRet = new ArrayList<>();
		
		Attribute heigth = new Attribute("height", (double) getHeight());
		Attribute abstractClasses = new Attribute("abstract", (double) getCountAbstractClasses());
		Attribute extraFields = new Attribute("fields", (double) getCountExtraFields());
		
		toRet.add(heigth);
		toRet.add(abstractClasses);
		toRet.add(extraFields);
		
		return toRet;
		
	}
}
