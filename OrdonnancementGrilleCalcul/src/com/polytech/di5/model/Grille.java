package com.polytech.di5.model;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	private int nbMachine;
	public ArrayList<Node> listNode;
	
	public Grille(int id, int nbM){
		this.setIdGrille(id);
		this.setNbMachine(nbM);
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
			int idProcessus=job.listProcessusOrdonne.get(i).getIdProcessus();
			for(int j=0;j<i;j++){
				if(job.adjProcessus[j][i]!=0){
					if(start<job.listProcessus.get(idProcessus).getFin()+job.adjProcessus[j][idProcessus]){
						start=job.listProcessus.get(idProcessus).getFin()+job.adjProcessus[j][idProcessus]+1;
					}
				}
			}
			
			//trouver une machine FAM pour executer processus p
			for(int j=0;j<listNode.size();j++){
				tmpTime=listNode.get(j).fisrtStartTime(start, job.listProcessus.get(idProcessus));
				if(tmpTime<firstTimeMachine){
					firstTimeMachine=tmpTime;
					idNode=j;
				}				
			}
			//si les procedent de job i est 1 processus, de plus, ils s'executent sur meme machine
			/*int nbProc=0;
			int tmpIdNode=0;
			int dureePrec=0;
			for(int j=0;j<job.getNbProcessus();j++){
				if(job.adjProcessus[j][i]!=0){
					nbProc++;
					tmpIdNode=job.listProcessus.get(j).getIdMachine();
					dureePrec=job.listProcessus.get(j).getDuree();
				}
			}
			if(nbProc==1 && tmpIdNode==idNode){
				start-=dureePrec;
			}*/
			//affecter
			job.listProcessus.get(idProcessus).setIdMachine(idNode);
			listNode.get(idNode).affectationRessources(start, job.listProcessus.get(idProcessus));
			
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

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public int getNbMachine() {
		return nbMachine;
	}

	public void setNbMachine(int nbMachine) {
		this.nbMachine = nbMachine;
	}

}
