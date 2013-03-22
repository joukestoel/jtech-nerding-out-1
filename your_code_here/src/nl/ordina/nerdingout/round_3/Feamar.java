package nl.ordina.nerdingout.round_3;

import robocode.*;

import java.awt.*;
import java.util.Random;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Feamar extends AdvancedRobot {

    @Override
    public void run() {
        setAdjustRadarForRobotTurn(true);
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        while(true) {
            turnRadarLeft(360);
        }

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        setTurnRight(event.getBearing());
        setFire(Math.min(250 / event.getDistance(), 3));
        setAhead(event.getDistance() - 50);
        turnRadarRight(normalRelativeAngleDegrees(((getHeading() - getRadarHeading()) + event.getBearing())));
    }



}
