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

public class Target extends GraphicsProgram {	
	
	public void run() {
		double radius = 152;
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
			double screenwidth = getWidth()/2;
			GLabel location = new GLabel ("getx is:"+test + "x is:"+x +"width is:"+screenwidth, 10*i,10*i);
			add(location);
		}
		/* You fill this in. */
	}
}
