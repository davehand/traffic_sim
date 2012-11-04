//******************************************************************* 
//David Hand
//November 22, 2011
//CSC 250-01: Accelerated Computer Science I & II
//Peter DePasquale
//******************************************************************* 


//*******************************************************************
//Vehicle.java
//Represents the vehicle number, street the vehicle is placed on,
//direction the vehicle is heading, time the vehicle arrived at the
//intersection, and time the vehicle exited the intersection.
//*******************************************************************
public class Vehicle
{
	//number, arrival time, and departure time of the vehicle
	private int vehicleNumber, arrivalTime, departureTime;
	
	//street the vehicle is on
	private String street;
	
	//direction the vehicle is traveling
	private char direction;
	
	//---------------------------------------------------------------
	//Constructor: Creates vehicle object with vehicle number and
	//arrival time passed in.  Departure time is set to zero for
	//every vehicle.  Street and direction are randomly determined
	//for each vehicle.
	//
	//@param	vehicleNumber	Integer of vehicle's number
	//@param	arrivalTime		Integer of vehicle's arrival time
	//---------------------------------------------------------------	
	public Vehicle (int vehicleNumber, int arrivalTime)
	{
		this.vehicleNumber = vehicleNumber;
		this.arrivalTime = arrivalTime;
		departureTime = 0;
		
		//creates two random numbers
		int random1 = (int) (Math.random() * 2 + 1);
		int random2 = (int) (Math.random() * 2 + 1);
		
		//determines the street and direction with random numbers
		if (random1 == 1)
		{
			street = "Church";
			if (random2 == 1)
				direction = 'n';
			else
				direction = 's';
		}
		else
		{
			street = "Main";
			if (random2 == 1)
				direction = 'w';
			else
				direction = 'e';
		}
	}
	
	//---------------------------------------------------------------
	//Accessor for vehicle number.
	//
	//@return the vehicle number
	//---------------------------------------------------------------
	public int getVehicleNumber ()
	{
		return vehicleNumber;
	}
	
	//---------------------------------------------------------------
	//Accessor for street.
	//
	//@return the street
	//---------------------------------------------------------------
	public String getStreet ()
	{
		return street;
	}
	
	//---------------------------------------------------------------
	//Accessor for direction.
	//
	//@return the direction
	//---------------------------------------------------------------
	public char getDirection ()
	{
		return direction;
	}
	
	//---------------------------------------------------------------
	//Accessor for arrival time.
	//
	//@return the arrival time
	//---------------------------------------------------------------
	public int getArrivalTime ()
	{
		return arrivalTime;
	}
	
	//---------------------------------------------------------------
	//Accessor for departure time.
	//
	//@return the departure time
	//---------------------------------------------------------------
	public int getDepartureTime ()
	{
		return departureTime;
	}
	
	//---------------------------------------------------------------
	//Mutator for vehicle number.  Vehicle number is not modified if
	//value is not valid.
	//
	//@param	vehicleNumber2	Integer of the new vehicle number
	//---------------------------------------------------------------
	public void setVehicleNumber (int vehicleNumber2)
	{
		if (vehicleNumber2 > 0)
			vehicleNumber = vehicleNumber2;
	}
	
	//---------------------------------------------------------------
	//Mutator for street.  Street is not modified if value is not
	//valid.
	//
	//@param	street2	String of the new street
	//---------------------------------------------------------------
	public void setStreet (String street2)
	{
		if (street2.equals("Church") || street2.equals("Main"))
			street = street2;
	}
	
	//---------------------------------------------------------------
	//Mutator for direction.  Direction is not modified if value is
	//not valid.
	//
	//@param	direction2	Char of the new direction
	//---------------------------------------------------------------
	public void setDirection (char direction2)
	{
		if (direction2 == 'n' || direction2 == 'w' || direction2 == 'e' || direction2 == 's')
			direction = direction2;
	}
	
	//---------------------------------------------------------------
	//Mutator for arrival time.  Arrival time is not modified if 
	//value is not valid.
	//
	//@param	arrivalTime2	Integer of the new arrival time
	//---------------------------------------------------------------
	public void setArrivalTime (int arrivalTime2)
	{
		if (arrivalTime2 > 0)
			arrivalTime = arrivalTime2;
	}
	
	//---------------------------------------------------------------
	//Mutator for departure time.  Departure time is not modified if 
	//value is not valid.
	//
	//@param	departureTime2	Integer of the new departure time
	//---------------------------------------------------------------
	public void setDepartureTime (int departureTime2)
	{
		if (departureTime2 > 0)
			departureTime = departureTime2;
	}
	
	//---------------------------------------------------------------
	//Returns a string representation of the vehicle.
	//
	//@return	String representation of vehicle
	//---------------------------------------------------------------
	public String toString ()
	{
		return ("Vehicle Number: " + vehicleNumber + "\nStreet: " + street + "\nDirection: " + 
			direction + "\nArrival Time: " + arrivalTime + "\nDeparture Time: " + departureTime);
	}
	
}