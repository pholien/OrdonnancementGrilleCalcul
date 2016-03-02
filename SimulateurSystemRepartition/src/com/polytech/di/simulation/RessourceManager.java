package com.polytech.di.simulation;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.polytech.di.modele.Grille;
import com.polytech.di.modele.Job;
import com.polytech.di.modele.Machine;

public class RessourceManager {
	public ArrayList<Grille> listGrille;
	//private BlockingQueue<Integer> queue; //file d'attent d'evenement
	private boolean isRunning =true;
	public RessourceManager() {
		listGrille = new ArrayList<Grille>();
		

	}

	public void AjouterGrille(int nbGrille) {
		for (int i = 0; i < nbGrille; i++) {
			// Pour chaque grille, on genere 5_10 machines
			int nbMachine = (int) Math.round(Math.random() * 5 + 5);
			Grille grille = new Grille(i, nbMachine);

			for (int j = 0; j < nbMachine; j++) {
				// nombre de CPU est 2-8
				int c = (int) Math.round(Math.random() * 6 + 2);
				// nombre de RAM est 2G-16G
				int r = (int) Math.round(Math.random() * 14 + 2);
				// disque dur est 500-1000G
				int d = (int) Math.round(Math.random() * 500 + 500);

				Machine machine = new Machine(i, c, r, d);
				grille.listMachine.add(machine);
			}
			this.listGrille.add(grille);
		}
	}

	public int ordonnacerUnJob(Job job) {
		/**
		 * Entree: un job
		 * on retourne le numero de la machine qui peut demarrer le job le plus tot
		 * 
		 * */
		int idGrille = 0;
		int firstTimeGrille = Integer.MAX_VALUE;
		int tmpFirstTime = 0;
		for (int i = 0; i < listGrille.size(); i++) {
			tmpFirstTime = listGrille.get(i).firstStartTimeOnGrid(job.getDebut(), job.listeMap.get(0));
			if (tmpFirstTime < firstTimeGrille) {
				firstTimeGrille = tmpFirstTime;
				idGrille = i;
			}
		}
		return idGrille;

	}
	
	public int ordonnancerUnJob_2(Job job){
		/**
		 * Entree: un job
		 * on retourne le numero de la machine qui peut a la moins charge
		 * 
		 * */
		
		int idGrille=0;
		double tauxDeCharge=listGrille.get(0).calculCharge(job.getDebut());
		for(int i=1;i<listGrille.size();i++){
			if(tauxDeCharge<listGrille.get(i).calculCharge(job.getDebut())){
				tauxDeCharge=listGrille.get(i).calculCharge(job.getDebut());
				idGrille=i;
			}
		}
		return idGrille;
	}
	
	public int ordonnancerUnJob_3(Job job){
		/**
		 * Entree: un job
		 * on retourne le numero de la machine qui peut a la moins charge sur le debut de tache
		 * 
		 * */
		int idGrille=0;
		double tauxDeCharge=listGrille.get(0).calculChargeMoyen(job.getDebut(),job.listeMap.get(0));
		for(int i=1;i<listGrille.size();i++){
			if(tauxDeCharge<listGrille.get(i).calculChargeMoyen(job.getDebut(),job.listeMap.get(0))){
				tauxDeCharge=listGrille.get(i).calculChargeMoyen(job.getDebut(),job.listeMap.get(0));
				idGrille=i;
			}
		}
		return idGrille;
		
	}

	public void affecterUnJob(int idGrille, Job job) {
		listGrille.get(idGrille).affecterRessourceOnGrid(job);		
	}

	/*
	 * public int FirstStartTimeMachine(int start,Processus p){ int
	 * fstm=listMachine.get(0).fisrtStartTime(start,p); int machineId=0;
	 * for(Machine machine: listMachine){
	 * if(machine.fisrtStartTime(start,p)<fstm)
	 * machineId=machine.getIdMachine(); } return machineId; }
	 * 
	 * public void affectation(int start,Processus p, Machine machine){
	 * machine.affectationRessources(start,p);
	 * 
	 * }
	 */
	public int checkCMAXOnGrid(){
		int CMax=0;
		for(int i=0;i<listGrille.size();i++){
			if(CMax<listGrille.get(i).checkCMax()){
				CMax=listGrille.get(i).checkCMax();
			}
		}
		return CMax;
			
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}


	
	

}
