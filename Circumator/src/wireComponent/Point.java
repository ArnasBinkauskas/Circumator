package wireComponent;

/**
 * Helper class to hold Point coordinates
 * */
public class Point {
	int x;
	int y;
	
	public Point(){
		x = 0;
		y = 0;
	}
	
	public Point(int xCord, int yCord){
		x = xCord;
		y = yCord;
	}
	
	public void setX(int xCord){
		x = xCord;
	}
	
	public void setY(int yCord){
		y = yCord;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
