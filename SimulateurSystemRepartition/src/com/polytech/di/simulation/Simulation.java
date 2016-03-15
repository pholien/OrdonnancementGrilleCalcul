package com.polytech.di.simulation;

public class Simulation {
	public static void main(String[] args) {
		/**
		 * Dans le fonction Main, Nous definissons AM et RM, les deux gardent une file d'attent d'evenement,
		 * nous definissons certain nombre de evenement;
		 * 1 represente l'evenement job arrive
		 * 2 represente choisir un job 
		 * 3 represente decider une grille pour executer job
		 * 4 represente affecter une grille pour executer job
		 * 5 represente affecter les machines pour executer job dans la grille
		 * 6 represente liberte les ressources 
		 * */
		/*Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(1);//D'abord, Il faux ajouter une evenement "job arrive " dans le file d'attente de evenement.
		
		
		JobManager jobManager=new JobManager();
		RessourceManager ressourceManager=new RessourceManager();
		int idGrille=0;
		Job job=null;
		int idJobExecute=0;
		ressourceManager.AjouterGrille(3);
		boolean flag=true;
		while(flag && !queue.isEmpty()){
			int evenement=queue.poll();
			switch (evenement) {
			case 1:
				jobManager.GenererJob(100);
				queue.add(2);
				break;
			case 2:
				job=jobManager.listJob.get(idJobExecute++);
				queue.add(3);
				break;
			case 3:
				idGrille = ressourceManager.ordonnacerUnJob(job);
				job.setIdGrille(idGrille);
				queue.add(4);
				break;
			case 4:
				ressourceManager.affecterUnJob(idGrille, job);
				queue.add(5);
				break;	
			case 5:				
				if(idJobExecute==jobManager.listJob.size())
					flag=false;
				else{
					queue.add(2);
				}
				break;

			default:
				
				break;
			}
		}
		System.out.println(ressourceManager.checkCMAXOnGrid());*/
		int[][] resultat = new int[20][5];
		int count = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				resultat[i][j] = 0;
			}
		}

		while (count < 20) {
			System.out.println("Le "+(count+1)+" fois!"); 
			JobManager jobManager = new JobManager();
			RessourceManager ressourceManager = new RessourceManager();
			jobManager.GenererJob(100);
			ressourceManager.AjouterGrille(4);
			
			//FAM
			for (int i = 0; i < jobManager.listJob.size(); i++) {
				int idGrille = 0;
				// chercher un grille pour executer le job
				idGrille = ressourceManager.ordonnacerUnJob(jobManager.listJob.get(i));
				jobManager.listJob.get(i).setIdGrille(idGrille);
				// affecter les ressources
				ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
			}
			resultat[count][0] = ressourceManager.checkCMAXOnGrid();
			System.out.println("FAM: " + ressourceManager.checkCMAXOnGrid());
			//CHARGE
			for (int i = 0; i < ressourceManager.listGrille.size(); i++) {
				for (int j = 0; j < ressourceManager.listGrille.get(i).listMachine.size(); j++) {
					ressourceManager.listGrille.get(i).listMachine.get(j).CPURest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).DDRest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).RAMRest.clear();
				}
			}
			
			for (int i = 0; i < jobManager.listJob.size(); i++) {
				int idGrille = 0;
				// chercher un grille pour executer le job
				idGrille = ressourceManager.ordonnancerUnJob_2(jobManager.listJob.get(i));
				jobManager.listJob.get(i).setIdGrille(idGrille);
				// affecter les ressources
				ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
			}
			resultat[count][1] = ressourceManager.checkCMAXOnGrid();
			System.out.println("tauxOccupationCPU: " + ressourceManager.checkCMAXOnGrid());
			//CHARGEMOYEN
			for (int i = 0; i < ressourceManager.listGrille.size(); i++) {
				for (int j = 0; j < ressourceManager.listGrille.get(i).listMachine.size(); j++) {
					ressourceManager.listGrille.get(i).listMachine.get(j).CPURest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).DDRest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).RAMRest.clear();
				}
			}

			for (int i = 0; i < jobManager.listJob.size(); i++) {
				int idGrille = 0;
				// chercher un grille pour executer le job
				idGrille = ressourceManager.ordonnacerUnJob_3(jobManager.listJob.get(i));
				jobManager.listJob.get(i).setIdGrille(idGrille);
				// affecter les ressources
				ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
			}
			resultat[count][2] = ressourceManager.checkCMAXOnGrid();
			System.out.println("tauxOccupationMoyenCPU: " + ressourceManager.checkCMAXOnGrid());
			//NBJOB
			for (int i = 0; i < ressourceManager.listGrille.size(); i++) {
				for (int j = 0; j < ressourceManager.listGrille.get(i).listMachine.size(); j++) {
					ressourceManager.listGrille.get(i).listMachine.get(j).CPURest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).DDRest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).RAMRest.clear();
				}
			}

			for (int i = 0; i < jobManager.listJob.size(); i++) {
				int idGrille = 0;
				// chercher un grille pour executer le job
				idGrille = ressourceManager.ordonnancerUnJob_charge(jobManager.listJob.get(i));
				jobManager.listJob.get(i).setIdGrille(idGrille);
				// affecter les ressources
				ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
			}
			resultat[count][3] = ressourceManager.checkCMAXOnGrid();
			System.out.println("charge: " + ressourceManager.checkCMAXOnGrid());
			//NBJOBMOYEN
			for (int i = 0; i < ressourceManager.listGrille.size(); i++) {
				for (int j = 0; j < ressourceManager.listGrille.get(i).listMachine.size(); j++) {
					ressourceManager.listGrille.get(i).listMachine.get(j).CPURest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).DDRest.clear();
					ressourceManager.listGrille.get(i).listMachine.get(j).RAMRest.clear();
				}
			}

			for (int i = 0; i < jobManager.listJob.size(); i++) {
				int idGrille = 0;
				// chercher un grille pour executer le job
				idGrille = ressourceManager.ordonnancerUnJob_chargeMoyen(jobManager.listJob.get(i));
				jobManager.listJob.get(i).setIdGrille(idGrille);
				// affecter les ressources
				ressourceManager.affecterUnJob(idGrille, jobManager.listJob.get(i));
			}
			resultat[count][4] = ressourceManager.checkCMAXOnGrid();
			System.out.println("chargeMoyen: " + ressourceManager.checkCMAXOnGrid());
			count++;
		}
		//compter les moyen 
		int[] sum = new int[5];
		for (int i = 0; i < 5; i++)
			sum[i] = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				sum[j] += resultat[i][j];
			}
		}
		//Sortir les infos
		System.out.println("Cmax FAM: "+(double) sum[0] / 20);
		System.out.println("Cmax charge: "+(double) sum[1] / 20);
		System.out.println("Cmax chargeMoyen: "+(double) sum[2] / 20);
		System.out.println("Cmax NbJob: "+(double) sum[3] / 20);
		System.out.println("Cmax NbJobMoyen: "+(double) sum[4] / 20);

	}

}
