package org.ungs;

import java.io.File;

public class ClassTryLoader {

    private static ClassTryLoader instance;


    public static ClassTryLoader getInstance(){
        if(instance == null)
            instance = new ClassTryLoader();
        return instance;
    }

    private ClassTryLoader() {

    }

    public Class tryToLoad(File f){
        String[] pathParts = f.getAbsolutePath().split('\\'+File.separator);
        //HACK
        pathParts[pathParts.length - 1] = pathParts[pathParts.length - 1].replace(".class", "");
        String className = pathParts[pathParts.length - 1];
        Class toRet = null;

        for(int i = pathParts.length - 2; i >= 0; i--){
            String current = pathParts[i];
            try{
                toRet = Class.forName(className);
                return toRet;
            } catch (ClassNotFoundException e) {

            }
            className = current + "." + className;

        }

        throw new RuntimeException();
    }
}
