package com.polytech.di5.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.polytech.di5.model.Job;
import com.polytech.di5.model.Processus;


public class AM {
	public ArrayList<Job> listJob;
	public AM(){
		listJob=new ArrayList<Job>();
	}
	
	
	public void GenererJob(int nbJob){
		
		for(int i=0;i<nbJob;i++){
			int nbProcessus=10;//(int) Math.round(Math.random() * 30 + 10);
			Job job=new Job(i, true, nbProcessus);
			/**
			 * Generer les Processus
			 * chaue processus a besoin des ressoures
			 * CPU 1-4
			 * RAM 1-4G
			 * */
			for (int j = 0; j < nbProcessus; j++) {
				// le nombre de cpu: 1-3
				int nbCPU = (int) Math.round(Math.random() * 2 + 1);
				// Le RAM: 1-4G
				int nbRAM = (int) Math.round(Math.random() * 3 + 1);
				//le duree 3-10
				int duree = (int) Math.round(Math.random() * 7 + 3);
				Processus processus=new Processus(j, nbCPU, nbRAM, duree);
				job.addProcessus(processus);
			}
			/**
			 * Generer le graph DAG
			 *
			 * 
			 * */
			Queue<Integer> queueProcessus = new LinkedList<Integer>();
			for(int j=1;j<nbProcessus;j++){
				queueProcessus.add(j);
			}
			Queue<Integer> queueDetected = new LinkedList<Integer>();
			
			Queue<Integer> queueDetecting = new LinkedList<Integer>();
			queueDetecting.add(0);
			while(!queueDetecting.isEmpty()){
				int tmp=queueDetecting.poll();
				int nbAdj=(int) Math.round(Math.random() * 4 + 1);
				if(nbAdj>queueProcessus.size())
					nbAdj=queueProcessus.size();
				for(int k=0;k<nbAdj;k++){
					int tmpPro=queueProcessus.poll();
					job.adjProcessus[tmp][tmpPro]=(int) Math.round(Math.random() * 15 + 5);
					queueDetecting.add(tmpPro);
				}
			}
			
			
		}
	}

}
