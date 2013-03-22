package nl.ordina.nerdingout.round_2;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;


public class ElmerFudd extends AdvancedRobot {
	Color brown = new Color(0xd2691e);
	
	@Override
	public void run() {
		setColors(brown, Color.GRAY, brown);
		while (true)  {
			turnRadarLeft(360);
		}
	}

}
