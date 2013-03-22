package nl.ordina.nerdingout.round_3;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import java.awt.*;
import java.util.Random;

import static robocode.util.Utils.*;

public class Veamer extends AdvancedRobot {
    private static final int DISTANCE = 100;
    private Random random = new Random();
    private volatile String nameToKill = "";

    @Override
    public void run() {
//        setAdjustRadarForRobotTurn(true);
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while (true) {
            turnGunRight(360);
            // start your engines for round 3!
        }
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
        System.out.println("e.getDistance() = " + e.getDistance());
        System.out.println("e.getName() = " + e.getName());
        if (e.getDistance() < DISTANCE && nameToKill.isEmpty()) {
            nameToKill = e.getName();
            System.out.println("Go get m!!!");
        }
        if (!nameToKill.isEmpty()) {
            System.out.println("kill someone");
            setTurnRight(e.getBearing());
            setFire(Math.min(250 / e.getDistance(), 3));
            setAhead(e.getDistance() - 10);
            turnGunRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + e.getBearing())));
            return;
        }

        // start your engines for round 1!
        System.out.println("randomness");
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

    private boolean chance(final double chance) {
        return random.nextDouble() <= chance;
    }

    private double spread(final int degrees) {
        return spreadFactor() * degrees;
    }

    private double spreadFactor() {
        return (random.nextDouble() * 2) - 1;
    }

    @Override
    public void onHitByBullet(final HitByBulletEvent event) {
        System.out.println("get away");
        nameToKill = "";
        turnLeft(90);
        back(200);
    }

    @Override
    public void onHitWall(final HitWallEvent e) {
        setAhead(spread(50) - 100);

        if (chance(0.5)) {

            turnRight(90 + e.getBearing());
        } else {
            turnRight(180);
        }
    }

    public void onWin(WinEvent e) {
   		// Victory dance
   		turnRight(36000);
        setBodyColor(Color.ORANGE);
   	}


}
