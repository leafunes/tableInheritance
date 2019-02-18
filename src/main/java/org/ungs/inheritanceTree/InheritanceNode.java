package org.ungs.inheritanceTree;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;


public class InheritanceNode implements Iterable<InheritanceNode>{

	private List<InheritanceNode> children;
    private Class clazz;

    protected InheritanceNode(List<InheritanceNode> children, Class content) {
        this.children = children;
        this.clazz = content;
    }
    
    protected Class getClazz() {
		return clazz;
	}
    
    // Esta funcion no deberia ir aca, pues el nodo no tiene altura
    // pero para hacerla recursiva, es mas facil que esté acá que en el arbol
    // igualmente, es protected y solo el arbol puede acceder a ella
    protected int getHeight() {
    	
    	if(this.children.isEmpty())
    		return 1;
    	
    	return 1 + children.stream().mapToInt(x -> x.getHeight()).max().getAsInt();
    }
    
    // Esta funcion no deberia ir aca
    // pero para hacerla recursiva, es mas facil que esté acá que en el arbol
    // igualmente, es protected y solo el arbol puede acceder a ella
    protected int getCountAbstractClasses() {
    	
    	int thisClazzAbstractWeight = Modifier.isAbstract(this.clazz.getModifiers()) ? 1 : 0;
    	
    	if(children.isEmpty())
        	return thisClazzAbstractWeight;
	 
		return thisClazzAbstractWeight + children.stream()
					    		.mapToInt(x -> x.getCountAbstractClasses())
					    		.sum();
    	
    }
    
    // Esta funcion no deberia ir aca
    // pero para hacerla recursiva, es mas facil que esté acá que en el arbol
    // igualmente, es protected y solo el arbol puede acceder a ella
    protected int getCountExtraFields() {
    	
    	if(children.isEmpty())
        	return 0;

    	int toRet = 0;
		
    	for(InheritanceNode n : children) {
    		toRet += n.getClazz().getDeclaredFields().length;
    		toRet += n.getCountExtraFields();
    	}
    	
    	return toRet;
    	
    }


    @Override
    public Iterator<InheritanceNode> iterator() {
        return children.iterator();
    }
}
