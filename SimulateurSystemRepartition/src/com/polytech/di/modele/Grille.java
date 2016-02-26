package com.polytech.di.modele;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	private int nbMachine;
	public ArrayList<Machine> listMachine;

	public Grille(int id, int nbM) {
		this.setIdGrille(id);
		this.setNbMachine(nbM);
		listMachine = new ArrayList<Machine>();
	}

	public void GenererMachine(int n) {

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
	}

	public void ajouterMachine(Machine n) {
		this.listMachine.add(n);
	}

	public int firstStartTimeOnGrid(int start, Processus p) {
		/**
		 * parametre start est le temps plus tot pour executer le processus p, Nous trouvons la machine qui peut executer 
		 * le processus p le plus tot.
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

	// /*
	// * public void affecterRessourceOnGrid(Job job) { int idNode = -1; int
	// * firstTimeMachine = Integer.MAX_VALUE;
	// *
	// * int tmpTime; int start = 0; boolean flag = true; for (int i = 0; i <
	// * job.getNbProcessus(); i++) { // trouver le temps plus tot pour executer
	// * le processus start = 0; flag = true;
	// *
	// * int idProcessus = job.listProcessusOrdonne.get(i).getIdProcessus(); for
	// * (int j = 0; j < idProcessus; j++) { if
	// (job.adjProcessus[j][idProcessus]
	// * != 0) { if (flag) { start = job.listProcessus.get(idProcessus).getFin()
	// +
	// * 1; idNode = job.listProcessus.get(j).getIdMachine(); ; } if (!flag &&
	// * start < job.listProcessus.get(j).getFin() +
	// * job.adjProcessus[j][idProcessus]) { start =
	// * job.listProcessus.get(j).getFin() + job.adjProcessus[j][idProcessus] +
	// 1;
	// * idNode = -1; } flag = false; } } if (idNode != -1) {
	// * job.listProcessus.get(idProcessus).setIdMachine(idNode);
	// * listNode.get(idNode).affectationRessources(start,
	// * job.listProcessus.get(idProcessus)); } else { idNode=0;
	// firstTimeMachine=
	// * Integer.MAX_VALUE; for (int j = 0; j < listNode.size(); j++) { tmpTime
	// =
	// * listNode.get(j).fisrtStartTime(start,
	// * job.listProcessus.get(idProcessus)); if (tmpTime < firstTimeMachine) {
	// * firstTimeMachine = tmpTime; idNode = j; }
	// //System.out.println(tmpTime);
	// * } //System.out.println(" test " + i + " idnode " + idNode + " taille "
	// +
	// * listNode.size());
	// * job.listProcessus.get(idProcessus).setIdMachine(idNode);
	// * listNode.get(idNode).affectationRessources(start,
	// * job.listProcessus.get(idProcessus)); }
	// *
	// * // trouver une machine FAM pour executer processus p
	// *
	// * }
	// *
	// * }
	// *
	// * public int checkCMax() { int CMax = 0; for (int i = 0; i <
	// * listNode.size(); i++) { if (CMax < listNode.get(i).CMAX()) { CMax =
	// * listNode.get(i).CMAX(); } } return CMax;
	// *
	// * }
	// */

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
