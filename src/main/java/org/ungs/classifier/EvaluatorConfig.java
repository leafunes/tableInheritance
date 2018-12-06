package org.ungs.classifier;

import java.util.HashMap;

public class EvaluatorConfig {

	private HashMap<String, Double> coeficients;

	protected EvaluatorConfig(HashMap<String, Double> coeficients) {
		super();
		this.coeficients = coeficients;
	}
	
	public Double getCoeficientFor(String attrName) {
		
		if(!this.coeficients.containsKey(attrName))
			return 0.0;
		
		return this.coeficients.get(attrName);
		
	}
	
	
}
