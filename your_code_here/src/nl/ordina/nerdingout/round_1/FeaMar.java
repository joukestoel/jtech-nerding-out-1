package nl.ordina.nerdingout.round_1;

import robocode.AdvancedRobot;

public class FeaMar extends AdvancedRobot {
    @Override
    public void run() {
        while(true) {
            // start your engines for round 1!
            scan();
            turnLeft(360);
            
        }
    }
}
