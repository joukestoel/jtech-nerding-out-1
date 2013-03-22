package nl.ordina.nerdingout.round_3;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.util.Random;


public class JaPas extends AdvancedRobot {
    private Random random = new Random();

    @Override
    public void run() {
		setColors(Color.BLUE, Color.BLUE, Color.WHITE);
		setScanColor(Color.ORANGE);
        while(true) {
    		turnRadarLeft(360);
        }
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
    public void onHitByBullet(HitByBulletEvent event) {
        if (chance(0.5)) {
            setTurnRight(90 + event.getBearing());
        } else {
            setTurnRight(180);
        }
    	
        ahead(100 - spread(50));
        
//    	super.onHitByBullet(event);
    }
    
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
    	setTurnRight(event.getBearing());
		setFire(Math.min(250 / event.getDistance(), 3));
       	setAhead(event.getDistance() - 10);
		turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + event.getBearing())));
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


}
