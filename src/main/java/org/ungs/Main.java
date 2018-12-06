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

        Clazz Aclass = new Clazz(A.class);

        InheritanceNode node = factory.getTreeOf(Aclass);

        Evaluator SiTIEvaluator = new Evaluator(new ScoringClass("SiTI"),
        		new EvaluatorConfigBuilder().addCoeficient("fields", -2.0).build());
        

        Evaluator ClTIEvaluator = new Evaluator(new ScoringClass("ClTI"),
        		new EvaluatorConfigBuilder().addCoeficient("height", -1.0).build());
        

        Evaluator CoTIEvaluator = new Evaluator(new ScoringClass("CoTI"),
        		new EvaluatorConfigBuilder().addCoeficient("abstract", -2.0).build());

        Classifier classifier = new Classifier(new ScoringClass("AD-Hoc"));
        classifier.addEvaluator(CoTIEvaluator);
        classifier.addEvaluator(SiTIEvaluator);
        classifier.addEvaluator(ClTIEvaluator);
        
        ScoringClass end = classifier.classify(node);
        
        
        System.out.println(end.getName());
    }
}
