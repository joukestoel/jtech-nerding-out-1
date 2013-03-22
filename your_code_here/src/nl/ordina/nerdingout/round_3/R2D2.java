package nl.ordina.nerdingout.round_3;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class R2D2 extends AdvancedRobot {
	int direction = 1;	
	Map<String, ScannedRobotEvent> enemies = new HashMap<String, ScannedRobotEvent>();
	
	@Override
	public void run() {
		setColors(Color.WHITE, Color.BLUE, Color.WHITE);
		setAdjustGunForRobotTurn(true);
		while (true)  {
			turnRadarLeft(360);
		}
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		enemies.put(event.getName(), event);
		fireOn(selectTarget());		
	}

	private void fireOn(ScannedRobotEvent event) {
		double gunBearing = Utils.normalRelativeAngleDegrees((getGunHeading()-getHeading()+360)%360);
		setTurnGunRight(event.getBearing() - gunBearing);
		fire(1);
		
		setTurnRight(event.getBearing()-90);	
		setAhead(direction * 100);
	}
	
	@Override
	public void onHitRobot(HitRobotEvent event) {
		fire(3);
		ahead(5);
	}	
	
	@Override
		public void onHitWall(HitWallEvent event) {
		direction *= -1;
	}
	
	ScannedRobotEvent selectTarget() {
		ScannedRobotEvent target = enemies.values().iterator().next();
		for (ScannedRobotEvent e : enemies.values()) {
			if (e.getDistance() < target.getDistance()) {
				target=e;
			}
		}
		return target;
	}
}