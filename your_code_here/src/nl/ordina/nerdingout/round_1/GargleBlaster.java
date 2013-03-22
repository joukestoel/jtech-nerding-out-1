package nl.ordina.nerdingout.round_1;

import java.util.Date;

import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class GargleBlaster extends AdvancedRobot {
	double shadowBearing;
	double shadowEnergy;
	int scanIgnore = 0;
	
	@Override
	public void run() {
		while (true) {
			turnRadarLeft(360);		
		}
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		if (scanIgnore-- > 0) {
			return;
		}
		
		if (event.getName().equals("abc.Shadow 3.84")) {
			shadowBearing = event.getBearing();
			
			double energyDrop = event.getEnergy() - shadowEnergy;
			if(energyDrop >= 0.1 && energyDrop <=3) {
				scanIgnore = 50;
				turnRight(90);
				ahead(100);
			} else {
				double absBearing = Math.abs(shadowBearing);
				int multiplier = absBearing>90?1:-1;
				if (getClosestWall().range < 30) {
					double diffHeading = Utils.normalRelativeAngleDegrees(getHeading()-getClosestWall().heading);
					//turnRight(multiplier * (getClosestWall().heading>getHeading()?-90:90));
					turnRight(multiplier*-diffHeading);
				}
				
				if (absBearing > 20 && (absBearing < 70 || absBearing > 110)) {
					setTurnRight(-shadowBearing);
				}
				setAhead(multiplier * 100);
			}
			
		}
	}
	
	ClosestWall getClosestWall() {
		if (getMinimalX() < getMinimalY()) {
			return new ClosestWall(getX() < (getBattleFieldWidth()/2)?270:90, getMinimalX());
		} else {
			return new ClosestWall(getY() < (getBattleFieldHeight()/2)?180:0, getMinimalY());
		}
	}
	
	double getMinimalX() {
		return Math.min(Math.abs(getBattleFieldWidth()-getX()) , getX());
	}
	
	double getMinimalY() {
		return Math.min(Math.abs(getBattleFieldWidth()-getX()) , getX());
	}
	
	@Override
	public void onHitWall(HitWallEvent event) {
		setTurnLeft(130);
		setAhead(100);
	}
	
	
	
	
	class ClosestWall {
		public ClosestWall(double heading, double range) {
			this.heading=heading;
			this.range= range;
		}
		double heading;
		double range;		
	}
}
