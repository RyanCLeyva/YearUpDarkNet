package pkg.edu.gatewaycc.ryan.weektwo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class TestLaser {
	
	TestAnimation parent;
    private long Delay;
    Random r = new Random();

	int timer = 0;
	
	int x,y;
	int xModifier;
    
	public TestLaser(TestAnimation cube) {
		parent = cube;
	}
	
	public void shootLaser(int x, int y, int posX){
		timer = parent.tick;
		this.x = x;
		this.y = y;
		xModifier = posX;
	}
	
	public void drawLaser(Graphics g){
		if((parent.tick-timer)<=15){
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawLine(525, parent.posY+25, x -(parent.posX-xModifier), y);
		}
	}
	
    private boolean wait(int var1)
    {
        if (System.currentTimeMillis() - this.Delay > (long)var1)
        {
            this.Delay = System.currentTimeMillis();
            return true;
        }
        else
        {
            return false;
        }
    }

}
