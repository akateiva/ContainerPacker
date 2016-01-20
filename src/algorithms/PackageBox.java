package algorithms;

import org.joml.Vector3f;

public class PackageBox {
	private int xp;
	private int yp;
	private int zp;
	
	private String letter;
	private int width;
	private int height;
	private int length;
	private double value;
	//private int volume;
	
	private int[][][] latice;
	
	public PackageBox(int type, int rotation){
		
		switch(type) {
		case 0:
			switch(rotation) {
			case 0:
				length = 2;
				width = 2;
				height = 4;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 1:
				length = 2;
				width = 4;
				height = 2;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 2:
				length = 4;
				width = 2;
				height = 2;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 3:
				length = 2;
				width = 2;
				height = 4;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 4:
				length = 2;
				width = 4;
				height = 2;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 5:
				length = 4;
				width = 2;
				height = 2;
				letter = "A";
				value = 3;
				latice = new int[length][width][height];
				numberFill();
				break;
			}
			break;
		case 1:
			switch(rotation) {
			case 0: 
				length = 2;
				width = 3;
				height = 4;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 1:
				length = 2;
				width = 4;
				height = 3;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 2:
				length = 3;
				width = 2;
				height = 4;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 3:
				length = 3;
				width = 4;
				height = 2;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 4:
				length = 4;
				width = 2;
				height = 3;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			case 5:
				length = 4;
				width = 3;
				height = 2;
				letter = "B";
				value = 4;
				latice = new int[length][width][height];
				numberFill();
				break;
			}
			break;
		case 2:
			length = 3;
			width = 3;
			height = 3;
			letter = "C";
			value = 5;
			latice = new int[length][width][height];
			numberFill();
			break;
		}
	}
	
	public String getLetter() {
		return letter;
	}
	
	public void setX(int x) {
		xp = x;
	}
	public void setY(int y) {
		yp = y;
	}
	public void setZ(int z) {
		zp = z;
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
		return length*width*height;
	}

	public double getValue() {
		return value;
	}
	public double getValueDensity() {
		double volume = length*width*height;
		return value/volume;
	}
	
	public int[][][] getLatice() {
		return latice;
	}
	
	public void numberFill() {
		for(int i=0; i<latice.length; i++) {
			for(int j=0; j<latice[i].length; j++) {
				for(int k=0; k<latice[i][j].length; k++) { 
					latice[i][j][k] = 1;
				}
			}
		}
	}
	
	public boolean fits(int[][][] board, int x, int y, int z){
		for(int k = 0; k < latice.length; k++) {
			for(int i = 0; i < latice[k].length; i++){
				for(int j = 0; j < latice[k][i].length; j++){
					//Check if a piece of a pentomino is out of the bounds, making it not fit
					if(latice[k][i][j] != 0 && (y + i >= board[k].length  || x + j >= board[k][i].length || k + z >= board.length)){
						return false;
					}
					//If we got here, it means we are working within the bounds of the board
					//Check the current piece of the pentomino (m_matrix[x][y]) is not overlapping with an existing pentomino within the board
					if(k + z < board.length || y + i < board[k].length  || x + j < board[k][i].length){
						if(latice[k][i][j] != 0 && board[k+z][y+i][x+j] != 0)
							return false;
					}
				}
			}
		}
		return true;
	} 
	
	public void insert(int[][][] board){
		for(int k = 0; k < latice.length; k++) {
			for(int i = 0; i < latice[k].length; i++){
				for(int j = 0; j < latice[k][i].length; j++){
					if(zp + i < board.length || yp + i < board[k].length || xp + j < board[k][i].length){
						try{
							if(latice[k][i][j] != 0){
								board[zp+k][yp+i][xp+j] = 1;
							}
						}
						catch(Exception e){
	
						}
					}
				}
			}
		}
	}
	public void insertColorMask(Vector3f[][][] colormask, Vector3f color){
		for(int k = 0; k < latice.length; k++) {
			for(int i = 0; i < latice[k].length; i++){
				for(int j = 0; j < latice[k][i].length; j++){
					if(zp + i < colormask.length || yp + i < colormask[k].length || xp + j < colormask[k][i].length){
						try{
							if(latice[k][i][j] != 0){
								colormask[zp+k][yp+i][xp+j] = color;
							}
						}
						catch(Exception e){

						}
					}
				}
			}
		}
	}
	
	
	
}
