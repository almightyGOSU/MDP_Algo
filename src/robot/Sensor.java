package robot;

import map.Grid;
import map.MapConstants;
import map.RealMap;
import robot.RobotConstants.DIRECTION;

public class Sensor {
	
	// Range (In grids)
	private int _minRange;
	private int _maxRange;
	
	// Sensor's position on the map (In grids)
	private int _sensorPosRow;
	private int _sensorPosCol;
	
	// Sensor's current direction
	private DIRECTION _sensorDirection;
	
	public Sensor(int minRange, int maxRange, int sensorPosRow, int sensorPosCol, DIRECTION sensorDirection) {
		_minRange = minRange;
		_maxRange = maxRange;
		
		_sensorPosRow = sensorPosRow;
		_sensorPosCol = sensorPosCol;
		
		_sensorDirection = sensorDirection;
	}
	
	public int getMinRange() {
		return _minRange;
	}
	
	public int getMaxRange() {
		return _maxRange;
	}
	
	public void setMinRange(int newMinRange) {
		_minRange = newMinRange;
	}
	
	public void setMaxRange(int newMaxRange) {
		_maxRange = newMaxRange;
	}
	
	public int getSensorPosRow() {
		return _sensorPosRow;
	}

	public int getSensorPosCol() {
		return _sensorPosCol;
	}
	
	/**
	 * Update the sensor's position on the map (In grids)<p>
	 * 
	 * This function should be invoked whenever the robot moves, or
	 * when the robot turns.<br>Be fully aware of sensor's position relative to the
	 * robot's position at all times!
	 * 
	 * @param newSensorPosRow Sensor's new row on the map
	 * @param newSensorPosCol Sensor's new column on the map
	 */
	public void updateSensorPos(int newSensorPosRow, int newSensorPosCol) {
		_sensorPosRow = newSensorPosRow;
		_sensorPosCol = newSensorPosCol;
	}
	
	public DIRECTION getSensorDirection() {
		return _sensorDirection;
	}
	
	/**
	 * Update the sensor's direction relative to the robot<p>
	 * 
	 * This function should be invoked whenever the robot moves, or
	 * when the robot turns.<br>Be fully aware of sensor's direction relative to the
	 * robot's direction at all times!
	 * 
	 * @param newDirection Sensor's new direction
	 */
	public void updateSensorDirection(DIRECTION newDirection) {
		_sensorDirection = newDirection;
	}

	/**
	 * Most important function of the sensor class
	 * 
	 * @return
	 */
	public int sense(final RealMap realMap) {
		
		final Grid [][] realMapGrids = realMap.getMapGrids();
		
		for (int currGrid = _minRange; currGrid <= _maxRange; currGrid++) {
			switch (_sensorDirection) {
			
			case NORTH:
				//System.out.println("Checking " + (_sensorPosRow - currGrid) + ", " + _sensorPosCol + ".. ");
				// Reached top limit of map without detecting any obstacle
				if((_sensorPosRow - currGrid) < 0)
					return currGrid;
				else if(realMapGrids[_sensorPosRow - currGrid][_sensorPosCol].isObstacle())
					return currGrid - 1; // Return number of free grids for this direction
				break;

			case SOUTH:
				//System.out.println("Checking " + (_sensorPosRow + currGrid) + ", " + _sensorPosCol + ".. ");
				// Reached bottom limit of map without detecting any obstacle
				if((_sensorPosRow + currGrid) > (MapConstants.MAP_ROWS - 1))
					return currGrid;
				else if(realMapGrids[_sensorPosRow + currGrid][_sensorPosCol].isObstacle())
					return currGrid - 1; // Return number of free grids for this direction
				break;
				
			case EAST:
				//System.out.println("Checking " + _sensorPosRow + ", " + (_sensorPosCol + currGrid) + ".. ");
				// Reached right limit of map without detecting any obstacle
				if((_sensorPosCol + currGrid) > (MapConstants.MAP_COLS - 1))
					return currGrid;
				else if(realMapGrids[_sensorPosRow][_sensorPosCol + currGrid].isObstacle())
					return currGrid - 1; // Return number of free grids for this direction
				break;

			case WEST:
				//System.out.println("Checking " + _sensorPosRow + ", " + (_sensorPosCol - currGrid) + ".. ");
				// Reached left limit of map without detecting any obstacle
				if((_sensorPosCol - currGrid) < 0)
					return currGrid;
				else if(realMapGrids[_sensorPosRow][_sensorPosCol - currGrid].isObstacle())
					return currGrid - 1; // Return number of free grids for this direction
				break;
				
			} // End switch
		} // End for loop
		
		// No obstacles detected within the sensor's maximum range
		// Allow the robot to mark those grids as free
		return _maxRange;
	}
	
}
