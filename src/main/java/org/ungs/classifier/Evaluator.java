package org.ungs.classifier;

import java.util.Map;

public class Evaluator {
	
	private Map<String, Double> evaluatorConfig;
	
	public Evaluator(Map<String, Double>  evaluatorConfig) {
		
		this.evaluatorConfig = evaluatorConfig;
		
	}
	
	public Double getScore(Classifiable thing) {
		
		Double score = 0.0;
		
		for(Attribute attr : thing.getAttributes()) {
			score += attr.getScore() * this.getCoeficientFor(attr.getName());
		}
		
		return score;
		
	}

	private Double getCoeficientFor(String attr){

		Double coef = this.evaluatorConfig.get(attr);
		if(coef == null)
			return 0.0;
		
		return coef;
	}
	
	

}
