package com.polytech.di5.model;


public class Cost {
	private Processus dest;
	private int cost;
	
	public Cost(Processus p, int c){
		this.dest=p;
		this.setCost(c);
	}
	
	public boolean equals(Object c)
	{
	
		return this.dest.equals(((Cost)c).dest);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
