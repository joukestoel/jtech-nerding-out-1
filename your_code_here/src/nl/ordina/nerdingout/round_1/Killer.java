package nl.ordina.nerdingout.round_1;

import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Killer extends AdvancedRobot {
    @Override
    public void run() {
        setBodyColor(Color.RED);
        setGunColor(Color.RED);
        setRadarColor(Color.RED);

        while (true) {
            turnRadarLeft(360);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());


        if (e.getDistance() > 100) {
            setTurnLeft(e.getBearing());

        }

        if (e.getEnergy() > 10) {
            setFire(2);
        }


    }

    @Override
    public void onHitWall(HitWallEvent e) {
        // put code to follow the wall here
        setTurnLeft(180);
        setAhead(5);
    }
}
