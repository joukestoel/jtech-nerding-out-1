package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.*;
import java.util.Random;

public class WalPas extends AdvancedRobot {
    private Random random = new Random();

    @Override
	public void run() {
		setColors(Color.BLUE, Color.BLUE, Color.WHITE);
		while (true) {
			turnRadarRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		double distance = event.getDistance();
		turnRight(event.getBearing());
		// turnRight(normalRelativeAngleDegrees(getHeading() - getRadarHeading()
		// + event.getBearing()));
		// ahead(20);
		fire(2);
	}

	private double spread(int degrees) {
		return spreadFactor() * degrees;
	}

	private double spreadFactor() {
		return (random.nextDouble() * 2) - 1;
	}

}