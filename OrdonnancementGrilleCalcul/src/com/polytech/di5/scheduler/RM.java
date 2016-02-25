package com.polytech.di5.scheduler;

import java.util.ArrayList;

import com.polytech.di5.model.Grille;
import com.polytech.di5.model.Job;
import com.polytech.di5.model.Node;

public class RM {
	public ArrayList<Grille> listGrille;
	
	public RM(){
		listGrille=new ArrayList<Grille>();
		
	}
	
	
	
	public void AddGrille(int nbGrille){
		for(int i=0;i<nbGrille;i++){
			//Pour chaque grille, on genere 5_10 machines
			int nbMachine=(int) Math.round(Math.random() * 5 + 5);
			Grille grille=new Grille(i,nbMachine);
			for(int j=0;j<nbMachine;j++){
				//nombre de CPU est 2-8
				int c=(int) Math.round(Math.random()*6+2);
				//nombre de RAM est 2G-16G
				int r=(int) Math.round(Math.random()*14+2);
				
				Node node=new Node(j,c,r);
				grille.ajouterNode(node);
			}
			this.listGrille.add(grille);
			
		}
	}
	/**
	 * chercher un grille pour executer le job selon FAM
	 * */
	public int ordonnacerUnJob(Job job){
		int firstTimeGrille=Integer.MAX_VALUE;
		int idGrille=0;
		int tmpFirstTime=0;
		for(int i=0;i<listGrille.size();i++){
			tmpFirstTime=listGrille.get(i).firstStartTimeOnGrid(0, job.listProcessusOrdonne.get(0));
			if(tmpFirstTime<firstTimeGrille){
				firstTimeGrille=tmpFirstTime;
				idGrille=i;
			}
		}
		return idGrille;
	}
	public void affecterUnJob(int idGrille, Job job) {
		
		
		listGrille.get(idGrille).affecterRessourceOnGrid(job);;
		
	}
	
	public int checkCMAXOnGrid(){
		int CMax=0;
		for(int i=0;i<listGrille.size();i++){
			if(CMax<listGrille.get(i).checkCMax()){
				CMax=listGrille.get(i).checkCMax();
			}
		}
		return CMax;
			
	}

}
