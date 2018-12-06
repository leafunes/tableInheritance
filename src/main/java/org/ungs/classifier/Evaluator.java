package org.ungs.classifier;

public class Evaluator {
	
	private ScoringClass scoringClass;
	private EvaluatorConfig evaluatorConfig;
	
	public Evaluator(ScoringClass scoringClass, EvaluatorConfig evaluatorConfig) {
		
		this.scoringClass = scoringClass;
		this.evaluatorConfig = evaluatorConfig;
		
	}
	
	public EvaluatorResult getScore(Classifiable thing) {
		
		Double score = 0.0;
		
		for(Attribute attr : thing.getAttributes()) {
			score += attr.getScore() * this.evaluatorConfig.getCoeficientFor(attr.getName());
		}
		
		return new EvaluatorResult(scoringClass, score);
		
	}
	
	

}
