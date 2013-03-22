package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;


public class ElmerFudd extends AdvancedRobot {
	Color brown = new Color(0xd2691e);
	
	@Override
	public void run() {
		setColors(brown, Color.GRAY, brown);
		while (true)  {
			turnRadarLeft(360);
		}
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {		
		double gunBearing = Utils.normalRelativeAngleDegrees((getGunHeading()-getHeading()+360)%360);
		if (Math.abs(event.getBearing() - gunBearing) < 3) {
			fire(3);
		}
		
		turnRight(event.getBearing());	
		setAhead(10000);		
	}
	
	@Override
	public void onHitRobot(HitRobotEvent event) {
		fire(3);
		ahead(5);
	}

}
