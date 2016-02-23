package com.polytech.di5.model;

import java.util.ArrayList;

public class Job {
	private int idJob;
	private int nbProcessus;
	public ArrayList<Processus> listProcessus;
	public int[][] adjProcessus;
	private boolean memoryDistribued;
	
	public Job(int id,boolean type,int nb){
		this.idJob=id;
		this.setNbProcessus(nb);
		this.memoryDistribued=type;
		listProcessus=new ArrayList<Processus>();
		adjProcessus=new int[nb][nb];
		for(int i=0;i<nb;i++){
			for(int j=0;j<nb;j++){
				adjProcessus[i][j]=0;
			}
		}
		
	}
	
	public int getIdJob() {
		return idJob;
	}
	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}
	public boolean isMemoryDistribued() {
		return memoryDistribued;
	}
	public void setMemoryDistribued(boolean memoryDistribued) {
		this.memoryDistribued = memoryDistribued;
	}
	public void addProcessus(Processus p){
		listProcessus.add(p);
	}

	public int getNbProcessus() {
		return nbProcessus;
	}

	public void setNbProcessus(int nbProcessus) {
		this.nbProcessus = nbProcessus;
	}
	
	

}
