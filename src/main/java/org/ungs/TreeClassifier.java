package org.ungs;

import org.ungs.classifier.Classifier;
import org.ungs.classifier.Evaluator;
import org.ungs.classifier.EvaluatorConfigBuilder;
import org.ungs.classifier.ScoringClass;
import org.ungs.inheritanceTree.InheritanceNode;

public class TreeClassifier{

	private Classifier classifier;

	public TreeClassifier() {
		
		Evaluator SiTIEvaluator = new Evaluator(new ScoringClass("SiTI"),
        		new EvaluatorConfigBuilder().addCoeficient("fields", -2.0).build());
        

        Evaluator ClTIEvaluator = new Evaluator(new ScoringClass("ClTI"),
        		new EvaluatorConfigBuilder().addCoeficient("height", -1.0).build());
        

        Evaluator CoTIEvaluator = new Evaluator(new ScoringClass("CoTI"),
        		new EvaluatorConfigBuilder().addCoeficient("abstract", -2.0).build());

        classifier = new Classifier(new ScoringClass("AD-Hoc"));
        classifier.addEvaluator(CoTIEvaluator);
        classifier.addEvaluator(SiTIEvaluator);
        classifier.addEvaluator(ClTIEvaluator);
	}

	
	public ScoringClass classify(InheritanceNode tree) {
		
		return classifier.classify(tree);
		
	}
	
	
	
}
