package com.polytech.di.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Job {
	//identité de job
	private int idJob;
	//le job contient combien de map
	private int nbMap;
	//le job contient combien de reduce
	private int nbReduce;
	//liste de map
	public ArrayList<Processus> listeMap;
	//liste de reduce
	public ArrayList<Processus> listeReduce;
	//le job s'exécuter sur quelle grille 
	private int idGrille;
	//le début de job 
	private int debut;

	public Job(int id) {
		/**
		 * constructeur de classe Job, pour initialiser un Job
		 * */
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


}
