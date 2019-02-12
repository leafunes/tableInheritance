package org.ungs.inheritanceTree;

import java.util.ArrayList;
import java.util.List;

import org.ungs.clazz.ClassPath;

public class InheritanceTreeFactory {



    //TODO: esta bien este nombre? estoy devolviendo un arbol en realidad
    public InheritanceNode getRootNodeOf(Class base){
        
        List<InheritanceNode> nehi = getSons(base);

        return new InheritanceNode(nehi, base);

    }
    
    private List<InheritanceNode> getSons(Class clazz){

        ClassPath classPath = ClassPath.getInstance();
        List<InheritanceNode> sons = new ArrayList<>();

        for(Class c : classPath.getAllClasses()) if(!c.equals(clazz) && isSubclassOf(c, clazz)){
        	sons.add(new InheritanceNode(getSons(c), c));
        }
        
        return sons;
        
    }

    // Esta funcion no tiene mucho sentido que este aca, pero 
    // encapsula y hace otras funciones mas legibles
    // No se usa el instanceOf porque necesito que los objetos esten instanciados
    private boolean isSubclassOf(Class a, Class b){
        
        if(a.getSuperclass() == null)
        return false;
    
        return a.getSuperclass().equals(b);

    }

}

