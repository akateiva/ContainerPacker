package algorithms;

import java.util.Random;

public class KnapAlg {
	
	private static int mutationRate = 10;
	
	private static PackageBox option;
	
	private static TruckSpace storage;

	public static void main(String[] args) {

		storage = new TruckSpace();

		boolean place;
		int type;
		int rotation;
		Random rand = new Random();
		
		int populationSize = 100;
		int boxes = 85;
		
		PackageBox[] optionsUsed;
		TruckSpace[] population = new TruckSpace[populationSize];
		
		while(populationSize>0) {
			storage = new TruckSpace();
			
			boxes = 85;
			optionsUsed = new PackageBox[boxes];
			
			while(boxes>0) {
				place = true;
				type = rand.nextInt(3);
				rotation = rand.nextInt(6);
				option = new PackageBox(type, rotation);
				
				//System.out.println(option.getLetter());
				
				for(int i=0; i<storage.getLatice().length; i++) {
					for(int j=0; j<storage.getLatice()[i].length; j++) {
						for(int k=0; k<storage.getLatice()[i][j].length; k++) { 
							if(option.fits(storage.getLatice(), k, j, i) && (place == true)) {
								option.setX(k);
								option.setY(j);
								option.setZ(i);
								option.insert(storage.getLatice());
								
								if(option.getLetter() == "A") {
									storage.countA++;
								}else if(option.getLetter() == "B") {
									storage.countB++;
								}else if(option.getLetter() == "C") {
									storage.countC++;
								}
								
								optionsUsed[optionsUsed.length - boxes] = option;
								place = false;
							}
							//System.out.print(storage.getLatice()[i][j][k]);
						}
					//System.out.println();
					}
				//System.out.println();
				}
				boxes--;	
				
			}
			storage.setOptionsArray(optionsUsed);
			population[population.length-(populationSize)] = storage;
			populationSize--;
		}
		
		
		for(int i=0; i+1<population.length; i++) {
			if(population[i] == null || population[i] == population [i+1]) {
				System.out.println("debug: Problem");
			}else{
				//System.out.println(truckFitness(population[i]));
				HeapSort.sort(population);
			}
		}

		
		
		algorithm(population);

	}
	
	public static double truckFitnessVolume(TruckSpace storage) {
		//double maxFitness = storage.getVolume();
		double currentFitness = 0;
		
		for(int i=0; i<storage.getLatice().length; i++) {
			for(int j=0; j<storage.getLatice()[i].length; j++) {
				for(int k=0; k<storage.getLatice()[i][j].length; k++) { 
					if(storage.getLatice()[i][j][k] == 1) {
						currentFitness++;
					}
				}
			}
		}
		storage.setFitness(currentFitness);
		return currentFitness;
	}
	
	
	
	public static TruckSpace crossover(TruckSpace t_1, TruckSpace t_2) {
		PackageBox[] result = new PackageBox[t_1.getOptionsArray().length];
		
		Random rand = new Random();
		
		for(int i=0; i<t_1.getOptionsArray().length; i++) {
			if(rand.nextInt(100) < 50) {
				result[i] = t_1.getOptionsArray()[i];
			}else {
				result[i] = t_2.getOptionsArray()[i];
			}
		}
		TruckSpace newTruck = new TruckSpace();
		newTruck.setOptionsArray(result);
		return newTruck;
		
	}
	
