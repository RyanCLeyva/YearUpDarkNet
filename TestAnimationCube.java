package pkg.edu.gatewaycc.ryan.weektwo;

public class TestAnimationCube {

	int posX;
	int posY;
	
	boolean special;
	public TestAnimationCube(int x,int y) {
		this.posX = x;
		this.posY = y;

	}
	
	public TestAnimationCube setType(int i){
		if(i ==10){
			special = true;
		}
		return this;
	}
	
	public int getBoundingBoxLeft(){
		return posX;
	}
	public int getBoundingBoxRight(){
		return posX+50;
	}

}
