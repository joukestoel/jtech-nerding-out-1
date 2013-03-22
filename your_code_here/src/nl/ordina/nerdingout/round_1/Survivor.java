package nl.ordina.nerdingout.round_1;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Survivor extends AdvancedRobot {
	private static final String SHADOW = "abc.Shadow 3.84";

	@Override
	public void run() {
		int r = 0;
		while (true) {
			r += 16;
            if (r>=256) r = 0;
			setBodyColor(new Color(r,0,255-r));
            turnRadarLeft(360);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
    	if (SHADOW.equals(e.getName())) {
        	double bearing = e.getBearing();
        	double angle = normalRelativeAngleDegrees((getHeading() - getRadarHeading()) + bearing);
        	setTurnRight(180 + angle);        		
        	setAhead(Double.POSITIVE_INFINITY);
    	}
	}

	@Override
	public void onHitWall(HitWallEvent e) {
		turnRight(Math.random() * 360 - 180);
    	setAhead(Double.POSITIVE_INFINITY);
        setTurnRadarLeft(360);
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		setTurnRight(Math.random() * 360 - 180);
    	setAhead(Double.POSITIVE_INFINITY);
        setTurnRadarLeft(360);
	}
}
