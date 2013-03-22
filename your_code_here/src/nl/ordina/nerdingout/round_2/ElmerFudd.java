package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;


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
		if (event.getBearing() == getGunHeading()-getHeading()) {
			
		}
		
		setTurnRight(event.getBearing());	
		setFire(0.5);
		setAhead(100);		
	}
	
	@Override
	public void onHitRobot(HitRobotEvent event) {
		fire(3);
		ahead(5);
	}
}
