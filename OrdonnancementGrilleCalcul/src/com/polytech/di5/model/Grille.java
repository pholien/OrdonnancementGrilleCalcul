package com.polytech.di5.model;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	private int nbMachine;
	public ArrayList<Node> listNode;

	public Grille(int id, int nbM) {
		this.setIdGrille(id);
		this.setNbMachine(nbM);
		listNode = new ArrayList<Node>();
	}

	public void ajouterNode(Node n) {
		this.listNode.add(n);
	}

	public int firstStartTimeOnGrid(int start, Processus p) {
		int tmpTime;
		int firstTime = Integer.MAX_VALUE;
		for (int i = 0; i < listNode.size(); i++) {
			tmpTime = listNode.get(i).fisrtStartTime(start, p);
			
			if (firstTime > tmpTime) {
				firstTime = tmpTime;
			}
		}
		return firstTime;

	}

	public void affecterRessourceOnGrid(Job job) {
		int idNode = -1;
		int firstTimeMachine = Integer.MAX_VALUE;
		
		int tmpTime;
		int start = 0;
		boolean flag = true;
		for (int i = 0; i < job.getNbProcessus(); i++) {
			// trouver le temps plus tot pour executer le processus
			start = 0;
			flag = true;
			
			int idProcessus = job.listProcessusOrdonne.get(i).getIdProcessus();
			for (int j = 0; j < idProcessus; j++) {
				if (job.adjProcessus[j][idProcessus] != 0) {
					if (flag) {
						start = job.listProcessus.get(idProcessus).getFin() + 1;
						idNode = job.listProcessus.get(j).getIdMachine();
						;
					}
					if (!flag && start < job.listProcessus.get(j).getFin() + job.adjProcessus[j][idProcessus]) {
						start = job.listProcessus.get(j).getFin() + job.adjProcessus[j][idProcessus] + 1;
						idNode = -1;
					}
					flag = false;
				}
			}
			if (idNode != -1) {
				job.listProcessus.get(idProcessus).setIdMachine(idNode);
				listNode.get(idNode).affectationRessources(start, job.listProcessus.get(idProcessus));
			} else {
				idNode=0;
				firstTimeMachine= Integer.MAX_VALUE;
				for (int j = 0; j < listNode.size(); j++) {
					tmpTime = listNode.get(j).fisrtStartTime(start, job.listProcessus.get(idProcessus));
					if (tmpTime < firstTimeMachine) {
						firstTimeMachine = tmpTime;
						idNode = j;
					}
					//System.out.println(tmpTime);
				}
				//System.out.println(" test " + i + " idnode " + idNode + " taille " + listNode.size());
				job.listProcessus.get(idProcessus).setIdMachine(idNode);
				listNode.get(idNode).affectationRessources(start, job.listProcessus.get(idProcessus));
			}

			// trouver une machine FAM pour executer processus p

		}

	}

	public int checkCMax() {
		int CMax = 0;
		for (int i = 0; i < listNode.size(); i++) {
			if (CMax < listNode.get(i).CMAX()) {
				CMax = listNode.get(i).CMAX();
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
