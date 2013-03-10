package nl.ordina.nerdingout.example;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class ExampleBot extends AdvancedRobot {

    public void run() {
        while(true) {
            turnRadarLeft(360);

        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        if (event.getBearing() > 0) {
            turnRight(event.getBearing());
        }
        else {
            turnLeft(-event.getBearing());
        }

        setAhead(event.getDistance() / 2);

        fire(3);
    }
}
