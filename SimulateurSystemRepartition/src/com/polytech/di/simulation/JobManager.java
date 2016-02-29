package com.polytech.di.simulation;


import java.util.LinkedList;
import java.util.Queue;

import com.polytech.di.modele.Job;
import com.polytech.di.modele.Processus;

public class JobManager {
	public Queue<Job> queueJob;
	//public Queue<Integer> queue;
	public JobManager() {
		queueJob = new LinkedList<Job>();
		
	}

	public void GenererJob(int n) {

		for (int i = 0; i < n; i++) {
			Job job = new Job(i);
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
			queueJob.add(job);
		}
		

	}
	


}
