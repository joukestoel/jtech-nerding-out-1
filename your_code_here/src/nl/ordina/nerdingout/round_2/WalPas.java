package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.ScannedRobotEvent;

public class WalPas extends AdvancedRobot {

    @Override
	public void run() {
		setColors(Color.BLUE, Color.BLUE, Color.WHITE);
		while (true) {
			turnRadarRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		double distance = event.getDistance();
		
		double bearing = event.getBearing();
//		System.out.println("dist:"+distance+"^");
		if (bearing < 0) {
			turnLeft(-bearing);
		} else {
			turnRight(bearing);
		}
		setAhead(Double.MAX_VALUE);
		fire(fireBoDistance(distance));
	}

	// fire power based on distance
	private double fireBoDistance(double distance) {
		if (distance < 350) {
			return 3;
		} else {
			return 1;
		}
	}
	
	@Override
	public void onBulletHit(BulletHitEvent event) {
		setBodyColor(new Color(randValue(), randValue(), randValue()));
	}
	
	private int randValue() {
		return (int)(Math.random() * 128 + 128);
	}
}