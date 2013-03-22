package nl.ordina.nerdingout.round_3;

import robocode.*;

import java.awt.*;
import java.util.Random;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Feamar extends AdvancedRobot {
    private Random random = new Random();

    @Override
    public void run() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while (true) {
            setTurnRadarLeft(360);
            move();
            turn();
        }
    }

    private void turn() {
        if (chance(0.9)) {
            turnRight(spread(50));
        } else {
            turnRight(180);
        }
    }

    private void move() {
        if (chance(0.1)) {
            setAhead(spread(100));
        } else {
            setAhead(100);
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
        setAhead(spread(50)-100);
        turnRight(90 + e.getBearing());
    }

    @Override
    public void onHitByBullet(final HitByBulletEvent e) {
        ahead(spread(100)-200);
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent event) {
        setTurnGunRight(event.getBearing());
        if (event.getDistance() < 300) {
            setFire(Math.min(100 / event.getDistance(), 3));
        }
        setAhead(event.getDistance() - 10);
        turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + event.getBearing())));
    }
}
