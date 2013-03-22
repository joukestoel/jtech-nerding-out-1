package nl.ordina.nerdingout.round_1;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;

import java.awt.*;
import java.util.Random;

public class FeaMar extends AdvancedRobot {

    private Random random = new Random();


    // margers in de gaten houden
    // goTo

    @Override
    public void run() {
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while (true) {
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
        if (chance(0.5)) {

            turnRight(90 + e.getBearing());
        } else {
            turnRight(180);
            setAhead(50);
        }
    }

    @Override
    public void onHitRobot(final HitRobotEvent e) {
        turnRight(90 + e.getBearing());
    }

    @Override
    public void onHitByBullet(final HitByBulletEvent event) {
        ahead(spread(200));

    }

    private double avoidWall(double factor) {
        if (closeToWall()) {

        }
        return factor;
    }

    private boolean closeToWall() {
        final double y = getY();
        final double battleFieldHeight = getBattleFieldHeight();
        final double x = getX();
        final double battleFieldWidth = getBattleFieldWidth();
        final double close = 10;
        return x < close || y < close || battleFieldWidth - x < close || battleFieldHeight - y < close;
    }

    private boolean headedToWall() {
        return false;
    }
}
