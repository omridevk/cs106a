/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

public class Target2 extends GraphicsProgram {	
	
	public void run() {
		double radius = 152;
		label = new GLabel("");
		label.setFont("Times New Roman-36");
		add(label, 200, 400);
		addMouseListeners();
		double x = getWidth()/2 - radius;
		double y = getHeight()/2 - radius;		
		for (int i = 0; i <= 3 ; i++){
			GOval circle = new GOval (x,y,radius*2,radius*2);
			if ( i%2 != 0){
				circle.setFilled(true);
				circle.setFillColor(Color.red);
			} else {
				circle.setFilled(true);
				circle.setFillColor(Color.WHITE);
			}
			add(circle);
			radius = radius - (radius * (i*0.35));
			x = getWidth()/2 - radius;
			y = getHeight()/2 - radius;	
			double test = circle.getX();
			double testy = circle.getY();
			double screenwidth = getWidth()/2;
			GLabel location = new GLabel ("getx is:"+test + "x is:"+x +"width is:"+screenwidth + "\n getYis: "+testy, 10*i,10*i);
			add(location);
		}
		/* You fill this in. */
	}
	
	public void mouseMoved(MouseEvent e) {
		label.setLabel("Mouse: (" + e.getX() + ", " + e.getY() + ")");
	}
	
	private GLabel label;
}
