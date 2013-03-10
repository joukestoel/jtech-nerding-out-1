package nl.ordina.nerdingout.example;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class ExampleBot extends AdvancedRobot {

    public void run() {
        while(true) {
        	setBodyColor(Color.BLACK);
        	turnRadarLeft(360);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        setBodyColor(Color.WHITE);

        if (event.getBearing() > 0) {
            setTurnRight(event.getBearing());
        }
        else {
            setTurnLeft(-event.getBearing());
        }

        setAhead((event.getDistance() / 2) - 50);

        fire(3);
    }
}
