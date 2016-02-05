package com.polytech.di5.model;

import java.util.ArrayList;

public class Processus {
	private int idProcessus;
	private int nbCPU;
	private int nbRAM;
	private int duree;
	public ArrayList<Cost> adjProcessus;

	public Processus(int id, int nbC, int nbR, int duree) {
		this.idProcessus = id;
		this.nbCPU = nbC;
		this.nbRAM = nbR;
		this.duree = duree;
		adjProcessus = new ArrayList<Cost>();
	}

	public Processus(Processus p){
		this.idProcessus=p.getIdProcessus();
		this.nbCPU=p.getNbCPU();
		this.nbRAM=p.getNbRAM();
		this.duree=p.getDuree();
	}
	
	public boolean equals(Object processus)
	{
		if(this.idProcessus==((Processus)processus).getIdProcessus()){
			return true;
		}
		
		return false;
	}

	public int getIdProcessus() {
		return idProcessus;
	}

	public void setIdProcessus(int idProcessus) {
		this.idProcessus = idProcessus;
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

}
