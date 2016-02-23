package com.polytech.di5.scheduler;

public class Scheduler {

	public static void main(String[] args) {
		AM appManager=new AM();
		RM ressourceManager=new RM();
		appManager.GenererJob(5);
		
		ressourceManager.AddGrille(3);

	}

}
