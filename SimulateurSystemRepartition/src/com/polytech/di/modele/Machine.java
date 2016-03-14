package com.polytech.di.modele;

import java.util.ArrayList;

public class Machine {
	//identification de machine
	private int idMachine;
	//nombre de CPU
	private int nbCPU;
	//nombre de RAM
	private int nbRAM;
	//nombre de disque dur
	private int nbDD;
	
	//l'occupation de CPU
	public ArrayList<Integer> CPURest;
	//l'occupation de RAM
	public ArrayList<Integer> RAMRest;
	//l'occupation de disque dur 
	public ArrayList<Integer> DDRest;
	//nombre de job 
	public ArrayList<Integer> nbJob;
	

	public Machine(int id, int c, int r, int d) {
		/**
		 * constructeur, pour initialiser un machine, preciser les ressources 
		 * et l'utilisation de chaque ressource
		 * */
		this.setIdMachine(id);
		this.nbCPU = c;
		this.nbDD = d;
		this.nbRAM = r;

		CPURest=new ArrayList<Integer>();
		RAMRest=new ArrayList<Integer>();
		DDRest=new ArrayList<Integer>();
		nbJob=new ArrayList<Integer>();

	}
	
	public int fisrtStartTime(int start, Processus p){
		/**
		 * Entree: le début plus tôt, et un processus
		 * sortie: le début 
		 * selon l'utilisation de ressources, décider quand exécuter le processus.
		 * */
		int position=CPURest.size();
		for(int i=start;i<CPURest.size();i++){
			//considerer le 
			
			if(p.getNbCPU()<=CPURest.get(i) && p.getNbRAM()<=RAMRest.get(i) && p.getNbDD()<=DDRest.get(i)){
				//Si le duree de processus est plus petit que le listRest
				if(p.getDuree()>=CPURest.size()-i){
					int tmp=i;
					for(int j=i;j<CPURest.size()-i;j++){
						if(p.getNbCPU()>CPURest.get(j) || p.getNbRAM()>RAMRest.get(j) || p.getNbDD()>DDRest.get(j)){
							tmp=j;
							break;
						}
					}
					if(tmp==i){
						position=i;
					}
					
				}else {
					int tmp=i;
					for(int j=i;j<i+p.getDuree();j++){
						if(p.getNbCPU()>CPURest.get(j) || p.getNbRAM()>RAMRest.get(j) || p.getNbDD()>DDRest.get(j)){
							tmp=j;
							break;
						}
					}
					if(tmp==i){
						position=i;
					}
				}
			}
		}
				
		return position;
	}
	
	public void affectationRessources(int start,Processus p){
		/**
		 * Entree: le début de processus et un processus
		 * nous savons le début, affecter les ressources pour processus p. et ajouter fin au processus.
		 * */
		int fst=this.fisrtStartTime(start,p);
		for(int i=fst;i<fst+p.getDuree();i++){
			if(CPURest.size()<i){
				CPURest.set(i, nbCPU-p.getNbCPU());				
			}else{
				CPURest.add(nbCPU-p.getNbCPU());				
			}
			if(nbJob.size()<i){
				nbJob.set(i, nbJob.get(i)+1);
			}else{
				nbJob.add(1);
			}
			
			if(RAMRest.size()<i){
				RAMRest.set(i, nbRAM-p.getNbRAM());
			}else{
				RAMRest.add(nbRAM-p.getNbRAM());
			}
			if(DDRest.size()<i){
				DDRest.set(i, nbDD-p.getNbDD());
			}else{
				DDRest.add(nbDD-p.getNbDD());
			}
		}
		p.setFinProcessus(fst+p.getDuree());
		
	}
	
	public int CMAX(){
		return CPURest.size();
	}

	public int getIdMachine() {
		return idMachine;
	}

	public void setIdMachine(int idMachine) {
		this.idMachine = idMachine;
	}
	
	
	public int getNbCPU() {
		return nbCPU;
	}

	public void setNbCPU(int nbCPU) {
		this.nbCPU = nbCPU;
	}

	public String toString() {
		return String.valueOf(idMachine) + " " + String.valueOf(nbCPU) + " " + String.valueOf(nbRAM) + " "
				+ String.valueOf(nbDD);

	}

}
