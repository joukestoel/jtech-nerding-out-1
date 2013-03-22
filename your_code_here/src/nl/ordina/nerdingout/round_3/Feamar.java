package nl.ordina.nerdingout.round_3;

import robocode.*;

import java.awt.*;
import java.util.Random;

public class Feamar extends AdvancedRobot {

    private Random random = new Random();

    @Override
    public void run() {
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while (true) {
            setTurnRadarLeft(360);
            // start your engines for round 1!
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
    public void onScannedRobot(final ScannedRobotEvent e) {

    }


}
