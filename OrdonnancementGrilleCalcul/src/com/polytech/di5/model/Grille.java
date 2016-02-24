package com.polytech.di5.model;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	private int nbMachine;
	public ArrayList<Node> listNode;
	
	public Grille(int id, int nbM){
		this.idGrille=id;
		this.nbMachine=nbM;
		listNode=new ArrayList<Node>();
	}
	
	public void ajouterNode(Node n){
		this.listNode.add(n);
	}
	
	public int firstStartTimeOnGrid(int start, Processus p){
		int tmpTime;
		int firstTime=Integer.MAX_VALUE;
		for(int i=0;i<listNode.size();i++){
			tmpTime=listNode.get(i).fisrtStartTime(start, p);
			if(firstTime>tmpTime){
				firstTime=tmpTime;
			}
		}
		return firstTime;
		
	}
	
	public void affecterRessourceOnGrid(Job job){
		int idNode=0;
		int firstTimeMachine=Integer.MAX_VALUE;
		int tmpTime;
		int start=0;
		for(int i=0;i<job.getNbProcessus();i++){
			//trouver le temps plus tot pour executer le processus
			for(int j=0;j<i;j++){
				if(job.adjProcessus[j][i]!=0){
					if(start<job.listProcessus.get(i).getFin()+job.adjProcessus[j][i]){
						start=job.listProcessus.get(i).getFin()+job.adjProcessus[j][i]+1;
					}
				}
			}
			
			//trouver une machine FAM pour executer processus p
			for(int j=0;j<listNode.size();j++){
				tmpTime=listNode.get(j).fisrtStartTime(start, job.listProcessus.get(i));
				if(tmpTime<firstTimeMachine){
					firstTimeMachine=tmpTime;
					idNode=j;
				}				
			}
			//affecter
			
			listNode.get(idNode).affectationRessources(start, job.listProcessus.get(i));
			
		}
		
	}
	
	public int checkCMax(){
		int CMax=0;
		for(int i=0;i<listNode.size();i++){
			if(CMax<listNode.get(i).CMAX()){
				CMax=listNode.get(i).CMAX();
			}
		}
		return CMax;
			
	}

}
