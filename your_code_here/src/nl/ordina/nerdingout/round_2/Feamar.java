package nl.ordina.nerdingout.round_2;

import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Feamar extends AdvancedRobot {
	private boolean found = false;
	
	@Override
    public void run() {
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while(true) {
            // start your engines for round 2!
        	if (!found) {
        		turnRadarLeft(360);
        	}
        }
        
    }

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		found = true;
		System.out.println("Haas");
		turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + event.getBearing())));
	}
}
