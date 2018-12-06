package org.ungs.classifier;

import java.util.HashMap;

public class EvaluatorConfigBuilder {
	

	private HashMap<String, Double> coeficients;
	
	public EvaluatorConfigBuilder() {
		this.coeficients = new HashMap<>();
	}
	
	public EvaluatorConfigBuilder addCoeficient(String attrName, Double coef) {
		this.coeficients.put(attrName, coef);
		
		return this;
	}
	
	public EvaluatorConfig build() {
		
		return new EvaluatorConfig(coeficients);
		
	}

}
