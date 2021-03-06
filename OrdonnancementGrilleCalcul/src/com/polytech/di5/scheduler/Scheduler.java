package com.polytech.di5.scheduler;

public class Scheduler {

	public static void main(String[] args) {
		AM appManager = new AM();
		RM ressourceManager = new RM();
		//generer les jobs
		appManager.GenererJob(5);
		//pour chaque job, Nous sort les Processus par le valeur rang
		appManager.SortJobOnRang();

		ressourceManager.AddGrille(3);

		for (int i = 0; i < appManager.listJob.size(); i++) {
			int idGrille = 0;
			// chercher un grille pour executer le job
			idGrille = ressourceManager.ordonnacerUnJob(appManager.listJob.get(i));
			//affecter les ressources
			ressourceManager.affecterUnJob(idGrille, appManager.listJob.get(i));
		}
		
		System.out.println(ressourceManager.checkCMAXOnGrid());

	}

}
