package pkg.edu.gatewaycc.ryan.weektwo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class TestAnimation extends Applet implements Runnable, KeyListener {
	
	ArrayList<TestAnimationCube> list = new ArrayList<TestAnimationCube>();
	Random rr = new Random();
	int tick = 0;
	boolean w;
	boolean a;
	boolean s;
	boolean d;

	@Override
	public void init() {
		this.addKeyListener(this);
		list.add(new TestAnimationCube(0,640));
		this.setSize(1000, 700);
		new Thread(this).start();
		super.init();
	}
	
	int speedX = 40;
	int speedY = 0;
	boolean onGround;
	
	int x = 55;
	int posX = 0;
	int posY = 0; 
	int oldX;
	int oldY;
	int oldPosX;
	int oldPosY;
	int size = 50;
	int multiplierX = 1;
	int multiplierY = 1;
	boolean debug = false;
	
	
	public void paint(Graphics g){
		
		g.setColor(new Color(0,0,0));
		g.fillRect(500, posY, size, size);
		g.setColor(new Color(100,100,100));

		g.fillRect(0, 690, 1000, 700);

		g.drawString("posX: "+posX+" x: "+x,10,10);
		g.drawString("posY: "+posY+" y: "+posY,10,20);
		g.drawString(debug+"",10,30);
		
		for(int i = 0; i<list.size(); i++){
			TestAnimationCube cube = list.get(i);
			if(cube.special)g.setColor(new Color(getRandom(255),getRandom(255),getRandom(255)));
			else
			g.setColor(new Color(100,100,100));
			g.fillRect(cube.posX-this.posX+500, cube.posY, 50, 50);
		}
	}
	
	private void endGame(){
		size = 50;
		x = 10;
		posY = 10;
		multiplierY = 1;
		multiplierX = 1;
		tick=0;
		list.clear();
	}
	
	int getRandom(int i){
		return rr.nextInt(i);
	}
	
	


	@Override
	public void run() {
		while(true){
			tick++;
			this.repaint();
			if(size>this.getWidth() || size>this.getHeight()){
				endGame();
			}
			oldPosY = posY;
			posY+=(speedY*multiplierY);
			if(x+size>this.getWidth())
			{
				speedX = -speedX;
			}
			if(posY+size>this.getHeight())
			{
				speedY = 0;
			}
			if(x<0)
			{
				speedX = -speedX;
			}

			if(w && posY>640){
				speedY=-30;
			}else{
				speedY+=2;
			}
			if(a){
				if(speedX>-20)
				{
					speedX--;
				}
			}else
			if(d){
				
				if(speedX<20)
				{
					speedX++;
				}
			}else
			{
				if(speedX>0){
					speedX--;

				}
				if(speedX<0)
				{

					speedX++;
				}

			}

				oldPosX = posX;
				posX+=(speedX*multiplierX);

			for(TestAnimationCube cube: list){
				if(posY+50> cube.posY&&posY<cube.posY+50 && posX+50> cube.getBoundingBoxLeft()&&posX<cube.getBoundingBoxRight() ){
					posY = oldPosY;
				}
				if(posX+50> cube.getBoundingBoxLeft()&&posX<cube.getBoundingBoxRight() && posY+55>=cube.posY+5){
					posX = oldPosX;
					speedX = 0;
					debug = true;
				}else{
					debug = false;
				}

			}

				
			
			if(x+size>950){
				x = 950-size;
			}
			if(posY+size>690){
				posY = 690-size;
			}
			if(x<0) x = 0;
			if(posY<0) posY=0;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() =='s'){
			s = true;
		}else
		if(e.getKeyChar() =='w'){
			w= true;
		}
		if(e.getKeyChar() =='a'){
			a = true;
		}else
			if(e.getKeyChar() =='d'){
				d = true;
			}


		
	}
	private int getSpeedX(){
		int sx = speedX;
		int sy = speedY;
		if(speedX<0){
			sx = speedX*-1;
		}
		if(speedY<0){
			sy = speedY*-1;
		}
		return sx;
	}
	
	private boolean toSpeed(){
		int sx = speedX;
		int sy = speedY;
		if(speedX<0){
			sx = speedX*-1;
		}
		if(speedY<0){
			sy = speedY*-1;
		}
		return sx+sy>=10;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() =='w'){
			w= true;

		}
		if(e.getKeyChar() =='a'){
			a = true;

		}
		if(e.getKeyChar() =='s'){
			s = true;

			
		}
		if(e.getKeyChar() =='d'){
			d = true;

		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() =='w'){
			w= false;

		}
		if(e.getKeyChar() =='a'){
			a = false;

		}
		if(e.getKeyChar() =='s'){
			s = false;

		}
		if(e.getKeyChar() =='d'){
			d = false;

		}		
	}
}

