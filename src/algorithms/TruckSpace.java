package algorithms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TruckSpace {
	private int xp;
	private int yp;
	
	int countA = 0;
	int countB = 0;
	int countC = 0;
	
	int countL = 0;
	int countP = 0;
	int countT = 0;

	private int width;
	private int height;
	private int length;
	double fitness;
	PackageBox[] optionsUsed;
	PackagePentomino[] pentOptionsUsed;
	
	private int[][][] latice;
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public PackageBox[] getOptionsArray() {
		return optionsUsed;
	}
	public PackagePentomino[] getPentOptionsArray() {
		return pentOptionsUsed;
	}
	
	public void setOptionsArray(PackageBox[] optionsUsed) {
		this.optionsUsed = optionsUsed;
	}
	public void setPentOptionsArray(PackagePentomino[] optionsUsed) {
		this.pentOptionsUsed = optionsUsed;
	}
	
	public TruckSpace() {
		xp = 100;
		yp = 100;
		length = 11;
		width = 5;
		height = 8;
		
		latice = new int[width][height][length];
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getLength() {
		return length;
	}
	public int getVolume() {
		return width*height*length;
	}
	public int[][][] getLatice() {
		return latice;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setLatice(int[][][] latice) {
		this.latice = latice;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Rectangle2D.Double area = new Rectangle2D.Double(xp,yp,width,height);
		g2.draw(area);
	}
}
