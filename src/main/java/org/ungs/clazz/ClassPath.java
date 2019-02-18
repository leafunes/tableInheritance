package org.ungs.clazz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public List<Class> getAllClasses(){

        List<Class> toRet = new ArrayList<>();

        for (File file : this.classpath) if(file.isDirectory()) {
                for(File insider : getAllFiles(file)) if(insider.getName().endsWith(".class")){
                    toRet.add(ClassTryLoader.tryToLoad(insider));
            }
        }else if(file.getName().endsWith(".class"))
            toRet.add(ClassTryLoader.tryToLoad(file));

        return toRet;

    }
    
    public Stream<Class> getAllClassesAsStream(){
    	return this.getAllClasses().stream();
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
