/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aco.main;

import java.util.ArrayList;

import aco.support.ACOSystem;
import aco.support.Solution;

import util.Reader;

/**
 * 
 * @author abuzaher
 */
public class AcoLongestPath {

	public static void main(String args[]) throws InterruptedException {
		// set program running parameters here
		int numIterations = 10;
		int numAnts = 10;
		double initialPheromoneAmount = 20.0;
		double antPheromoneDepositAmount = 1.0;
		double daemonPheromoneDepositAmount = 10.0;
		double pheromoneEvaporationRate = 0.3;
		double maxPheromoneThreshold = 100.0;
		double minPheromoneThreshold = 0.001;
		double alpha = 3.0;
		double beta = 2.0;
		// the less the exploitation factor, the more probabilistic the nature
		double exploitationFactor = 0.1;
		String inputFilePath = "input/entrada3.txt";

		for (int j = 10; j < 1000; j++) {
			numIterations = j;
			double optimumCost = 1556.0;
			int runs = 100;
			int successCount = 0;
			long totalExecTimeForAllRuns = 0;
			double quality = 0;

			// System.out.print(numIterations + " ");

			for (int i = 0; i < runs; i++) {
				ACOSystem acoSystem;

				ArrayList<Double> inputList = readInputFromFile(inputFilePath);

				long startTime = System.nanoTime();

				// setting all the parameters
				acoSystem = new ACOSystem(inputList);
				intializeAcoSystemParameters(numIterations, numAnts,
						initialPheromoneAmount, antPheromoneDepositAmount,
						daemonPheromoneDepositAmount, pheromoneEvaporationRate,
						maxPheromoneThreshold, minPheromoneThreshold, alpha,
						beta, exploitationFactor, acoSystem);

				// acoSystem.initializePheromoneMatrix();
				acoSystem.run();

				Solution solution = acoSystem.getSolution();

				long endTime = System.nanoTime();

				// System.out.println("GLOABAL SOLUTION");
				// System.out.println(solution);
				totalExecTimeForAllRuns += endTime - startTime;
				
				if (solution.getCost() >= optimumCost) {
					successCount++;
				}

				quality += solution.getCost() / optimumCost;
				System.out.println(i);
				// System.out.println("Execution time: " + execTime / 1000000 +
				// " milli seconds");
				// acoSystem.printPheromoneMatrix();

			}

			// System.out.println(successCount / (double) runs * 100 +
			// "% success rate");
			// System.out.println("Avg Running time: " + totalExecTimeForAllRuns
			// / (double) runs / 1000000 + " milliseconds");
			// System.out.println("Avg quality: " + quality / runs * 100);
			System.out.println(numIterations + " " + successCount
					/ (double) runs * 100 + "% " + totalExecTimeForAllRuns
					/ (double) runs / 1000000 + " " + quality / runs * 100);

		}
	}

	private static void intializeAcoSystemParameters(int numIterations,
			int numAnts, double initialPheromoneAmount,
			double antPheromoneDepositAmount,
			double daemonPheromoneDepositAmount,
			double pheromoneEvaporationRate, double maxPheromoneThreshold,
			double minPheromoneThreshold, double alpha, double beta,
			double exploitationFactor, ACOSystem acoSystem) {
		acoSystem.setNumIterations(numIterations);
		acoSystem.setNumAnts(numAnts);
		acoSystem.setInitialPheromoneAmount(initialPheromoneAmount);
		acoSystem
				.setAntPheromoneDepositAmount(antPheromoneDepositAmount);
		acoSystem
				.setDaemonPheromoneDepositAmount(daemonPheromoneDepositAmount);
		acoSystem.setPheromoneEvaporationRate(pheromoneEvaporationRate);
		acoSystem.setMinPheromoneThreshold(minPheromoneThreshold);
		acoSystem.setMaxPheromoneThreshold(maxPheromoneThreshold);
		acoSystem.setAlpha(alpha);
		acoSystem.setBeta(beta);
		acoSystem.setExploitationFactor(exploitationFactor);
	}

	private static ArrayList<Double> readInputFromFile(String inputFilePath) {
		// Reader.openFile("input2.txt");
		// Reader.openFile("longestPath.txt");
		// Reader.openFile("input3.txt");
		// Reader.openFile("input4.txt");
		// Reader.openFile("input5.txt");
		Reader.openFile(inputFilePath);
		ArrayList<Double> inputList = Reader.readFile();
		Reader.closeFile();
		
		return inputList;
	}
}
