package org.ungs;

import org.ungs.inheritanceTree.InheritanceNode;
import org.ungs.inheritanceTree.InheritanceTreeFactory;
import org.ungs.test.A;

public class Main {

    public static void main(String[] args) {

        InheritanceTreeFactory factory = new InheritanceTreeFactory();
        TreeClassifier classifier = new TreeClassifier();

        InheritanceNode node = factory.getRootNodeOf(A.class);

        String end = classifier.classify(node);
        
        System.out.println(end);

    }
}
