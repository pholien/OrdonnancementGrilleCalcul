package com.polytech.di.simulation;


import java.util.ArrayList;


import com.polytech.di.modele.Job;
import com.polytech.di.modele.Processus;

public class JobManager {
	public ArrayList<Job> listJob;
	//public Queue<Integer> queue;
	public JobManager() {
		listJob = new ArrayList<Job>();
		
	}

	public void GenererJob(int n) {
		/**
		 * Entree: le nombre de job
		 * generer les processus (map et reduce) pour chaque job, chaque job a besoin de ressources,
		 * chaque machine a 1-4 CPU, 1-4G RAM, et 2-5G disque dur
		 * */

		for (int i = 0; i < n; i++) {
			Job job = new Job(i);
			job.setDebut((int) Math.round(Math.random() * 200));
			// pour chaque Job, generer 3-5 Map et 1-2 reduce
			int nbMap = (int) Math.round(Math.random() * 2 + 3);
			for (int j = 0; j < nbMap; j++) {
				// le nombre de cpu: 1-4
				int nbCPU = (int) Math.round(Math.random() * 3 + 1);
				// Le RAM: 1-4G
				int nbRAM = (int) Math.round(Math.random() * 3 + 1);
				//le disque dur 2-50G
				int nbDD = (int) Math.round(Math.random() * 48 + 2);
				//le duree 1-5
				int duree = (int) Math.round(Math.random() * 2 + 3);
				Processus processus=new Processus(j, nbCPU, nbRAM, nbDD, true, duree);
				job.addMap(processus);
			}
			
			//generer le reduce
			int nbReduce=(int) Math.round(Math.random() * 1 + 1);
			for (int j = 0; j < nbReduce; j++) {
				// le nombre de cpu: 1-4
				int nbCPU = (int) Math.round(Math.random() * 3 + 1);
				// Le RAM: 1-4G
				int nbRAM = (int) Math.round(Math.random() * 3 + 1);
				//le disque dur 2-50G
				int nbDD = (int) Math.round(Math.random() * 48 + 2);
				//le duree 1-5
				int duree = (int) Math.round(Math.random() * 2 + 3);
				Processus processus=new Processus(nbMap+j, nbCPU, nbRAM, nbDD, false, duree);
				job.addReduce(processus);
			}
			//ajouter des nombre de map et reduce to job
			job.setNbMap(nbMap);
			job.setNbReduce(nbReduce);
			//ajouter le job dans la liste 
			listJob.add(job);
		}
		

	}
	


}
