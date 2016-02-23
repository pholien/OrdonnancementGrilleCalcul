package com.polytech.di5.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.polytech.di5.model.Job;
import com.polytech.di5.model.Processus;

public class AM {
	public ArrayList<Job> listJob;

	public AM() {
		listJob = new ArrayList<Job>();
	}

	public void GenererJob(int nbJob) {

		for (int i = 0; i < nbJob; i++) {
			int nbProcessus =  (int) Math.round(Math.random() * 30 + 10);
			Job job = new Job(i, true, nbProcessus);
			/**
			 * Generer les Processus chaue processus a besoin des ressoures CPU
			 * 1-4 RAM 1-4G
			 * */
			for (int j = 0; j < nbProcessus; j++) {
				// le nombre de cpu: 1-3
				int nbCPU = (int) Math.round(Math.random() * 2 + 1);
				// Le RAM: 1-4G
				int nbRAM = (int) Math.round(Math.random() * 3 + 1);
				// le duree 5-15
				int duree = (int) Math.round(Math.random() * 10 + 5);
				Processus processus = new Processus(j, nbCPU, nbRAM, duree);
				job.addProcessus(processus);
			}
			/**
			 * Generer le graph DAG
			 *
			 * 
			 * */
			int nbProcessusNonTraite = nbProcessus - 2;
			Queue<Integer> queueDetecting = new LinkedList<Integer>();
			queueDetecting.add(0);
			ArrayList<Integer> tmpList = new ArrayList<Integer>();

			while (!queueDetecting.isEmpty()) {
				tmpList.clear();

				while (!queueDetecting.isEmpty()) {
					tmpList.add(queueDetecting.poll());
				}

				int nbAdj = (int) Math.round(Math.random() * 3 + 2);
				if (nbAdj >= nbProcessusNonTraite)
				{
					nbAdj = nbProcessusNonTraite;
				}else{
					for (int j = 0; j < nbAdj; j++) {
						queueDetecting.add(tmpList.get(tmpList.size() - 1) + j + 1);
					}
				}
					
				nbProcessusNonTraite -= nbAdj;

				int nbMaxLayer = nbAdj;
				if (tmpList.size() > nbAdj) {
					nbMaxLayer = tmpList.size();
				}
				

				for (int j = 0; j < nbMaxLayer; j++) {
					int nodeSuccTmp = tmpList.get(tmpList.size() - 1) + 1;
					job.adjProcessus[tmpList.get(j % tmpList.size())][nodeSuccTmp
							+ j % nbAdj] = (int) Math
							.round(Math.random() * 5 + 5);
				}
				for (int j = 0; j < tmpList.size(); j++) {
					int nbAdjSucc = (int) Math.round(Math.random()
							* (nbAdj - 1) + 1);
					for (int k = 0; k < nbAdjSucc; k++) {
						int nodeSuccTmp = (int) Math.round(Math.random()
								* (nbAdj - 1) + tmpList.get(tmpList.size() - 1)
								+ 1);
						job.adjProcessus[tmpList.get(j)][nodeSuccTmp] = (int) Math
								.round(Math.random() * 5 + 5);
					}
				}

			}
			
			for (int j = tmpList.get(tmpList.size() - 1)+1; j < nbProcessus-1; j++) {
				job.adjProcessus[j][nbProcessus - 1] = (int) Math
						.round(Math.random() * 5 + 5);
			}

			this.listJob.add(job);

			for (int j = 0; j < nbProcessus; j++) {

				for (int k = 0; k < nbProcessus; k++) {
					System.out.print(job.adjProcessus[j][k] + " ");
				}
				System.out.println(" ");
			}

		}
	}

}
