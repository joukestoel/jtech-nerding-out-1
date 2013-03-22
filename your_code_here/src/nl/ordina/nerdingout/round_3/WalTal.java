package nl.ordina.nerdingout.round_3;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.*;

public class WalTal extends AdvancedRobot {
	@Override
	public void run() {
		setTurnLeft(Math.random() * 90);
		setAdjustRadarForRobotTurn(true);
		setColors(Color.CYAN, Color.YELLOW, Color.MAGENTA);
		turnLeft(getHeading());
		setAhead(1000);
		while (true) {
			turnRadarLeft(90);
		}

	}

	@Override
	public void onHitWall(HitWallEvent event) {
		turnRight(90);
		setAhead(100000);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		 if (event.getDistance() < 300) {
		 turnGunRight(normalRelativeAngleDegrees(((getHeading() -
		 getGunHeading()) + event.getBearing())));
		 setFire(3);
		 }
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		setAhead(100000);
	}
}
