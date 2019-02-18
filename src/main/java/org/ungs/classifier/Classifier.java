package org.ungs.classifier;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.ungs.maths.Probability;



public class Classifier {
	
	private Map<String, Evaluator> evaluators;
	private String defaultClass;
	
	
	public Classifier(String defaultClass) {
		this.evaluators = new HashMap<>();
		this.defaultClass = defaultClass;
	}
	
	public void addEvaluator(String category, Evaluator evaluator) {
		this.evaluators.put(category, evaluator);
	}
	
	public String classify(Classifiable thing) {

		Map<String, Double> results = new HashMap<>();
		
		this.evaluators.forEach((name, eva) -> results.put(name, eva.getScore(thing)));
		
		//results.stream().forEach(x -> System.out.println(x.getScore() + " " + x.getScoringClass().getName()));
		
		if(isMaxElegible(results.values()))
			return Collections.max(results.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
		
		return defaultClass;
	
	}
	
	private boolean isMaxElegible(Collection<Double> values) {

		Double max = Collections.max(values);

		Double avg = values.stream()
	            .mapToDouble(x -> x)
	            .average()
	            .getAsDouble();
		
		Double stdDev = Probability.computeStandardDeviation(values);
		
		return (max >= avg + (2 * stdDev)) || (max >= avg - (2 * stdDev));
	
	}
	
	

}
