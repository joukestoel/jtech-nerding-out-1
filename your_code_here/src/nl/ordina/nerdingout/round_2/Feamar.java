package nl.ordina.nerdingout.round_2;

import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.SkippedTurnEvent;

import java.awt.*;

public class Feamar extends AdvancedRobot {
	private volatile boolean found = false;
	
	@Override
    public void run() {
		setAdjustRadarForRobotTurn(true);
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while(true) {
            // start your engines for round 2!
        	if (!found) {
        		turnRadarLeft(360);
        	}
        }
        
    }

	@Override
	public void onSkippedTurn(SkippedTurnEvent event) {
		found = false;
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		found = true;
		turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + event.getBearing())));
    	setTurnRight(event.getBearing());
    	if (event.getDistance() < 100) {
    		setFire(1.5);
    	} else {
        	setAhead(50);
    	}
	}
}
