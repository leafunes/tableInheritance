package org.ungs;

import java.util.ArrayList;
import java.util.List;

public class InheritanceTreeFactory {



    public InheritanceNode getTreeOf(Clazz base){
        
        List<InheritanceNode> nehi = getNeighborhood(base);

        return new InheritanceNode(nehi, base);

    }
    
    public List<InheritanceNode> getNeighborhood(Clazz clazz){

        ClassPath classPath = ClassPath.getInstance();
        List<InheritanceNode> nehi = new ArrayList<>();
        
        for(Clazz c : classPath.getAllClasses()) if(!c.equals(clazz) && c.isSubclassOf(clazz)){
        	nehi.add(new InheritanceNode(getNeighborhood(c), c));
        }
        
        return nehi;
        
    }

}

