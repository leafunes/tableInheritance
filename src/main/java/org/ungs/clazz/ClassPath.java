package org.ungs.clazz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassPath {

    private static ClassPath instance;

    public static ClassPath getInstance(){
        if(instance == null)
            instance = new ClassPath();
        return instance;
    }

    private List<File> classpath;

    private ClassPath() {

        String classpathStr = System.getProperty("java.class.path");
        String[] classpathSliced = classpathStr.split(File.pathSeparator);
        this.classpath = new ArrayList<>();

        for (String path :
                classpathSliced) {
            this.classpath.add(new File(path));
        }

    }

    public List<Clazz> getAllClasses(){

        List<Clazz> toRet = new ArrayList<>();


        for (File file : this.classpath) if(file.isDirectory()) {
                for(File insider : getAllFiles(file)) if(insider.getName().endsWith(".class")){
                    toRet.add(new Clazz(insider));
            }
        }else if(file.getName().endsWith(".class"))
            toRet.add(new Clazz(file));

        return toRet;

    }

    //TODO esto tiene que ir aca?
    private List<File> getAllFiles(File dir){

        File[] allFiles = dir.listFiles();
        List<File> toRet = new ArrayList<>();
        for (File f : allFiles) {
            if(f.isDirectory()){
                toRet.addAll(getAllFiles(f));
            }else{
                toRet.add(f);
            }
        }
        return toRet;
    }


}
