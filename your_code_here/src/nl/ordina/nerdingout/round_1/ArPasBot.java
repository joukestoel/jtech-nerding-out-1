package nl.ordina.nerdingout.round_1;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.*;

public class ArPasBot extends AdvancedRobot {
	double bfHeight;
	double bfWidth;
	double minDistance = 100;
    @Override
    public void run() {
    	setColors(Color.BLUE, Color.BLUE, Color.BLUE);
    	// bepaal grootte veld
    	bfHeight=getBattleFieldHeight();
    	bfWidth=getBattleFieldWidth();
    	
        while(true) {
            // start your engines for round 1!
        	//radar
        	turnRadarRight(360);
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
    	double bearing = event.getBearing();
    	double absAngle = getHeading() + bearing;
    	double revAbsAngle =  Utils.normalRelativeAngleDegrees(absAngle + 180);
    	double relDirection = Utils.normalRelativeAngleDegrees(getHeading() - absAngle);
    	
    	// Bepaal eigen positie
    	double curX = getX();
    	double curY = getY();
    	System.out.println("bfHeight:"+bfHeight+" bfWidth:"+bfWidth+"^");
    	System.out.println("curX:"+curX+" curY:"+curY+" heading:"+getHeading()+ " absAngle:" +absAngle+" relDirection:"+relDirection+"^");
    	
    	// Ga van muur af indien dicht bij een muur
    	
    	
    	if (curX < minDistance) {
    		if (180 <= absAngle  && absAngle < 360) {
    			relDirection = Utils.normalRelativeAngleDegrees(360 - getHeading());
    		}
    	} else if ((bfWidth-curX) < minDistance) {
    		if (0 <= absAngle  && absAngle < 180) {
    			relDirection = Utils.normalRelativeAngleDegrees(180 - getHeading());
    		}
    	} 
    	
    	
    	if (curY < minDistance) {
    		
    	} else if ((bfHeight-curY) < minDistance) {
    		
    	}
    	
        // Bepaal of scannedRobot shadow is
    	System.out.println("Gevonden robot:"+event.getName()+"^");
    	//abc.Shadow 3.84
    	if (event.getName().indexOf("Shadow")!=-1) {
    		// Wel shadow.
        	System.out.println("Shadow gevonden");
        	setTurnRight(relDirection);
    	} else {
    		// Geen shadow.
        	System.out.println("Geen shadow");
    	}
    	setAhead(100);
    }

    
    
}
