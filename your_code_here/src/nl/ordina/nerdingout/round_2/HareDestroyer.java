package nl.ordina.nerdingout.round_2;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.*;

import java.awt.*;


public class HareDestroyer extends AdvancedRobot {

    private double fireDistance = 300;
    private double stoppingDistance = 100;
    
    public void run() {
        setBodyColor(Color.GREEN);
        setGunColor(Color.WHITE);
        setRadarColor(Color.GREEN);

        while (true) {
            turnRadarLeft(360);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {


        setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
        double bearing = e.getBearing();
        
        if (Math.abs(bearing) > 90) {
        	turnRight(bearing);
        } else {
        	setTurnRight(bearing);
        }

        if (e.getDistance() > stoppingDistance) {
            setAhead(50);
        }

        if (e.getDistance() < fireDistance) {
        	double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        	setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - 
        	    getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - 
        	    absoluteBearing) / 13.0)));
        	setFire(3.0);
        }

    }
    
    @Override
    public void onRobotDeath(RobotDeathEvent e) {
    	setTurnGunRight(Double.POSITIVE_INFINITY);
    	setBack(50);
    	for (int i = 0; i < 10; i++) {
    		turnRight(40);
    		turnLeft(40);
    	}
    		
    }

}
