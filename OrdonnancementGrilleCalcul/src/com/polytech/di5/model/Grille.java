package com.polytech.di5.model;

import java.util.ArrayList;

public class Grille {
	private int idGrille;
	public ArrayList<Node> listNode;
	public Grille(int id){
		this.idGrille=id;
		listNode=new ArrayList<Node>();
	}
	
	public void ajouterNode(Node n){
		this.listNode.add(n);
	}

}
