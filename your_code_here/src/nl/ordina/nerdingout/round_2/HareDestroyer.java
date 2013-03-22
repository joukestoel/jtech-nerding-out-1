package nl.ordina.nerdingout.round_2;

import robocode.AdvancedRobot;

import java.awt.*;

public class HareDestroyer extends AdvancedRobot {


    public void run() {
        setBodyColor(Color.RED);
        setGunColor(Color.RED);
        setRadarColor(Color.RED);

        while (true) {
            turnRadarLeft(360);
        }
    }
}
