package ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import javax.swing.JPanel;

import logic.Configuration;

public class SplashScreen extends JPanel {
	
	protected static final BufferedImage splashBG = DrawingUtility.getImage("res/SplashScreen/SplashBG.png");
	protected static final BufferedImage splashTitle = DrawingUtility.getImage("res/SplashScreen/SplashTitle.png");
	protected int opa = 0;
	protected int rotate = 0;
	protected int count = 0;
	protected boolean rotateCCW = false;
	protected boolean finished = false;
	
	public SplashScreen(){
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Configuration.screenWidth,Configuration.screenHeight));
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void update(){
		//140 Steps : 14 wait + 34 fadeIn + 60 rotate + 32 wait&fadeOut
		if(count < 14) {
			this.rotate = 0;
			this.opa = 0;
		} else if(count < 48){
			this.opa += 5;
			this.rotate = 0;
		} else if(count < 108){
			if(this.rotateCCW){
				this.rotate += 2;
				if(this.rotate>=10) {
					this.rotateCCW = false;
				}
			} else {
				this.rotate -= 2;
				if(this.rotate<=-10) {
					this.rotateCCW = true;
				}
			}
		} else if(count < 140) {
			if(this.opa>0)
				this.opa -= 5;
			this.rotate = 0;
		} else {
			finished = true;
		}
		count++;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		DrawingUtility.drawSplashScreen(g2, opa, rotate, splashBG, splashTitle);
	}
}