package algorithms;
/*
import java.util.Random;

public class KnapAlgGreedy implements Runnable{

	private TruckSpace storage;

	
	private int cntrA;
	private int cntrB;
	private int cntrC;


	@Override
	public void run() {
		storage = new TruckSpace();

		PackageBox option;
		PackageBox parcel0 = new PackageBox(0,0);
		PackageBox parcel1 = new PackageBox(1,0);
		PackageBox parcel2 = new PackageBox(2,0);
		int volume = storage.getVolume();

		if(parcel2.getValueDensity() > parcel1.getValueDensity() && parcel2.getValueDensity() > parcel0.getValueDensity() && volume - parcel2.getVolume() > 0) {
			option = parcel2;
		}else if(parcel1.getValueDensity() > parcel0.getValueDensity() && volume - parcel1.getVolume() > 0) {
			option = parcel1;
		}else if(volume - parcel0.getVolume() > 0){
			option = parcel0;



			//System.out.println("Next box");
			for(int i=0; i<storage.getLatice().length; i++) {
				for(int j=0; j<storage.getLatice()[i].length; j++) {
					for(int k=0; k<storage.getLatice()[i][j].length; k++) {
						if(option.fits(storage.getLatice(), k, j, i)) {
							option.setX(k);
							option.setY(j);
							option.setZ(i);
							option.insert(storage.getLatice());

							if (option.getLetter() == "C") {
								//System.out.println("C");
								cntrC += option.getValue();
							}else if (option.getLetter() == "B") {
								//System.out.println("B");
								cntrB += option.getValue();
							}else {
								//System.out.println("A");
								cntrA += option.getValue();
							}

						}
						System.out.print(storage.getLatice()[i][j][k]);
					}
					System.out.println();
				}
				System.out.println();
			}

		}

		System.out.println("Total Value: " + (cntrA + cntrB + cntrC));
		System.out.println("A: " + cntrA/parcel0.getValue());
		System.out.println("B: " + cntrB/parcel1.getValue());
		System.out.println("C: " + cntrC/parcel2.getValue());
	}
}
*/