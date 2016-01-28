package com.polytech.di.simulation;

import com.polytech.di.modele.Processus;

public class Simulation {
	private JobManager jobManager;
	private RessourceManager ressourceManager;

	public Simulation() {
		jobManager = new JobManager();
		ressourceManager = new RessourceManager();
	}

	public void Ordonnacement() {
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

	}

	public static void main(String[] args) {
		// JobManager.GenererJob(10);
		Simulation simulation = new Simulation();
		simulation.Ordonnacement();

	}

}
