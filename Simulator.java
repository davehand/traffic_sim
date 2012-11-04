//******************************************************************* 
//David Hand
//November 22, 2011
//CSC 250-01: Accelerated Computer Science I & II
//Peter DePasquale
//******************************************************************* 

//import various classes from Java, as well as javafoundations
import javafoundations.*;
import javafoundations.exceptions.*;
import java.io.*;
import java.text.NumberFormat;

//*******************************************************************
//Simulator.java
//Performs a traffic simulation for Driver.java.  Contains the
//simulate method, which creates four Linked Queues in order to
//store vehicles, and processes the traffic until the maximum 
//number of vehicles have passed through the intersection.
//*******************************************************************

public class Simulator
{
	//four linked queues, one for each way at the intersection
	private LinkedQueue<Vehicle> north_Church_Traffic = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> south_Church_Traffic = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> west_Main_Traffic = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> east_Main_Traffic = new LinkedQueue<Vehicle>();

	//new EmptyCollectionException
	private EmptyCollectionException excep = new EmptyCollectionException ("Queue is empty");
	
	//vehicle number, with the first vehicle being number 1
	private int vehicleNumber = 1;
	
	//clock to increment during simulation, counter for incremental purposes, waitTime for calculations
	int clock = 0, count = 0, waitTime;
	
	//street of the vehicle
	private String street;
	
	//direction of the vehicle
	private char direction;
	
