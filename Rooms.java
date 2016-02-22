package dtz.cyoa;

import java.util.ArrayList;
import java.util.Stack;
import java.io.*;

public class Rooms implements Serializable {

	public static Stack<Room> _stkPrevious = new Stack<Room>();		// previous rooms chain
	public static Room currentRoom; // keep track of current room in Main
	public static Room priorRoom; // used to modify Room descriptions based from whence you come
	
	//TZ's rooms
	protected static Room CottonCandyStand, MiniatureTrainStation, MiniatureTrainCars, ChairSwing;
	protected static Room ChairSwingTower, ChairSwingSeat, ChairSwingUnderGrating, ChairSwingTowerMid;
	protected static Room ChairSwingTowerNearTop, ChairSwingTowerOnTop;
	protected static Room HiStriker, AirRifleSidestall;
	protected static Room Bathroom, DeepFriedTwinkie, Outbuilding;

	//DT's rooms
	public static Room Begin;
	protected static Room FenceHole;
	protected static Room Home, Work, Tracks, Fence;
	protected static Room TieShoes, FenceLock;
 	protected static Room Tracks2, ShoesNoise, FreakOut;
	protected static Room Melissa, TakeLoveLock, AskMelissa;
	protected static Room MaintenanceShed, MaintenanceRearDoor, MaintenanceRearWindow;
	protected static Room MaintenanceShedFront, MaintShedFrontDoor, MaintShedInside;
	protected static Room TakeCrowbar;
	//DT unwrit
	protected static Room Gazebo, BurlesqueParlor;
	protected static Room FerrisWheel, FunnelcakeStand, PublicSquare;
	protected static Room ArcherySidestall,BasketballSidestall, HotDogCart;
	protected static Room MerryGoRound, PortaJon, BumperCars, TicketBooth;
	
	//items
	protected static Item iYourself;
	protected static Item iTshirt;
	protected static Item iLoveLocks, iLoveLockKey, iThingamabob;
	protected static Item iCrowbar, iMaintShedFD, iTissue;
	protected static Item iChairSwingGrating, iChairSwingDoor, iChairSwingDoorHandle, iChairSwingLever;
	protected static Item iChairSwingTowerNearTopDoor, iFlashlight;
	
	//persons
	protected static Person pMelissa;
	protected static Player You;
	
	// constructor
	public Rooms(){
	}
	
	public void init(){
		Items i = new Items();
		Persons p = new Persons();
		You = new Player();
		TawniRooms t = new TawniRooms();
		DaveRooms d = new DaveRooms();
		
		t.initMemory();
		d.initMemory();
		i.initMemory();
		p.initMemory();
		
		t.initializeRooms();
		d.initializeRooms();
		i.initializeItems();
		p.initializePersons();
		
		currentRoom = Begin;
		You.updateItem(iYourself,1);
		You.updateWearing(iTshirt,1);
		You.setDescription("",true,true);
	}
	
	public void initAfterRestore(){	//this re-inits the callbacks/interfaces that can't be serialized
		Items i = new Items();
		TawniRooms t = new TawniRooms();
		DaveRooms d = new DaveRooms();
		
		t.initializeRooms();
		d.initializeRooms();
		i.initializeItems();
	}
	

	
	public void removePreviousRooms(int howMany){
		if (_stkPrevious.size() >= howMany){
			for (int i=1;i<=howMany;i++){
				_stkPrevious.pop();
			}
		}
	}
	
	public void setCurrent(Room room){
		currentRoom = room;
	}
			
	public Room getPrevious() {
		if (!_stkPrevious.empty()) {
			return _stkPrevious.pop();
		}
		return null;
	}
	
	public Room peekPrevious() {
		if (!_stkPrevious.empty()) {
			return _stkPrevious.peek();
		}
		return null;
	}
	
	
	public void setPrevious(Room room) {
		if (!room.isAdoor()){
			_stkPrevious.push(room);
			priorRoom = room;
		}
	}
	
	public boolean yesPrevious() {
		if (_stkPrevious.empty() == false) {
			return true;
		}
		else { return false; }		
	}
	
	
	public void removePortal(Room portal){	// remove door "room"
	
		Room beforePortal = peekPrevious();
		Room afterPortal = new Room();
		if (portal._next1 != null) { afterPortal = portal._next1; }
		else if (portal._next2 != null) { afterPortal = portal._next2; }
		else if (portal._next3 != null) { afterPortal = portal._next3; }
		
		if (beforePortal._next1 == portal) {
			beforePortal.setNext1(afterPortal, "Inside", "Go inside");
		}
		else if (beforePortal._next2 == portal) {
			beforePortal.setNext2(afterPortal, "Inside", "Go inside");
		}
		else if (beforePortal._next3 == portal) {
			beforePortal.setNext3(afterPortal, "Inside", "Go inside");
		}
	}
	
	public boolean comingFrom(Room room){
		if (priorRoom == room){ return true; }
		else { return false; }
	}
	
	/*

					****************TEMPLATES*****************
	// ROOM.setDescription(String name, Description description);
	// Room: ROOM ----------------------------------------------------------------------------------------
	ROOM.setDescription(
	"ROOMNAME",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION; } else { ACTION; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"ROOMDESC1"
					};
				break;
				case 2:
					strTemp = new String[] {
						"ROOMDESC2"
					};
				break;
				case 3:
					strTemp = new String[] {
						"ROOMDESC3"
					};
				break;
				default:
					strTemp = new String[] {
						"ROOMDESC_Default"
					};
				break;
			}
		
			return strTemp;
		}
	});
	*/

	// ROOM.setOtherRooms(Room next1, Room next2, Room next3, String btn1Label, String btn1Txt, String btn2Label, String btn2Txt, String btn3Label, String btn3Txt);
	// Example:
	/*
	ROOM.setOtherRooms(
	null, null, null,
	"","","","","",""
	);
	*/
	
	// ROOM.setStuff(ArrayList<Item> items, ArrayList<Person> persons);
	// Example:
	/*
	ROOM.setStuff(
	new ArrayList<Item>() {
		{ add(iThingamabob);
		}
	},
	null
	);
	*/

	// ROOM.setAction(Callback callback);
	// Example:
	/*
	ROOM.setAction(
	new Room.Callback() {  
		public void update(int numberOfVisits) { 
			You.setDescription("",true,true);	// include with all rooms, to update player description
			//OTHER CODE
		}  
	});
	*/
	
}
