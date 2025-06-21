package parkingLot;
import java.util.*;
// Create the parking lot.
// Add floors to the parking lot.
// Add a parking lot slot to any of the floors.
// Given a vehicle, it finds the first available slot, books it, creates a ticket, parks the vehicle, and finally returns the ticket.
// Unparks a vehicle given the ticket id.
// Displays the number of free slots per floor for a specific vehicle type.
// Displays all the free slots per floor for a specific vehicle type.
// Displays all the occupied slots per floor for a specific vehicle type.
// first slot truck 2 3  bike 4 ownwards car
class Vehicle{
    String colour;
    VehicleType type;
    Integer registrationNumber;
}
enum VehicleType
{
    TRUCK, CAR, BIKE
}
class Floor{
    List<Slot>slots;
    boolean isFull;
}
class Slot{
    Integer id;
    Vehicle vehicle;
    VehicleType type;
    Boolean isBooked;
}
class ticket{
    static Integer ticketId = 0;
    Integer ParkingLotId;
    Integer floor;
    Integer slot;
    ticket(Integer ParkingLotId,Integer floor, Integer slot)
    {
        this.ParkingLotId = ParkingLotId;
        this.floor = floor;
        this.slot = slot;
        this.ticketId++;
    }
    Integer getTicketId()
    {
        return ticketId;
    }
}
class ParkingLot{
    Integer id;
    List<Floor>floors;
    Integer no_of_floors;
    Integer no_of_slots_per_floor;
    ParkingLot(Integer id, Integer floors, Integer no_of_slots_per_floor )
    {
        this.id = id;
        this.no_of_floors = floors;
        this.no_of_slots_per_floor = no_of_slots_per_floor;
    }
}
class ParkingLotService
{
    //create_parking_lot <parking_lot_id> <no_of_floors> <no_of_slots_per_floor>
    Map<Integer,ParkingLot>mpParkingLot;
    ParkingLot parkingLot;
    Map<Vehicle,ticket>vehicleTicket;
    Map<Integer,Vehicle>mapVehicle;
    ParkingLotService()
    {
        mpParkingLot = new HashMap<>();
    }
    void create_parking_lot(Integer parking_lot_id, Integer floors, Integer no_of_slots_per_floor)
    {
        parkingLot = new ParkingLot(parking_lot_id, floors, no_of_slots_per_floor);
    }
    //vehicle_type> <reg_no> <color

    Integer parkVehicle(Vehicle vehicle, Integer regNo, String color) throws Exception{
        int numberOfFloors = parkingLot.no_of_floors;
        for(int i = 1;i<=numberOfFloors;i++)
        {
            if(!(parkingLot.floors.get(i).isFull))
            {
                for(int j = 1;j<=parkingLot.no_of_slots_per_floor;j++)
                {
                   if(j==1 && vehicle.type == VehicleType.TRUCK )
                   {
                        if(!(parkingLot.floors.get(i).slots.get(j).isBooked))
                        {
                            parkingLot.floors.get(i).slots.get(j).isBooked= true;
                            ticket t = new ticket(0,i,j);
                            return t.getTicketId();
                        }
                    }else if((j==2 || j == 3) && vehicle.type == VehicleType.BIKE)
                   {
                        parkingLot.floors.get(i).slots.get(j).isBooked= true;
                        ticket t = new ticket(0,i,j);
                        return t.getTicketId();
                   }else{
                        parkingLot.floors.get(i).slots.get(j).isBooked= true;
                        ticket t = new ticket(0,i,j);
                        return t.getTicketId();
                   }
                }
            }
        }
        return -1;
    }
    void unParkVehicle(Integer regNo, String colour)
    {
        Vehicle v = mapVehicle.get(regNo);
        if(vehicleTicket.containsKey(v))
        {
            System.out.println("Ticket is invalid");
        }
        ticket t = vehicleTicket.get(v);
        ParkingLot p = mpParkingLot.get(t.ParkingLotId);
        Floor f = p.floors.get(t.floor);
        f.slots.get(t.slot).isBooked = false;
    }
    void display_free_slots()
    {
       
    }
}
public class parkingLots {
    
}
