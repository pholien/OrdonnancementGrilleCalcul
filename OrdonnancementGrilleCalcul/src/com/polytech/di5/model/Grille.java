package com.polytech.di5.model;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	private int nbMachine;
	public ArrayList<Node> listNode;
	
	public Grille(int id, int nbM){
		this.idGrille=id;
		this.nbMachine=nbM;
		listNode=new ArrayList<Node>();
	}
	
	public void ajouterNode(Node n){
		this.listNode.add(n);
	}
	

}
