package nl.ordina.nerdingout.round_3;

import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.Random;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Veamer extends AdvancedRobot {
    private Random random = new Random();
    private String nameToKill = "";

    @Override
    public void run() {
        setAdjustRadarForRobotTurn(true);
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);

        while (true) {
            turnRadarLeft(360);
            // start your engines for round 3!
        }
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
        final String name = e.getName();
        if (e.getDistance() < 100 && nameToKill.isEmpty()) {
            nameToKill = e.getName();
            System.out.println("Go get m!!!");
        }
        if (!nameToKill.isEmpty()) {
            setTurnRight(e.getBearing());
            setFire(Math.min(250 / e.getDistance(), 3));
            setAhead(e.getDistance() - 10);
            turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + e.getBearing())));
            return;
        }

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
    public void onHitWall(final HitWallEvent e) {
        setAhead(spread(50) - 100);

        if (chance(0.5)) {

            turnRight(90 + e.getBearing());
        } else {
            turnRight(180);
        }
    }

}
