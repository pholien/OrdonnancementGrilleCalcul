package com.polytech.di.modele;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Grille {
	//identité de la grille
	private int idGrille;
	//nombre de machine dans la grille
	private int nbMachine;
	//la liste de machine 
	public ArrayList<Machine> listMachine;

	public Grille(int id, int nbM) {
		/**
		 * constructeur de la classe Grille, pour initialiser un Grille
		 * */
		this.setIdGrille(id);
		this.setNbMachine(nbM);
		listMachine = new ArrayList<Machine>();
	}

/*	public void GenererMachine(int n) {

		for (int i = 0; i < n; i++) {
			// nombre de CPU est 2-8
			int c = (int) Math.round(Math.random() * 6 + 2);
			// nombre de RAM est 2G-16G
			int r = (int) Math.round(Math.random() * 14 + 2);
			// disque dur est 500-1000G
			int d = (int) Math.round(Math.random() * 500 + 500);

			Machine machine = new Machine(i, c, r, d);
			this.listMachine.add(machine);
		}
	}*/

	public void ajouterMachine(Machine n) {
		this.listMachine.add(n);
	}

	public int firstStartTimeOnGrid(int start, Processus p) {
		/**
		 * Entrée: le temps arrivé de job et un processus 
		 * sortie: le début exécuté sur cette grille
		 * calculer le temps plus tôt à exécuter le P
		 * */
		int tmpTime;
		int firstTime = Integer.MAX_VALUE;
		for (int i = 0; i < listMachine.size(); i++) {
			tmpTime = listMachine.get(i).fisrtStartTime(start, p);

			if (firstTime > tmpTime) {
				firstTime = tmpTime;
			}
		}
		return firstTime;

	}
	
	public double calculCharge(int start){
		/**
		 * Entrée: le temps arrivé de job
		 * sortie: le charge de la grille
		 * calculer le charge dans le moment de job arrivé.
		 * */
		int nbProcesseur=0;
		int nbProcesseurOccup=0;
		
		for(int i=0;i<listMachine.size();i++){
			if(listMachine.get(i).CPURest.size()<=start){
				nbProcesseur+=listMachine.get(i).getNbCPU();
			}else{
				nbProcesseurOccup+=listMachine.get(i).CPURest.get(start);
				nbProcesseur+=listMachine.get(i).getNbCPU();
			}
			
		}
		return (double)nbProcesseurOccup/nbProcesseur;
	}
	
	public double calculChargeMoyen(int start,Processus p){
		/**
		 * Entrée: le temps arrivé de job et le premier processus de job
		 * sortie: le charge moyen de la grille
		 * calculer le charge moyen pendant la durée de premier processus 
		 * */
		int nbProcesseur=0;
		int nbProcesseurOccup=0;
		int nbProcesseurOccupTmp;
		
		for(int i=0;i<listMachine.size();i++){			
			nbProcesseurOccupTmp=0;
			for(int j=0;j<p.getDuree();j++){
				if(listMachine.get(i).CPURest.size()>start+j){
					nbProcesseurOccupTmp+=listMachine.get(i).CPURest.get(start+j);
				}
				
			}
			nbProcesseurOccup+=nbProcesseurOccupTmp/p.getDuree();			
			nbProcesseur+=listMachine.get(i).getNbCPU();
		}
		
		return (double)nbProcesseurOccup/nbProcesseur;
	}
	
	
	public int nbJobExecuteOnGrille(int start){
		/**
		 * Entrée: le temps arrivé de job
		 * sortie: le nombre de job
		 * calculer le nombre de job dans le moment de job arrivé.
		 * */
		int nbJob=0;
		for(int i=0;i<listMachine.size();i++){
			if(listMachine.get(i).nbJob.size()>start)
				nbJob+=listMachine.get(i).nbJob.get(start);
		}
		return nbJob;
	}
	
	public double nbJobMoyenExecuteOnGrille(int start, Processus p){
		/**
		 * Entrée: le temps arrivé de job et le premier processus de job
		 * sortie: le nombre de job moyen
		 * calculer nombrede job moyen pendant la durée de premier processus 
		 * */
		double nbJob=0;
		double nbJobMoyen=0;
		for(int i=0;i<listMachine.size();i++){
			nbJob=0;
			for(int j=0;j<p.getDuree();j++){
				if(listMachine.get(i).nbJob.size()>start+j)
					nbJob+=listMachine.get(i).nbJob.get(start+j);
			}
			nbJobMoyen+=nbJob/p.getDuree();
		}
		return nbJobMoyen;
	}
	
	

	public void affecterRessourceOnGrid(Job job) {
		/**
		 * job a deux types de processus,map et reduce, Les processus reduce s'executer apres les processus map.
		 * D'abord, affecter les ressource pour les processus maps, ensuite les processus reduces.
		 * */
		int idMachine = 0;
		int firstTimeMachine = Integer.MAX_VALUE;
		int tmpTime = 0;
		int start = 0;
		//affecter les maps sur les machines
		for (int i = 0; i < job.listeMap.size(); i++) {

			firstTimeMachine = Integer.MAX_VALUE;
			for (int j = 0; j < listMachine.size(); j++) {
				tmpTime = listMachine.get(j).fisrtStartTime(start, job.listeMap.get(i));
				if (tmpTime < firstTimeMachine) {
					firstTimeMachine = tmpTime;
					idMachine = j;
				}
				// System.out.println(tmpTime);
			}

			job.listeMap.get(i).setIdMachine(idMachine);
			listMachine.get(idMachine).affectationRessources(start, job.listeMap.get(i));

		}
		//calculer le debut de Processus reduce
		for(int j=0;j<job.listeMap.size();j++){
			if(start<job.listeMap.get(j).getFinProcessus()){
				start=job.listeMap.get(j).getFinProcessus();
			}
		}
		//affecter les reduce sur les machines
		
		for (int i = 0; i < job.listeReduce.size(); i++) {
		
			firstTimeMachine = Integer.MAX_VALUE;
			for (int j = 0; j < listMachine.size(); j++) {
				tmpTime = listMachine.get(j).fisrtStartTime(start, job.listeReduce.get(i));
				if (tmpTime < firstTimeMachine) {
					firstTimeMachine = tmpTime;
					idMachine = j;
				}
				// System.out.println(tmpTime);
			}

			job.listeReduce.get(i).setIdMachine(idMachine);
			listMachine.get(idMachine).affectationRessources(start, job.listeReduce.get(i));

		}
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
	
	public int checkCMax() {
		int CMax = 0;
		for (int i = 0; i < listMachine.size(); i++) {
			if (CMax < listMachine.get(i).CMAX()) {
				CMax = listMachine.get(i).CMAX();
			}
		}
		return CMax;

	}

}
