package com.polytech.di.modele;

import java.util.ArrayList;

public class Machine {
	private int idMachine;
	private int nbCPU;
	private int nbRAM;
	private int nbDD;

	public ArrayList<ArrayList<String>> occupationCPU;
	public ArrayList<Integer> CPURest;
	public ArrayList<Integer> RAMRest;
	public ArrayList<Integer> DDRest;
	
	
	public int getNbCPU() {
		return nbCPU;
	}

	public void setNbCPU(int nbCPU) {
		this.nbCPU = nbCPU;
	}
	public Machine(int id, int c, int r, int d) {
		this.setIdMachine(id);
		this.nbCPU = c;
		this.nbDD = d;
		this.nbRAM = r;

		occupationCPU = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < c; i++) {
			ArrayList<String> list = new ArrayList<String>();
			occupationCPU.add(list);
		}
		CPURest=new ArrayList<Integer>();
		RAMRest=new ArrayList<Integer>();
		DDRest=new ArrayList<Integer>();

	}
	
	public int fisrtStartTime(int start, Processus p){
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
		int fst=this.fisrtStartTime(start,p);
		for(int i=fst;i<fst+p.getDuree();i++){
			if(CPURest.size()<i){
				CPURest.set(i, nbCPU-p.getNbCPU());
			}else{
				CPURest.add(nbCPU-p.getNbCPU());
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

	public String toString() {
		return String.valueOf(idMachine) + " " + String.valueOf(nbCPU) + " " + String.valueOf(nbRAM) + " "
				+ String.valueOf(nbDD);

	}

}
