package nl.ordina.nerdingout.round_1;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Killer extends AdvancedRobot {

    private static final String EVIL = "Shadow 3.84";
    private static final int margin = 10;


    @Override
    public void run() {
        setBodyColor(Color.RED);
        setGunColor(Color.RED);
        setRadarColor(Color.RED);
        double direction = 1L;

        double battleFieldWidth = getBattleFieldWidth();
        double battleFieldHeight = getBattleFieldHeight();

        double width = getWidth();
        double height = getHeight();

        double maxX = battleFieldWidth - width - margin;
        double maxY = battleFieldHeight - height - margin;

        double minX = width + margin;
        double minY = height = margin;

        int counter = 0;
        while (true) {


            counter++;
            turnRadarLeft(360);
            setAhead(999999);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


            // checkBoundries(maxX, maxY, minX, minY, margin, width, height);


            setTurnRight(180 * direction);


            if (counter > 4) {

                counter = 0;
                direction = direction * -1;
            }


        }
    }

    private boolean checkBoundries(double maxX, double maxY, double minX, double minY, int margin, double width, double height) {

        double y = getY();
        double x = getX();


        if (x < minX || x > maxY) {
            setTurnLeft(180);
            return true;
        }

        if (y < minY || y > maxY) {
            setTurnRight(180);
            return true;
        }
        return false;
    }


    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        //setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());


    }

    @Override
    public void onHitWall(HitWallEvent e) {
        // put code to follow the wall here
        setTurnRight(90);
        // setAhead(5000);
    }

    @Override
    public void onHitRobot(HitRobotEvent e) {
        setTurnRight(90);
        //   setAhead(5000);


    }


}
