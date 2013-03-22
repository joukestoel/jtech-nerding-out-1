package nl.ordina.nerdingout.round_2;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class HareDestroyer extends AdvancedRobot {

    private double fireDistance = 300;
    private double stoppingDistance = 100;


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


        double bearing = e.getBearing();
        //double angle = normalRelativeAngleDegrees((getHeading() - getRadarHeading()) + bearing);
        setTurnRight(bearing);


        if(e.getDistance() > stoppingDistance) {
            setAhead(50);
        }

        if(e.getDistance() < fireDistance){
            setFire(3);
        }



    }

}