	//number of cars to create for simulation
	private final int VEHICLEMAX = 100;
	
	
	//-------------------------------------------------------------------
	//simulate: Pre-populates the intersection by randomly creating 5-10 
	//vehicles and placing them in the correct Linked Queue.  Then, a 
	//cycle begins.  First, the Church street traffic is processed.  The
	//clock is incremented, then one vehicle going each direction on 
	//Church street is allowed through the intersection.  This process
	//is repeated one more time, allowing for a maximum of four cars,
	//two in each direction, to pass through the intersection.  Once 
	//processing is completed, the intersection is re-populated by 
	//randomly creating 5-12 cars and placing them in the correct Linked 
	//Queue.  Next, traffic on Main street is processed.  Processing is
	//the same as Church street, except that a maximum of three cars
	//for each direction may move through the intersection.  After 
	//processing, the intersection is re-populated again by randomly 
	//creating 3-15 cars and placing them in the correct Linked Queue.  
	//This cycle continues until the maximum number of cars, declared 
	//above, is created and processed through the intersection.  As 
	//processing occurs, the program writes the results to a text file.
	//-------------------------------------------------------------------
	public void simulate ()
	{
		//Pre-populate the intersection at time 0 with the random creation of 5-10 vehicles
		//Place the randomly created vehicle into the correct Linked Queue
		for (int i = 1; i <= ((int) (Math.random() * 6 + 5)); i++)
		{
			Vehicle vehicle = new Vehicle (vehicleNumber, clock);
			street = vehicle.getStreet();
			direction = vehicle.getDirection();
		
			if (street.equals("Church"))
			{
				if (direction == 'n')
					north_Church_Traffic.enqueue(vehicle);
				else
					south_Church_Traffic.enqueue(vehicle);
			}
			else
			{
				if (direction == 'w')
					west_Main_Traffic.enqueue(vehicle);
				else
					east_Main_Traffic.enqueue(vehicle);
			}
			
			//increment vehicleNumber
			vehicleNumber++;
		}
		
		try
		{
		
		//creates an output file for printing the results
		FileWriter writer = new FileWriter("output.txt");
	
		//creates a format to be used for the clock
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setMinimumIntegerDigits(2);
		
		
		//Begin processing cycle
		while ((!north_Church_Traffic.isEmpty()) || (!south_Church_Traffic.isEmpty()) || 
					(!west_Main_Traffic.isEmpty()) || (!east_Main_Traffic.isEmpty()))
		{
		
			//Print to mark beginning of North-South traffic processing
			writer.write ("---Light changed.  Now processing north/south-bound traffic---\n");
			
			//Processes two vehicles in each direction if vehicle exists
			while (count < 2)
			{
				//increments the clock
				clock = clock + 3;
			
				//if North Queue is not empty, removes vehicle at front of line
				//sets vehicle departure time to the clock's value
				//prints vehicle removal to text file
				//catches exception if Queue is empty
				if (!north_Church_Traffic.isEmpty())
				{
					
					try
					{
						Vehicle vehicle = north_Church_Traffic.dequeue();
						vehicle.setDepartureTime(clock);
						waitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
			
						writer.write ("[Time " + fmt.format(clock) + "] Vehicle #" + vehicle.getVehicleNumber() + " (northbound) " +
							"moved through intersection.  Total wait time " + waitTime + " seconds.\n");
					}
					catch (EmptyCollectionException exception)
					{
						System.out.println ("Queue is empty.");
					}
				}
			
				//if South Queue is not empty, removes vehicle at front of line
				//sets vehicle departure time to the clock's value
				//prints vehicle removal to text file
				//catches exception if Queue is empty
				if (!south_Church_Traffic.isEmpty())
				{
				
					try
					{
						Vehicle vehicle = south_Church_Traffic.dequeue();
						vehicle.setDepartureTime(clock);
						waitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
						writer.write ("[Time " + fmt.format(clock) + "] Vehicle #" + vehicle.getVehicleNumber() + " (southbound) " +
							"moved through intersection.  Total wait time " + waitTime + " seconds.\n");
					}
					catch (EmptyCollectionException exception)
					{
						System.out.println ("Queue is empty.");
					}
				}
				
				//increment count
				count++;
			
			}
			
			writer.write ("\n");
			
			//reset count
			count = 0;
		

			//Re-populate the intersection with the random creation of 5-12 vehicles
			//Stops creating vehicles if vehicle max is reached
			//Place the randomly created vehicle into the correct queue
			for (int i = 1; i <= ((int) (Math.random() * 8 + 5)); i++)
			{
				if (vehicleNumber <= VEHICLEMAX)
				{
					Vehicle vehicle = new Vehicle (vehicleNumber, clock);
					street = vehicle.getStreet();
					direction = vehicle.getDirection();
		
					if (street.equals("Church"))
					{
						if (direction == 'n')
							north_Church_Traffic.enqueue(vehicle);
						else
							south_Church_Traffic.enqueue(vehicle);
					}
					else
					{
						if (direction == 'w')
							west_Main_Traffic.enqueue(vehicle);
						else
							east_Main_Traffic.enqueue(vehicle);
					}
					
					//increment vehicleNumber
					vehicleNumber++;
				}
			}

		
			//Print to mark beginning of East-West traffic processing
			writer.write ("---Light changed.  Now processing east/west-bound traffic---\n");
			
			//Processes three vehicles in each direction if vehicle exists
			while (count < 3)
			{
				//increments the clock
				clock = clock + 3;
	
				//if East Queue is not empty, removes vehicle at front of line
				//sets vehicle departure time to the clock's value
				//prints vehicle removal to text file
				//catches exception if Queue is empty
				if (!east_Main_Traffic.isEmpty())
				{
				
					try
					{
						Vehicle vehicle = east_Main_Traffic.dequeue();
						vehicle.setDepartureTime(clock);
						waitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
						writer.write ("[Time " + fmt.format(clock) + "] Vehicle #" + vehicle.getVehicleNumber() + " (eastbound) " +
							"turned right and headed southbound.  Total wait time " + waitTime + " seconds.\n");
					}
					catch (EmptyCollectionException exception)
					{
						System.out.println ("Queue is empty.");
					}
				}
				
				//if West Queue is not empty, removes vehicle at front of line
				//sets vehicle departure time to the clock's value
				//prints vehicle removal to text file
				//catches exception if Queue is empty
				if (!west_Main_Traffic.isEmpty())
				{
				
					try
					{
						Vehicle vehicle = west_Main_Traffic.dequeue();
						vehicle.setDepartureTime(clock);
						waitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
		
						writer.write ("[Time " + fmt.format(clock) + "] Vehicle #" + vehicle.getVehicleNumber() + " (westbound) " +
							"turned right and headed northbound.  Total wait time " + waitTime + " seconds.\n");
					}
					catch (EmptyCollectionException exception)
					{
						System.out.println ("Queue is empty.");
					}
				}
				
				//increment count
				count++;
			
			}
			
			writer.write ("\n");
			
			//reset count
			count = 0;
			
		
		
			//Re-populate the intersection with the random creation of 3-15 vehicles
			//Stops creating vehicles if vehicle max is reached
			//Place the randomly created vehicle into the correct queue
			for (int i = 1; i <= ((int) (Math.random() * 13 + 3)); i++)
			{
				if (vehicleNumber <= VEHICLEMAX)
				{
					Vehicle vehicle = new Vehicle (vehicleNumber, clock);
					street = vehicle.getStreet();
					direction = vehicle.getDirection();
		
					if (street.equals("Church"))
					{
						if (direction == 'n')
							north_Church_Traffic.enqueue(vehicle);
						else
							south_Church_Traffic.enqueue(vehicle);
					}
					else
					{
						if (direction == 'w')
							west_Main_Traffic.enqueue(vehicle);
						else
							east_Main_Traffic.enqueue(vehicle);
					}
					
					//increment vehicleNumber
					vehicleNumber++;
				}
			}
		}
		
	
		//close the connection to text file
		writer.close();
		
		//output simulation complete
		System.out.println ("Simulation Complete");
		
		
		}
		//catches exception if cannot generate or write to file
		catch (IOException exception)
		{
			System.out.println ("Could not generate output file.  Program aborted.");
			System.exit(1);
		}
	}	
}




	