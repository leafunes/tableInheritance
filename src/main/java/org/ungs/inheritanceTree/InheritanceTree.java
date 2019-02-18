package org.ungs.inheritanceTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ungs.classifier.Attribute;
import org.ungs.classifier.Classifiable;

public class InheritanceTree implements Classifiable{

    private InheritanceNode root;

    protected InheritanceTree(InheritanceNode root){
    	this.root = root;
        
    }

    protected int getHeight() {
    
    	return this.root.getHeight();
    }
    
    private int getCountAbstractClasses() {
    	
    	return this.root.getCountAbstractClasses();
    	
    }
    
    private int getCountExtraFields() {
    	
    	return this.root.getCountExtraFields();
    	
    }

    //El arbol conoce sus atributos
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