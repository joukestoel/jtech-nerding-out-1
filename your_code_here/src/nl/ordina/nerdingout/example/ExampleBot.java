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
        double bearing = event.getBearing();
        double heading = event.getHeading();
        if (bearing > 0) {
            turnRight(bearing);
        }
        else {
            turnLeft(-bearing);
        }
        System.out.println(bearing);
        setAhead(event.getDistance() / 2);
        fire(3);
    }
}