	public static TruckSpace[] nextTrucks(TruckSpace[] population) {
		TruckSpace[] nextGen = new TruckSpace[population.length];
		int counter = 0;
		
		for(int i=0; i<nextGen.length/2; i++) {
			nextGen[i] = crossover(population[counter],population[counter+1]);
			counter++;
		}
		counter = 0;
		
		for(int i=nextGen.length/2; i<nextGen.length; i++) {
			nextGen[i] = crossover(population[counter],population[counter+1]);
		}
		
		PackageBox currentBox;
		boolean place;
		for(int n=0; n<nextGen.length; n++) {
			if(nextGen[n]==null) {
				System.out.println("Problem");
			}else{
				for(int m=0; m<nextGen[n].getOptionsArray().length; m++) {
					place = true;
					if(!(nextGen[n].getOptionsArray()[m] == null)) {
						currentBox = nextGen[n].getOptionsArray()[m];
						
						for(int i=0; i<nextGen[n].getLatice().length; i++) {
							for(int j=0; j<nextGen[n].getLatice()[i].length; j++) {
								for(int k=0; k<nextGen[n].getLatice()[i][j].length; k++) { 
									if(nextGen[n].getOptionsArray()[m].fits(nextGen[n].getLatice(), k, j, i) && (place == true)) {
										currentBox.setX(k);
										currentBox.setY(j);
										currentBox.setZ(i);
										currentBox.insert(nextGen[n].getLatice());
										place = false;
										
										if(currentBox.getLetter() == "A") {
											nextGen[n].countA++;
										}else if(currentBox.getLetter() == "B") {
											nextGen[n].countB++;
										}else if(currentBox.getLetter() == "C") {
											nextGen[n].countC++; 
										}
									}
								}
							}
						}
					}
				}
			}
			mutate(nextGen[n]);
		}
		return nextGen;
	}
	
	
	public static TruckSpace mutate(TruckSpace storage) {
		Random rand = new Random();
		int randomType = rand.nextInt(3);
		int randomRotation =  rand.nextInt(6);
		PackageBox[] optionsArray = storage.getOptionsArray();
		
		//If the random integer is in the mutation range it will change a random chromosome 
		for (int i=0; i<optionsArray.length; i++) {
			if (rand.nextInt(100)<=mutationRate) {
				optionsArray[i] = new PackageBox(randomType, randomRotation);
			}
		}
		return storage;
	}
	
	public static double getFittest(TruckSpace[] population) {
		
		//Rate the population's fitness, sort and return top (since it will have to highest fitness from algorithms.HeapSort)
		for (int i=0; i<population.length; i++) {
			truckFitnessVolume(population[i]);
		}
		HeapSort.sort(population);
		return population[0].getFitness();
	}
	
	
	public static void algorithm(TruckSpace[] population) {
		int iterations = 0;
		//int counter = 0;
		
		
		while(getFittest(population) < storage.getVolume()) {
			TruckSpace[] newPopulation = nextTrucks(population);
			population = newPopulation;
			iterations++;
			
			//System.out.println("Truck Population Size: " + population.length);
			//System.out.println("Maximum Volume: " + storage.getVolume());
			//System.out.println("debug: Value of first box used in first truck: " + population[0].getOptionsArray()[0].getValue());
			//System.out.println("debug: Composition of first truck: ");
			
			System.out.println("Iteration number: " + iterations);
			System.out.println("A: " + population[0].countA + " B: " + population[0].countB + " C: " + population[0].countC);
			System.out.println("FITTEST: " + getFittest(population));
			//System.out.println("Net Value: " + (population[0].countA*3 + population[0].countB*4 + population[0].countC*5));
			System.out.println("Percentage of truck full: " + getFittest(population)/(storage.getVolume())*100);
			System.out.println("---------------------------------------");
			
			for( int i = 0; i< population.length; i++) {
				truckFitnessVolume(population[i]);
			}
			
			HeapSort.sort(population);
			
			/*
			for( int i = 0; i< population.length; i++) {
				System.out.println(truckFitness(population[i]));
			} */
			
		}
	}
	
	private static int valueA = 3;
	private static int valueB = 4;
	private static int valueC = 5;
	
	public static double truckFitnessValue(TruckSpace storage) {
		double netValue = storage.countA*valueA + storage.countB*valueB + storage.countC*valueC;
		storage.setFitness(netValue);
		return netValue;
	}
	

}