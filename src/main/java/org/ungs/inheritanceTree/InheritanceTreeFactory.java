package org.ungs.inheritanceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.ungs.clazz.ClassPath;

public class InheritanceTreeFactory {


	// Solo el factory puede instanciar un arbol
    public InheritanceTree getTreeOf(Class base){
        
        List<InheritanceNode> childs = getChilds(base);
        InheritanceNode root = new InheritanceNode(childs, base);

        return new InheritanceTree(root);

    }
    
    private List<InheritanceNode> getChilds(Class superClass){

        // Buscamos todas las clases que extiendan de "superClass"
        return ClassPath.getInstance()
        		.getAllClassesAsStream()
        		.filter(x -> x.getSuperclass() != null)
        		.filter(x -> x.getSuperclass().equals(superClass))
        		.map(x -> new InheritanceNode(getChilds(x), x))
        		.collect(Collectors.toList());
    }


}

