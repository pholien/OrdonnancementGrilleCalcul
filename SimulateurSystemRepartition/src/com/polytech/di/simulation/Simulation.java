package com.polytech.di.simulation;

import com.polytech.di.modele.Processus;

public class Simulation {
	/*private JobManager jobManager;
	private RessourceManager ressourceManager;

	public Simulation() {
		jobManager = new JobManager();
		ressourceManager = new RessourceManager();
	}*/

	/*public void Ordonnacement() {
		int nbJob = 10;
		int nbMachine = 4;
		jobManager.GenererJob(nbJob);
		System.out.println(jobManager.listJob.size());
		for (int i = 0; i < nbJob; i++) {
			System.out.println("nombre de map est " + jobManager.listJob.get(i).getNbMap());
			System.out.println("nombre de reduce est " + jobManager.listJob.get(i).getNbReduce());

		}
		ressourceManager.AjouterMachine(nbMachine);
		System.out.println(ressourceManager.listMachine.size());
		for (int i = 0; i < nbMachine; i++) {
			System.out.println("nombre de map est " + ressourceManager.listMachine.get(i).toString());

		}

		for (int i = 0; i < nbJob; i++) {
			// Nous allons la liste de Jobs, traiter par FIFO
			jobManager.listJob.get(i).SortBySPT();
			for (int j = 0; j < jobManager.listJob.get(i).listeMap.size(); j++) {
				// chaque Job a certain de processus.
				Processus p = jobManager.listJob.get(i).listeMap.get(j);
				int idMachine = ressourceManager.FirstStartTimeMachine(0,p);
				System.out.println(jobManager.listJob.get(i).getIdJob() + " " + p.getId() + " est deplace sur Machine "
						+ " " + idMachine);
				ressourceManager.affectation(0,p, ressourceManager.listMachine.get(idMachine));

			}
			int finMap = 0; // la fin d'executer les Maps

			for (int j = 0; j < nbMachine; j++) {
				int tmpFinMap = ressourceManager.listMachine.get(j).CPURest.size();
				if (tmpFinMap > finMap) {
					finMap = tmpFinMap;
				}
			}
			for (int j = 0; j < jobManager.listJob.get(i).listeReduce.size(); j++) {
				// chaque Job a certain de processus.
				Processus p = jobManager.listJob.get(i).listeReduce.get(j);
				int idMachine = ressourceManager.FirstStartTimeMachine(0,p);
				System.out.println(jobManager.listJob.get(i).getIdJob() + " " + p.getId() + " est deplace sur Machine "
						+ " " + idMachine);
				ressourceManager.affectation(finMap+1,p, ressourceManager.listMachine.get(idMachine));

			}
			
			
			
		}
		int Cmax = 0;

		for (int i = 0; i < nbMachine; i++) {
			int tmpCmax = ressourceManager.listMachine.get(i).CPURest.size();
			if (tmpCmax > Cmax) {
				Cmax = tmpCmax;
			}

		}
		System.out.println(Cmax);

	}*/

	public static void main(String[] args) {
		/**
		 * Dans le fonction Main, Nous definissons AM et RM, les deux gardent une file d'attent d'evenement,
		 * nous definissons certain nombre de evenement;
		 * 1 represente l'evenement job arrive
		 * 2 represente choisir un job 
		 * 3 represente decider une grille pour executer job
		 * 4 represente affecter une grille pour executer job
		 * 5 represente affecter les machines pour executer job dans la grille
		 * 6 represente liberte les ressources 
		 * */
		JobManager jobManager=new JobManager();
		RessourceManager ressourceManager=new RessourceManager();
		
		jobManager.GenererJob(100);
		ressourceManager.AjouterGrille(3);
		
		for (int i = 0; i < jobManager.listJob.size(); i++) {
			int idGrille = 0;
			// chercher un grille pour executer le job
			idGrille = ressourceManager.ordonnacerUnJob(jobManager.listJob.get(i));
			jobManager.listJob.get(i).setIdGrille(idGrille);
			//affecter les ressources
			ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
		}
		System.out.println(ressourceManager.checkCMAXOnGrid());
	}

}
