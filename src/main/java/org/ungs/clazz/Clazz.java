package org.ungs.clazz;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.ungs.inheritanceTree.InheritanceNode;

public class Clazz {

    private Class clazz;
    private String name;

    public Clazz(File path) {
        this.name = path.getName().replace(".class", "");

        this.clazz = ClassTryLoader.getInstance().tryToLoad(path);

    }

    public Clazz(Class c){
        this.name = c.getSimpleName();
        this.clazz = c;
    }

    public boolean isSubclassOf(Clazz other){

    	if(this.getClazz().getSuperclass() == null)
    		return false;
    	
        return this.getClazz().getSuperclass().equals(other.getClazz());

    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz1 = (Clazz) o;
        return Objects.equals(clazz, clazz1.clazz) &&
                Objects.equals(name, clazz1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, name);
    }

	public boolean isAbstract() {
		
		return Modifier.isAbstract(this.clazz.getModifiers());
		
	}

	public List<Field> getAllFields() {
		
        List<Field> fields = new ArrayList<>();
        Class internal = clazz;
        while (internal != Object.class) {
            fields.addAll(Arrays.asList(internal.getDeclaredFields()));
            internal = internal.getSuperclass();
        }
        return fields;
		
		
	}
}
