package algorithms;

import java.util.Random;

public class KnapAlgPent implements Runnable{
	
	private int mutationRate = 10;
	private int populationSize = 40;
	private int threshold;
	private PackagePentomino option;
	private TruckSpace storage;

	private int valueA = 3;
	private int valueB = 4;
	private int valueC = 5;

	public KnapAlgPent(int populationSize, int mutationRate, int threshold, int valueA, int valueB, int valueC) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.threshold = threshold;
		this.valueA = valueA;
		this.valueB = valueB;
		this.valueC = valueC;

	}

	public void run() {

		storage = new TruckSpace();

		boolean place;
		int type;
		int rotation;
		Random rand = new Random();

		int boxes;
		PackagePentomino[] optionsUsed;
		TruckSpace[] population = new TruckSpace[populationSize];
		
		while(populationSize>0) {
			storage = new TruckSpace();
			
			boxes = 264;
			optionsUsed = new PackagePentomino[boxes];
			
			while(boxes>0) {
				place = true;
				type = rand.nextInt(8);
				rotation = rand.nextInt(4);
				option = new PackagePentomino(type);

				for(int r = 0; r<rotation; r++) {
					option.rotate();
					//System.out.println("rotate");
				}
				
				
				//System.out.println(option.getLetter());
				
				for(int i=0; i<storage.getLatice().length; i++) {
					for(int j=0; j<storage.getLatice()[i].length; j++) {
						for(int k=0; k<storage.getLatice()[i][j].length; k++) { 
							if(option.fits(storage.getLatice(), k, j, i) && (place == true)) {
								option.setX(k);
								option.setY(j);
								option.setZ(i);
								option.insert(storage.getLatice());
								
								if(option.getLetter() == "L") {
									storage.countL++;
								}else if(option.getLetter() == "P") {
									storage.countP++;
								}else if(option.getLetter() == "T") {
									storage.countT++;
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
			storage.setPentOptionsArray(optionsUsed);
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
	
	public double truckFitness(TruckSpace storage) {
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
	
	public TruckSpace crossover(TruckSpace t_1, TruckSpace t_2) {
		PackagePentomino[] result = new PackagePentomino[t_1.getPentOptionsArray().length];
		
		Random rand = new Random();
		
		for(int i=0; i<t_1.getPentOptionsArray().length; i++) {
			if(rand.nextInt(100) < 50) {
				result[i] = t_1.getPentOptionsArray()[i];
			}else {
				result[i] = t_2.getPentOptionsArray()[i];
			}
		}
		TruckSpace newTruck = new TruckSpace();
		newTruck.setPentOptionsArray(result);
		return newTruck;
		
	}
	
	public TruckSpace[] nextTrucks(TruckSpace[] population) {
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
		
		PackagePentomino currentBox;
		boolean place;
		for(int n=0; n<nextGen.length; n++) {
			if(nextGen[n]==null) {
				System.out.println("Problem");
			}else{
				for(int m=0; m<nextGen[n].getPentOptionsArray().length; m++) {
					place = true;
					if(!(nextGen[n].getPentOptionsArray()[m] == null)) {
						currentBox = nextGen[n].getPentOptionsArray()[m];
						
						for(int i=0; i<nextGen[n].getLatice().length; i++) {
							for(int j=0; j<nextGen[n].getLatice()[i].length; j++) {
								for(int k=0; k<nextGen[n].getLatice()[i][j].length; k++) { 
									if(nextGen[n].getPentOptionsArray()[m].fits(nextGen[n].getLatice(), k, j, i) && (place == true)) {
										currentBox.setX(k);
										currentBox.setY(j);
										currentBox.setZ(i);
										currentBox.insert(nextGen[n].getLatice());
										place = false;
										
										if(currentBox.getLetter() == "L") {
											nextGen[n].countL++;
										}else if(currentBox.getLetter() == "P") {
											nextGen[n].countP++;
										}else if(currentBox.getLetter() == "T") {
											nextGen[n].countT++;
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
	
	
	public TruckSpace mutate(TruckSpace storage) {
		Random rand = new Random();
		int randomType = rand.nextInt(8);
		int randomRotation =  rand.nextInt(4);
		PackagePentomino[] optionsArray = storage.getPentOptionsArray();
		
		//If the random integer is in the mutation range it will change a random chromosome 
		for (int i=0; i<optionsArray.length; i++) {
			if (rand.nextInt(100)<=mutationRate) {
				optionsArray[i] = new PackagePentomino(randomType);
				for (int j=0; j<randomRotation; j++) {
					optionsArray[i].rotate();
				}
			}
		}
		return storage;
	}
	
	public double getFittest(TruckSpace[] population) {
		
		//Rate the population's fitness, sort and return top (since it will have to highest fitness from algorithms.HeapSort)
		for (int i=0; i<population.length; i++) {
			truckFitness(population[i]);
		}
		HeapSort.sort(population);
		return population[0].getFitness();
	}
	
	
	public void algorithm(TruckSpace[] population) {
		int iterations = 0;
		//int counter = 0;
		
		
		while(getFittest(population) < storage.getVolume()) {
			
			for( int i = 0; i< population.length; i++) {
				truckFitness(population[i]);
			}
			
			HeapSort.sort(population);

			System.out.println("Iteration number: " + iterations);
			System.out.println("L: " + population[0].countL + " P: " + population[0].countP + " T: " + population[0].countT);
			System.out.println("Net Value: " + (population[0].countL*3 + population[0].countP*4 + population[0].countT*5));
			//System.out.println("FITTEST: " + getFittest(population));
			System.out.println("Percentage of truck full: " + getFittest(population)/(storage.getVolume())*100);
			System.out.println("---------------------------------------");

			TruckSpace[] newPopulation = nextTrucks(population);
			population = newPopulation;
			iterations++;

			//System.out.println("Truck Population Size: " + population.length);
			//System.out.println("Maximum Volume: " + storage.getVolume());
			//System.out.println("debug: Value of first box used in first truck: " + population[0].getOptionsArray()[0].getValue());
			//System.out.println("debug: Composition of first truck: ");


		} 
	}
	
	public double truckFitnessValue(TruckSpace storage) {
		double netValue = storage.countA*valueA + storage.countB*valueB + storage.countC*valueC;
		storage.setFitness(netValue);
		return netValue;
	}
	

}

