//******************************************************************* 
//David Hand
//November 22, 2011
//CSC 250-01: Accelerated Computer Science I & II
//Peter DePasquale
//******************************************************************* 


//*******************************************************************
//Driver.java
//Creates a simulator object to represent a traffic intersection.
//Calls the simulator's simulate method to run the traffic
//simulation.
//*******************************************************************

public class Driver
{
	//-------------------------------------------------------------------
	//main:  Instantiates an object of class Simulator.  Calls the 
	//object's simulate method to run a simulation of a 4-way
	//intersection.
	//-------------------------------------------------------------------
	public static void main (String[] args)
	{
		//create Simulator object
		Simulator simulator = new Simulator ();  
		
		//call simulate method to begin traffic simulation
		simulator.simulate(); 
	}	
}			