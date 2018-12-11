package org.ungs.classifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.ungs.maths.Probability;



public class Classifier {
	
	private Collection<Evaluator> evaluators;
	private ScoringClass defaultClass;
	
	
	public Classifier(ScoringClass defaultClass) {
		this.evaluators = new ArrayList<>();
		this.defaultClass = defaultClass;
	}
	
	public void addEvaluator(Evaluator evaluator) {
		this.evaluators.add(evaluator);
	}
	
	public ScoringClass classify(Classifiable thing) {

		List<EvaluatorResult> results = evaluators.stream()
				.map(e -> e.getScore(thing))
				.sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		
		//results.stream().forEach(x -> System.out.println(x.getScore() + " " + x.getScoringClass().getName()));
		
		if(isElegible(results))
			return results.get(0).getScoringClass();
		
		return defaultClass;
	
	}
	
	private boolean isElegible(List<EvaluatorResult> sortedResults) {

		EvaluatorResult max = sortedResults.get(0);
		
		Double avg = sortedResults.stream()
	            .mapToDouble(x -> x.getScore())
	            .average()
	            .getAsDouble();
		
		Double stdDev = Probability.computeStandardDeviation(sortedResults
				.stream()
				.map(x -> x.getScore())
				.collect(Collectors.toList()));
		
		return (max.getScore() >= avg + (2 * stdDev)) || (max.getScore() >= avg - (2 * stdDev));
	
	}
	
	

}
