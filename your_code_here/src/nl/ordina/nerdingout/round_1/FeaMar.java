package nl.ordina.nerdingout.round_1;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;

import java.util.Random;

public class FeaMar extends AdvancedRobot {

    private Random random = new Random();



    // margers in de gaten houden
    // goTo

    @Override
    public void run() {
        while (true) {
            // start your engines for round 1!
            if (random.nextDouble() <= 0.5) {
                setAhead(getLeftOrRight(100));
            } else {
                setAhead(100);
            }
            turnRight(getLeftOrRight(50));
            if (random.nextDouble() <0.3) {
                setTurnLeft(180);
            }

        }
    }

    private double getLeftOrRight(int degrees) {
        return getLeftOrRightFactor() * degrees;
    }

    private double getLeftOrRightFactor() {
        return (random.nextDouble() * 2 - 1);
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        turnRight(90 + e.getBearing());
    }

    @Override
    public void onHitRobot(final HitRobotEvent e) {
        turnRight(90 + e.getBearing());
    }



//    private double avoidWall(double factor) {
//        final double y = getY();
//        final double battleFieldHeight = getBattleFieldHeight();
//        final double x = getX();
//        final double battleFieldWidth = getBattleFieldWidth();
//        if (battleFieldWidth - x <) {
//        }


//    }
}
