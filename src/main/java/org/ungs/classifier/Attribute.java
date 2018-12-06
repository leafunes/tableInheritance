package org.ungs.classifier;

public class Attribute {
	
	private String name;
	private Double score;
	
	
	public Attribute(String name, Double score) {
		super();
		this.name = name;
		this.score = score;
	}


	public String getName() {
		return name;
	}


	public Double getScore() {
		return score;
	}
	
	
	
}
