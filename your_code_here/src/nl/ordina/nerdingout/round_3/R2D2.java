package nl.ordina.nerdingout.round_3;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class R2D2 extends AdvancedRobot {
	private Random random = new Random();
	private Map<String, Enemy> enemies = new HashMap<String, Enemy>();
	
    @Override
    public void run() {
        setColors(Color.WHITE, Color.BLUE, Color.WHITE);
        setAdjustGunForRobotTurn(true);
        while (true) {
            setTurnRadarLeft(360);
            randomMoves();
            
            fireUpon(selectBestTarget());
        }
    }

	private Enemy selectBestTarget() {
		Enemy closest = enemies.values().iterator().next();
		for (Enemy e : enemies.values()) {
			System.out.println("enemy in list: " + e.name + ", distance: " + e.distance);
			if (e.distance < closest.distance) {
				closest = e;
			}
		}
		System.out.println("selected: " + closest.name);
		
		return closest;
	}

	private void randomMoves() {
		if (chance(0.1)) {
		    setAhead(spread(100));
		} else {
		    setAhead(100);
		}

		if (chance(0.9)) {
		    turnRight(spread(50));
		} else {
		    turnRight(180);
		}
	}

    private boolean chance(double chance) {
        return random.nextDouble() <= chance;
    }

    private double spread(int degrees) {
        return spreadFactor() * degrees;
    }

    private double spreadFactor() {
        return (random.nextDouble() * 2) - 1;
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        setAhead(spread(50)-100);
        if (chance(0.5)) {

            turnRight(90 + e.getBearing());
        } else {
            turnRight(180);
        }
    }

    @Override
    public void onHitRobot(final HitRobotEvent e) {
        fire(3);
        ahead(1);
    	turnRight(90 + e.getBearing());
    }

    @Override
    public void onHitByBullet(final HitByBulletEvent e) {
        ahead(spread(100)-200);
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
    	if (!enemies.containsKey(e.getName())) {
    		Enemy en = new Enemy(e.getName(), e.getBearing(), e.getDistance(), e.getBearingRadians(), e.getVelocity(), e.getHeadingRadians());
    		enemies.put(e.getName(), en);
    	} else {
    		Enemy en = enemies.get(e.getName());
    		en.bearing = e.getBearing();
    		en.distance = e.getDistance();
    	}
    }

	private void fireUpon(final Enemy e) {
		if (getGunHeat()==0) {
			double gunBearing = Utils.normalRelativeAngleDegrees((getGunHeading()-getHeading()+360)%360);
			setTurnGunRight(Utils.normalRelativeAngleDegrees(e.bearing - gunBearing));
				
	    	/*double absoluteBearing = getHeadingRadians() + e.bearingRadians;
	    	setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - 
	    	    getGunHeadingRadians() + (e.velocity * Math.sin(e.headingRadians - 
	    	    absoluteBearing) / 13.0)));
	    	setFire(3.0);*/
				
				
			setFire(3);
		}
	}    
}

class Enemy {
	public Enemy(String name, double bearing, double distance, double bearingRadians, double velocity, double headingRadians) {
		this.name=name;
		this.bearing=bearing;
		this.distance=distance;
		this.bearingRadians=bearingRadians;
		this.velocity=velocity;
		this.headingRadians=headingRadians;
	}
	
	String name;
	double bearing;
	double distance;
	double bearingRadians;
	double velocity;
	double headingRadians;
	
}