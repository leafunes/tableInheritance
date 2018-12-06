package org.ungs.clazz;

import java.io.*;
import java.util.Objects;

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
}
