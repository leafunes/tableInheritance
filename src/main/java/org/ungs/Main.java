package org.ungs;

import org.ungs.inheritanceTree.InheritanceNode;
import org.ungs.inheritanceTree.InheritanceTree;
import org.ungs.inheritanceTree.InheritanceTreeFactory;
import org.ungs.test.A;

public class Main {

    public static void main(String[] args) {

        InheritanceTreeFactory factory = new InheritanceTreeFactory();
        TreeClassifier classifier = new TreeClassifier();

        InheritanceTree tree = factory.getTreeOf(A.class);

        String end = classifier.classify(tree);
        
        System.out.println(end);

    }
}
