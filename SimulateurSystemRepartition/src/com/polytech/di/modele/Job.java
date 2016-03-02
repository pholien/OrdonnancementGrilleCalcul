package com.polytech.di.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Job {
	private int idJob;
	private int nbMap;
	private int nbReduce;
	public ArrayList<Processus> listeMap;
	public ArrayList<Processus> listeReduce;
	private int idGrille;
	private int debut;

	public Job(int id) {
		this.idJob = id;
		listeMap = new ArrayList<Processus>();
		listeReduce=new ArrayList<Processus>();
	}

	public void addMap(Processus e) {
		listeMap.add(e);
	}
	public void addReduce(Processus e) {
		listeMap.add(e);
	}

	public int getIdJob() {
		return idJob;
	}

	public int getNbMap() {
		return nbMap;
	}

	public void setNbMap(int nbMap) {
		this.nbMap = nbMap;
	}

	public int getNbReduce() {
		return nbReduce;
	}

	public void setNbReduce(int nbReduce) {
		this.nbReduce = nbReduce;
	}
	public void SortBySPT(){
		Collections.sort(listeMap,new SPT());
		Collections.sort(listeReduce,new SPT());
	}
	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}
	class SPT implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			Processus p1=(Processus)o1;
			Processus p2=(Processus)o2;
			if(p1.getDuree()>p2.getDuree())
				return 1;
			else
				return 0;
			
		}
		
	}

}
