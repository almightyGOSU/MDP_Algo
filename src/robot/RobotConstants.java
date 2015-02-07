package robot;

import java.awt.Color;

public final class RobotConstants {
	
	// Robot size
	public static final int ROBOT_SIZE = 2;
	
	// Sensors default range (In grids)
	public static final int SHORT_IR_MIN = 1;
	public static final int SHORT_IR_MAX = 8;
	
	public static final int LONG_IR_MIN = 2;
	public static final int LONG_IR_MAX = 15;
	
	public static enum DIRECTION {
		NORTH, EAST, SOUTH, WEST;

		public static DIRECTION getNext(DIRECTION currDirection) {
			return values()[(currDirection.ordinal() + 1) % values().length];
		}
		
		public static DIRECTION getPrevious(DIRECTION currDirection) {
			return values()[(currDirection.ordinal() + values().length - 1)
					% values().length];
		}
	};
	
	// Might not need to  be stored in RobotConstants
	// Colors for rendering the map
	public static final Color C_BORDER = Color.BLACK;
	
	public static final Color C_LINE = Color.ORANGE;
	public static final int LINE_WEIGHT = 2;
	
	public static final Color C_START = Color.BLUE;
	public static final Color C_GOAL = Color.GREEN;
	
	public static final Color C_UNEXPLORED = Color.LIGHT_GRAY;
	public static final Color C_FREE = Color.WHITE;
	public static final Color C_OBSTACLE = Color.DARK_GRAY;

	public static final Color C_ROBOT_OUTLINE = new Color(0, 0, 0, 160);
	public static final Color C_ROBOT = new Color(0, 205, 255, 100);
	public static final Color C_ROBOT_FRONT = new Color(0, 46, 155, 220);
	public static final Color C_PATH = Color.RED;
	
	
	// Prevent instantiation
	private RobotConstants() {}
}
