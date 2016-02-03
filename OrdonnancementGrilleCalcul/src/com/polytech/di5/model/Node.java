package com.polytech.di5.model;

import java.util.ArrayList;

public class Node {
	private int idNode;
	private int nbCPU;
	private int nbRAM;
	
	public ArrayList<Integer> CPURest;
	public ArrayList<Integer> RAMRest;
	
	public Node(int id,int nomberCPU,int nomberRAM){
		this.idNode=id;
		this.nbCPU=nomberCPU;
		this.nbRAM=nomberRAM;
		
		CPURest=new ArrayList<Integer>();
		RAMRest=new ArrayList<Integer>();
	}
	
	

}
