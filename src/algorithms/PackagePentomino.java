package algorithms;

import org.joml.Vector3f;

public class PackagePentomino {
	private int xp;
	private int yp;
	private int zp;

	private String letter;
	private int width;
	private int height;
	private int length;
	private int value;
	
	//private int[][] rotationMatrix  = new int[][]{{1,0,0},{0,0,-1},{0,1,0}};
	
	private final int MATRIX_SIZE = 4;
	
	private int[][][] latice;
	
	public PackagePentomino(int type){
		
		switch(type) {
		case 0:
			length = 1;
			width = 3;
			height = 4;
			letter = "L";
			value = 3;
			latice = new int[][][]
					{
						{
							{1,0,0,0},
							{1,0,0,0},
							{1,0,0,0},
							{1,1,0,0},
						},
						{
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						}
					};
			break;
			
		case 1:
			length = 1;
			width = 3;
			height = 4;
			letter = "L";
			value = 3;
			latice = new int[][][]
					{
						{
							{1,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{1,1,1,1},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						}
					};
			break;
			
		case 2:
			length = 1;
			width = 3;
			height = 4;
			letter = "L";
			value = 3;
			latice = new int[][][]
					{
						{
							{1,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{1,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{1,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						},
						{
							{1,1,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
						}
					};
			break;
			
		case 3:
			length = 1;
			width = 3;
			height = 4;
			letter = "P";
			value = 4;
			latice = new int[][][]
					{
				{
					{0,0,0,0},
					{1,1,0,0},
					{1,1,0,0},
					{1,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
			
		case 4:
			length = 1;
			width = 3;
			height = 4;
			letter = "P";
			value = 4;
			latice = new int[][][]
					{
				{
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
			
		case 5:
			length = 1;
			width = 3;
			height = 4;
			letter = "P";
			value = 4;
			latice = new int[][][]
					{
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{1,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{1,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
			
		case 6:
			length = 1;
			width = 3;
			height = 4;
			letter = "T";
			value = 5;
			latice = new int[][][]
					{
				{
					{0,0,0,0},
					{1,1,1,0},
					{0,1,0,0},
					{0,1,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
		
		case 7:
			length = 1;
			width = 3;
			height = 4;
			letter = "T";
			value = 5;
			latice = new int[][][]
					{
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,0,1,0},
					{0,0,1,0},
					{0,0,1,0},
				},
				{
					{0,0,0,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
			
		case 8:
			length = 1;
			width = 3;
			height = 4;
			letter = "T";
			value = 5;
			latice = new int[][][]
					{
				{
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				{
					{0,0,0,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
				}
			};
			break;
		}
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
	
	public int getX(int x) {
		return x;
	}
	public int getY(int y) {
		return y;
	}
	public int getZ(int z) {
		return z;
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
	public int getValue() {
		return value;
	}
	
	public double valueDensity() {
		double volume = getVolume();
		return value/volume;
	}
	
	public double getVolume() {
		return 5;
	}
	public String getLetter() {
		return letter;
	}
	
	public int[][][] getLatice() {
		return latice;
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
		
	public void rotate(){
		
		for (int k = 0; k<MATRIX_SIZE; k++) {
			int[][] tmp = new int[MATRIX_SIZE][MATRIX_SIZE];
			for (int i = 0; i < MATRIX_SIZE; i++) {
				for (int j = 0; j < MATRIX_SIZE; j++) {
				tmp[j][MATRIX_SIZE - 1 - i] = latice[k][i][j];
				}	
			}
			latice[k] = tmp;
		}	
	}	
}
