package nl.ordina.nerdingout.round_2;

import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

public class FourtyTwo extends AdvancedRobot {
    private double haasDistance = 0.0;
    private double haasBearing = 0.0;

    @Override
    public void run() {
        setColors(Color.BLACK, Color.BLACK, Color.WHITE);
        setScanColor(Color.ORANGE);
        while (true) {
//            ahead(100);
            turnRadarLeft(360);

        }
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
        final double newHaasDistance = e.getDistance();
        final double newHaasBearing = e.getBearing();
        final double deltaDistance = calcDeltaDistance(haasDistance, newHaasDistance);
//        System.out.println("deltaDistance = " + deltaDistance);
        final double deltaBearing = calcDeltaBearing(haasBearing, newHaasBearing);

        setTurnRight(e.getBearing());
        setTurnRadarRight(relativeRadarBearing(e));
        setTurnGunRight(relativeGunBearing(e) + (newHaasDistance * deltaBearing * calcGunFactor(deltaDistance)));
//        setTurnRight(e.getBearing() + (newHaasDistance * deltaBearing * calcDriveFactor(deltaDistance)));


//        System.out.println("getGunHeat() = " + getGunHeat());

        doOurFire(newHaasDistance);
        haasDistance = newHaasDistance;
        haasBearing = newHaasBearing;
//        ahead(newHaasDistance);
    }


    private double fieldMaxDist() {
        final double x = getBattleFieldWidth();
        final double y = getBattleFieldHeight();
        return Math.sqrt(x * x + y * y);
    }

    private void doOurFire(final double newHaasDistance) {
        final double power = 3.0 - 2.9 * (newHaasDistance / fieldMaxDist());
//        System.out.println("power = " + power);
        fire(power);
    }

    private double calcGunFactor(final double deltaDistance) {
        final double v = (10 + deltaDistance) / 20.0 * 0.2;
//        System.out.println("v = " + v);
        return v;
    }

    private double calcDriveFactor(final double deltaDistance) {
        final double v = (10 + deltaDistance) / 20.0 * 0.4;
//        System.out.println("v = " + v);
        return v;
    }

    private double calcDeltaBearing(final double haasBearing, final double newHaasBearing) {
        return newHaasBearing - haasBearing;
    }

    private double calcDeltaDistance(final double haasDistance, final double newHaasDistance) {
        return newHaasDistance - haasDistance;
    }

    private double relativeRadarBearing(final ScannedRobotEvent e) {
        return Utils.normalRelativeAngleDegrees((getHeading() - getRadarHeading()) + e.getBearing());
    }

    private double relativeGunBearing(final ScannedRobotEvent e) {
        return Utils.normalRelativeAngleDegrees((getHeading() - getGunHeading()) + e.getBearing());
    }

    @Override
    public void onHitWall(final HitWallEvent event) {
        setAhead(-100);
    }

/*
    newHaasBearing = -177.98862248792454
    newHaasDistance = 337.9768390020706
    haasDistance = 343.00796959872713
    haasBearing = -179.03531638462854
     */


}
