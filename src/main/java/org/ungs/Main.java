package org.ungs;

import org.ungs.classifier.Classifier;
import org.ungs.classifier.Evaluator;
import org.ungs.classifier.EvaluatorConfigBuilder;
import org.ungs.classifier.ScoringClass;
import org.ungs.clazz.Clazz;
import org.ungs.inheritanceTree.InheritanceNode;
import org.ungs.inheritanceTree.InheritanceTreeFactory;
import org.ungs.test.A;
import org.ungs.test.B;
import org.ungs.test.C;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        InheritanceTreeFactory factory = new InheritanceTreeFactory();
        TreeClassifier classifier = new TreeClassifier();

        Clazz Aclass = new Clazz(A.class);

        InheritanceNode node = factory.getTreeOf(Aclass);

        ScoringClass end = classifier.classify(node);
        
        System.out.println(end.getName());
    }
}
