package nl.ordina.nerdingout.round_2;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

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

        setTurnRight(bearing);

        if (e.getDistance() > stoppingDistance) {
            setAhead(50);
        }

        if (e.getDistance() < fireDistance) {
            setFire(3);
        }

    }

}
