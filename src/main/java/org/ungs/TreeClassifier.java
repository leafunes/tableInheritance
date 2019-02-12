package org.ungs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Triplet;
import org.ungs.classifier.Classifier;
import org.ungs.classifier.Evaluator;
import org.ungs.inheritanceTree.InheritanceNode;

public class TreeClassifier{

	private Classifier classifier;

	// Representa una tablita con los nombres de las estrategias,
	// y los atributos con sus coeficientes.
	private List<Triplet<String, String, Double>> evaluatorsData;

	public TreeClassifier() {
		evaluatorsData = new ArrayList<>();
		loadData();

		classifier = new Classifier("AD-Hoc");
		evaluatorsData.forEach(x -> 
			classifier.addEvaluator(x.getValue0(), 
				buildSimpleEvaluator(x.getValue1(), x.getValue2())
				)
			);
	}

	public String classify(InheritanceNode tree) {
		
		return classifier.classify(tree);
		
	}

	// TODO: El diseño es simplista, los evaluadores solo tienen un atributo con un coeficiente
	// Se deberia permitir tener muchos atributos
	private Evaluator buildSimpleEvaluator(String coefName, Double coefValue){
		Map<String, Double> coefs = new HashMap<>();
		coefs.put(coefName, coefValue);

		return new Evaluator(coefs);
	}

	private void loadData(){
		//TODO: deberia cargarse de un archivo
		evaluatorsData.add(Triplet.with("ConcreteTable", "abstract", -2.0));
		evaluatorsData.add(Triplet.with("SingleTable", "fields", -2.0));
		evaluatorsData.add(Triplet.with("ClassTable", "height", -1.0));
	}
	
	
	
}
