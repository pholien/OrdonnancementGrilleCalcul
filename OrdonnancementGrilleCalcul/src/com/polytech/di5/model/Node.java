package com.polytech.di5.model;

import java.util.ArrayList;

public class Node {
	private int idNode;
	private int nbCPU;
	private int nbRAM;
	
	public ArrayList<Integer> CPURest;
	public ArrayList<Integer> RAMRest;
	
	public Node(int id,int nomberCPU,int nomberRAM){
		this.idNode=id;
		this.nbCPU=nomberCPU;
		this.nbRAM=nomberRAM;
		
		CPURest=new ArrayList<Integer>();
		RAMRest=new ArrayList<Integer>();
	}
	
	public int CMAX(){
		return CPURest.size();
	}
	
	
	
	public int fisrtStartTime(int start, Processus p){
		int position=CPURest.size();
		for(int i=start;i<CPURest.size();i++){
			//considerer le 
			
			if(p.getNbCPU()<=CPURest.get(i) && p.getNbRAM()<=RAMRest.get(i) ){
				//Si le duree de processus est plus petit que le listRest
				if(p.getDuree()>=CPURest.size()-i){
					int tmp=i;
					for(int j=i;j<CPURest.size()-i;j++){
						if(p.getNbCPU()>CPURest.get(j) || p.getNbRAM()>RAMRest.get(j)){
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
						if(p.getNbCPU()>CPURest.get(j) || p.getNbRAM()>RAMRest.get(j) ){
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
			
		}
		p.setFin(fst+p.getDuree());
		
	}

}
