package com.polytech.di.modele;

public class Processus {
	private int id;
	private int nbCPU;
	private int nbRAM;
	private int nbDD;
	private boolean isMap;
	private int duree;

	public Processus(int id, int c, int r, int d, boolean b, int duree) {
		this.setId(id);
		this.setNbCPU(c);
		this.setNbRAM(r);
		this.setNbDD(d);
		this.setMap(b);
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

	public boolean getMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
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
