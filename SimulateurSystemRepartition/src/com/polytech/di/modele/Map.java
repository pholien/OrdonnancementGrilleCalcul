package com.polytech.di.modele;

public class Map {
	private int id;
	private int nbCPU;
	private int nbRAM;
	private int nbDD;
	private int duree;
	
	public Map(int id, int c, int r, int d, int duree) {
		this.setId(id);
		this.setNbCPU(c);
		this.setNbRAM(r);
		this.setNbDD(d);
		this.setDuree(duree);
	}

	public int getNbCPU() {
		return nbCPU;
	}

	public void setNbCPU(int nbCPU) {
		this.nbCPU = nbCPU;
	}

	public int getNbRAM() {
		return nbRAM;
	}

	public void setNbRAM(int nbRAM) {
		this.nbRAM = nbRAM;
	}

	public int getNbDD() {
		return nbDD;
	}

	public void setNbDD(int nbDD) {
		this.nbDD = nbDD;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
