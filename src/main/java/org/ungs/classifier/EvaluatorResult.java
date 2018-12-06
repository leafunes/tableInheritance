package org.ungs.classifier;

public class EvaluatorResult implements Comparable<EvaluatorResult>{

	private ScoringClass scoringClass;
	private Double score;
	
	public EvaluatorResult(ScoringClass scoringClass, Double score) {
		super();
		this.scoringClass = scoringClass;
		this.score = score;
	}
	public ScoringClass getScoringClass() {
		return scoringClass;
	}
	public Double getScore() {
		return score;
	}
	
	@Override
	public int compareTo(EvaluatorResult o) {
		
		return score.compareTo(o.getScore());
	}
	
	
	
}
