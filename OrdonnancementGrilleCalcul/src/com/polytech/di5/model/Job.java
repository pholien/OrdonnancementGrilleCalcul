package com.polytech.di5.model;

import java.util.ArrayList;

public class Job {
	private int idJob;
	public ArrayList<Processus> listProcessus;
	public ArrayList<Processus> listOrdo;
	private boolean memoryDistribued;
	
	public Job(int id,boolean type){
		this.idJob=id;
		this.memoryDistribued=type;
		listProcessus=new ArrayList<Processus>();
		listOrdo=new ArrayList<Processus>();
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
	
	public void ajouterProcessus(Processus p){
		this.listProcessus.add(p);
	}

}
