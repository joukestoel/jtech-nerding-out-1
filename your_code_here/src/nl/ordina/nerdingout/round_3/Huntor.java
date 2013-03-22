package nl.ordina.nerdingout.round_3;

import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

public class Huntor extends AdvancedRobot {
    private int factor = 1;

    @Override
    public void run() {
        setColors(Color.CYAN, Color.WHITE, Color.YELLOW);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        while(true) {
            turnRadarLeft(360);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
            theHuntIsOn(event);
    }

    public void theHuntIsOn(ScannedRobotEvent event) {

        int distanceLimit = 250;
        if (event.getDistance() > distanceLimit) {
            setTurnRight(event.getBearing());
            setAhead(Math.max(event.getDistance(), distanceLimit));
        } else {
            setTurnRight(event.getBearing() - 90);
            setAhead(100 * factor);
            setTurnGunTowardsOpponent(event.getBearing());
            setFire(Math.min(1.5, getEnergy()));
        }

        setTurnRadarRight(getHeading() - getRadarHeading() + event.getBearing());
    }

    private void setTurnGunTowardsOpponent(double opponentsBearing) {
        double degreesToTurnGun = getDegreesBetweenGunAndOpponentBearing(getHeading(), getGunHeading(), opponentsBearing);

        if (degreesToTurnGun < 0) {
            setTurnGunLeft(Math.abs(degreesToTurnGun));
        } else {
            setTurnGunRight(degreesToTurnGun);
        }
    }

    double getDegreesBetweenGunAndOpponentBearing(double ownHeading, double ownGunHeading, double opponentsBearing) {
        double opponentsHeading = fromBearingToDegrees(ownHeading, opponentsBearing);

        double degreesInBetween = opponentsHeading - ownGunHeading;
        if (degreesInBetween < -180) {
            degreesInBetween = 360 + degreesInBetween;
        } else if (degreesInBetween > 180) {
            degreesInBetween = 360 - degreesInBetween;
        }

        return degreesInBetween;
    }

    double fromBearingToDegrees(double ownHeading, double bearing) {
        double heading = ownHeading + bearing;

        if (heading < 0) {
            heading = heading + 360;
        } else if (heading > 360) {
            heading = heading - 360;
        }

        return heading;
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        factor = -factor;
    }
}
