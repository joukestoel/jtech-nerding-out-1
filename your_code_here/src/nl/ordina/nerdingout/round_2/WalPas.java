package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.*;

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
		double haasHeading = event.getHeading();
		double bearing = event.getBearing();
		double curHeading = getHeading();
//		System.out.println("dist:"+distance+"^");
//		System.out.println("curH:"+curHeading+" haasH:"+haasHeading+"^");
		if (bearing < 0) {
			turnLeft(-bearing);
		} else {
			turnRight(bearing);
		}
		// turnRight(normalRelativeAngleDegrees(getHeading() - getRadarHeading()
		// + event.getBearing()));
		setAhead(Double.MAX_VALUE);
		fire(fireBoDistance(distance));
	}

	// fire power based on distance
	private double fireBoDistance(double distance) {
		if (distance < 250) {
			return 3;
		} else {
			return 1;
		}
	}
}