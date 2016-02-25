package com.polytech.di5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Job {
	private int idJob;
	private int nbProcessus;
	public ArrayList<Processus> listProcessus;
	public ArrayList<Processus> listProcessusOrdonne;
	public int[][] adjProcessus;
	private boolean memoryDistribued;
	private int finJob;
	
	public Job(int id,boolean type,int nb){
		this.idJob=id;
		this.setNbProcessus(nb);
		this.memoryDistribued=type;
		listProcessus=new ArrayList<Processus>();
		listProcessusOrdonne=new ArrayList<Processus>();
		adjProcessus=new int[nb][nb];
		for(int i=0;i<nb;i++){
			for(int j=0;j<nb;j++){
				adjProcessus[i][j]=0;
			}
		}
		
	}
	
	public void sortListeProcessus(){
		
		for(int i=0;i<nbProcessus;i++){
			Processus p=new Processus(listProcessus.get(i));
			listProcessusOrdonne.add(p);
		}
		
		Comparator<Processus> comparator = new Comparator<Processus>(){  
			   public int compare(Processus s1, Processus s2) {  
			    //sort par valeur rang
			    if(s1.getValueRang()!=s2.getValueRang()){  
			     return s2.getValueRang()-s1.getValueRang();  
			    }  
			    else{  
			     //les valeurs rang sont paraill, sort part spt
			     if(s1.getDuree()!=s2.getDuree()){  
			      return s1.getDuree()-s2.getDuree();  
			     }  
			     else{  
			       //sort par id;
			      return s1.getIdProcessus()-s2.getIdProcessus();  
			     }  
			    }  
			   }  
			  };  
		Collections.sort(this.listProcessusOrdonne, comparator);
	}
	
	public int getIdJob() {
		return idJob;
	}
	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}
	public boolean isMemoryDistribued() {
		return memoryDistribued;
	}
	public void setMemoryDistribued(boolean memoryDistribued) {
		this.memoryDistribued = memoryDistribued;
	}
	public void addProcessus(Processus p){
		listProcessus.add(p);
	}

	public int getNbProcessus() {
		return nbProcessus;
	}

	public void setNbProcessus(int nbProcessus) {
		this.nbProcessus = nbProcessus;
	}

	public int getFinJob() {
		return finJob;
	}

	public void setFinJob(int finJob) {
		this.finJob = finJob;
	}
	
	class SortbyRang implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			Processus p1=(Processus)o1;
			Processus p2=(Processus)o2;
			int idP1,idP2;
			int rangP1,RangP2;
			for(int i=0;i<listProcessus.size();i++){
				if(p1.getIdProcessus()==listProcessus.get(i).getIdProcessus()){
					idP1=i;
				}
				if(p2.getIdProcessus()==listProcessus.get(i).getIdProcessus()){
					idP2=i;
				}
			}
			
			
			return 0;
		}
	}
}
