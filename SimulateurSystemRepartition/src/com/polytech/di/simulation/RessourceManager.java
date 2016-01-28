package com.polytech.di.simulation;

import java.util.ArrayList;

import javax.swing.JSpinner.ListEditor;

import com.polytech.di.modele.Machine;
import com.polytech.di.modele.Processus;

public class RessourceManager {
	public ArrayList<Machine> listMachine;
	
	
	public RessourceManager(){
		listMachine=new ArrayList<Machine>();
		
	}
	public void AjouterMachine(int n){
		
		for(int i=0;i<n;i++){
			//nombre de CPU est 2-8
			int c=(int) Math.round(Math.random()*6+2);
			//nombre de RAM est 2G-16G
			int r=(int) Math.round(Math.random()*14+2);
			//disque dur est 500-1000G
			int d=(int) Math.round(Math.random()*500+500);

			Machine machine=new Machine(i, c, r, d);
			listMachine.add(machine);
		}
	}
	
	public int FirstStartTimeMachine(int start,Processus p){
		int fstm=listMachine.get(0).fisrtStartTime(start,p);
		int machineId=0;
		for(Machine machine: listMachine){
			if(machine.fisrtStartTime(start,p)<fstm)
				machineId=machine.getIdMachine();
		}
		return machineId;
	}
	
	public void affectation(int start,Processus p, Machine machine){
		machine.affectationRessources(start,p);
		
	}

}
