package dtz.cyoa;

import java.util.ArrayList;
import java.util.Stack;
import java.io.*;

public class Rooms implements Serializable {

	protected static Player You;
	public static Stack<Room> _stkPrevious = new Stack<Room>();		// previous rooms chain
	public static Room currentRoom; // keep track of current room in Main
	public static Room priorRoom; // used to modify Room descriptions based from whence you come
	
	//TZ's rooms
	protected static Room CottonCandyStand, MiniatureTrainStation, MiniatureTrainCars, ChairSwing;
	protected static Room ChairSwingTower, ChairSwingSeat, ChairSwingUnderGrating, ChairSwingTowerMid;
	protected static Room ChairSwingTowerNearTop, ChairSwingTowerOnTop;
	protected static Room HiStriker, AirRifleSidestall;
	protected static Room Bathroom, DeepFriedTwinkie, Outbuilding;
	protected static Room TauntedHoRse, MechanicalBull, CableCar, PicnicArea;

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
	
	//bulk operations
	private Room[] tz_rooms = {CottonCandyStand, 
MiniatureTrainStation, MiniatureTrainCars, ChairSwing, ChairSwingTower, ChairSwingSeat,
ChairSwingUnderGrating, ChairSwingTowerMid, ChairSwingTowerNearTop, ChairSwingTowerOnTop, HiStriker,
AirRifleSidestall, Bathroom, DeepFriedTwinkie, Outbuilding,TauntedHoRse, MechanicalBull, CableCar, PicnicArea
	};

	private Room[] dt_rooms = { Begin, 
FenceHole, Home, Work, Tracks, Fence, 
TieShoes, FenceLock, Tracks2, ShoesNoise, FreakOut, 
Melissa, TakeLoveLock, AskMelissa, MaintenanceShed, MaintenanceRearDoor, 
MaintenanceRearWindow, MaintenanceShedFront, MaintShedFrontDoor, MaintShedInside, TakeCrowbar, 
Gazebo, BurlesqueParlor, FerrisWheel, FunnelcakeStand, PublicSquare, 
ArcherySidestall,BasketballSidestall, HotDogCart, MerryGoRound, PortaJon, 
BumperCars, TicketBooth
	};

	private Item[] items = { iYourself, 
iTshirt, iLoveLocks, iLoveLockKey, iThingamabob,iCrowbar, 
iMaintShedFD, iTissue, iChairSwingGrating, iChairSwingDoor, iChairSwingDoorHandle, 
iChairSwingLever, iChairSwingTowerNearTopDoor, iFlashlight
	};
	
	public void changeFieldReferences(){ // bc Java has no iYourself**
		//tz_rooms
		CottonCandyStand = tz_rooms[0];
		
		MiniatureTrainStation = tz_rooms[1];
		MiniatureTrainCars = tz_rooms[2];
		ChairSwing = tz_rooms[3];
		ChairSwingTower = tz_rooms[4];
		ChairSwingSeat = tz_rooms[5];

		ChairSwingUnderGrating = tz_rooms[6];
		ChairSwingTowerMid = tz_rooms[7];
		ChairSwingTowerNearTop = tz_rooms[8];
		ChairSwingTowerOnTop = tz_rooms[9];
		HiStriker = tz_rooms[10];

		AirRifleSidestall = tz_rooms[11];
		Bathroom = tz_rooms[12];
		DeepFriedTwinkie = tz_rooms[13];
		Outbuilding = tz_rooms[14];
		TauntedHoRse = tz_rooms[15];
		
		MechanicalBull = tz_rooms[16];
		CableCar = tz_rooms[17];
		PicnicArea = tz_rooms[18];
		
		//dt_rooms
		Begin = dt_rooms[0];

		FenceHole = dt_rooms[1];
		Home = dt_rooms[2];
		Work = dt_rooms[3];
		Tracks = dt_rooms[4];
		Fence = dt_rooms[5];

		TieShoes = dt_rooms[6];
		FenceLock = dt_rooms[7];
		Tracks2 = dt_rooms[8];
		ShoesNoise = dt_rooms[9];
		FreakOut = dt_rooms[10];

		Melissa = dt_rooms[11];
		TakeLoveLock = dt_rooms[12];
		AskMelissa = dt_rooms[13];
		MaintenanceShed = dt_rooms[14];
		MaintenanceRearDoor = dt_rooms[15];

		MaintenanceRearWindow = dt_rooms[16];
		MaintenanceShedFront = dt_rooms[17];
		MaintShedFrontDoor = dt_rooms[18];
		MaintShedInside = dt_rooms[19];
		TakeCrowbar = dt_rooms[20];

		Gazebo = dt_rooms[21];
		BurlesqueParlor = dt_rooms[22];
		FerrisWheel = dt_rooms[23];
		FunnelcakeStand = dt_rooms[24];
		PublicSquare = dt_rooms[25];

		ArcherySidestall = dt_rooms[26];
		BasketballSidestall = dt_rooms[27];
		HotDogCart = dt_rooms[28];
		MerryGoRound = dt_rooms[29];
		PortaJon = dt_rooms[30];

		BumperCars = dt_rooms[31];
		TicketBooth = dt_rooms[32];

		//items
		iYourself = items[0];
		
		iTshirt = items[1]; 
		iLoveLocks = items[2]; 
		iLoveLockKey = items[3]; 
		iThingamabob = items[4];
		iCrowbar = items[5]; 
		
		iMaintShedFD = items[6]; 
		iTissue = items[7]; 
		iChairSwingGrating = items[8]; 
		iChairSwingDoor = items[9]; 
		iChairSwingDoorHandle = items[10];
		
		iChairSwingLever = items[11];
		iChairSwingTowerNearTopDoor = items[12];
		iFlashlight = items[13];
	}
	//end bulk operations
	
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
		i.initializeItemsActions();
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		//write non-static fields
		out.defaultWriteObject();
		
		out.writeObject(You);
		out.writeObject(_stkPrevious);
		out.writeObject(currentRoom);
		out.writeObject(priorRoom);
		//write Rooms
		out.writeObject(CottonCandyStand);
		out.writeObject(MiniatureTrainStation);
		out.writeObject(MiniatureTrainCars);
		out.writeObject(ChairSwing);
		out.writeObject(ChairSwingTower);
		out.writeObject(ChairSwingSeat);
		out.writeObject(ChairSwingUnderGrating);
		out.writeObject(ChairSwingTowerMid);
		out.writeObject(ChairSwingTowerNearTop);
		out.writeObject(ChairSwingTowerOnTop);
		out.writeObject(HiStriker);
		out.writeObject(AirRifleSidestall);
		out.writeObject(Bathroom);
		out.writeObject(DeepFriedTwinkie);
		out.writeObject(Outbuilding);
		out.writeObject(Begin);
		out.writeObject(FenceHole);
		out.writeObject(Home);
		out.writeObject(Work);
		out.writeObject(Tracks);
		out.writeObject(Fence);
		out.writeObject(TieShoes);
		out.writeObject(FenceLock);
		out.writeObject(Tracks2);
		out.writeObject(ShoesNoise);
		out.writeObject(FreakOut);
		out.writeObject(Melissa);
		out.writeObject(TakeLoveLock);
		out.writeObject(AskMelissa);
		out.writeObject(MaintenanceShed);
		out.writeObject(MaintenanceRearDoor);
		out.writeObject(MaintenanceRearWindow);
		out.writeObject(MaintenanceShedFront);
		out.writeObject(MaintShedFrontDoor);
		out.writeObject(MaintShedInside);
		out.writeObject(TakeCrowbar);
		out.writeObject(Gazebo);
		out.writeObject(BurlesqueParlor);
		out.writeObject(FerrisWheel);
		out.writeObject(FunnelcakeStand);
		out.writeObject(PublicSquare);
		out.writeObject(ArcherySidestall);
		out.writeObject(BasketballSidestall);
		out.writeObject(HotDogCart);
		out.writeObject(MerryGoRound);
		out.writeObject(PortaJon);
		out.writeObject(BumperCars);
		out.writeObject(TicketBooth);
		//write Items
		/*
		for (int i=0;i<items.length;i++){
			out.writeObject(items[i]);
		}
		*/
		out.writeObject(iYourself);
		out.writeObject(iTshirt);
		out.writeObject(iLoveLocks);
		out.writeObject(iLoveLockKey);
		out.writeObject(iThingamabob);
		out.writeObject(iCrowbar);
		out.writeObject(iMaintShedFD);
		out.writeObject(iTissue);
		out.writeObject(iChairSwingGrating);
		out.writeObject(iChairSwingDoor);
		out.writeObject(iChairSwingDoorHandle);
		out.writeObject(iChairSwingLever);
		out.writeObject(iChairSwingTowerNearTopDoor);
		out.writeObject(iFlashlight);
		
		//write Persons
		out.writeObject(pMelissa);
	}
	
	@SuppressWarnings("unchecked") //otherwise, compiler complains about (Stack<Room>)in.readObject()
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
	//NOTE: This must read objects back in in _exactly_ the same order as they were writ out
		//read non-static fields
		in.defaultReadObject();
		
		You = (Player)in.readObject();
		_stkPrevious = (Stack<Room>)in.readObject();
		currentRoom = (Room)in.readObject();
		priorRoom = (Room)in.readObject();
		//read Rooms
		CottonCandyStand = (Room)in.readObject();
		MiniatureTrainStation = (Room)in.readObject();
		MiniatureTrainCars = (Room)in.readObject();
		ChairSwing = (Room)in.readObject();
		ChairSwingTower = (Room)in.readObject();
		ChairSwingSeat = (Room)in.readObject();
		ChairSwingUnderGrating = (Room)in.readObject();
		ChairSwingTowerMid = (Room)in.readObject();
		ChairSwingTowerNearTop = (Room)in.readObject();
		ChairSwingTowerOnTop = (Room)in.readObject();
		HiStriker = (Room)in.readObject();
		AirRifleSidestall = (Room)in.readObject();
		Bathroom = (Room)in.readObject();
		DeepFriedTwinkie = (Room)in.readObject();
		Outbuilding = (Room)in.readObject();
		Begin = (Room)in.readObject();
		FenceHole = (Room)in.readObject();
		Home = (Room)in.readObject();
		Work = (Room)in.readObject();
		Tracks = (Room)in.readObject();
		Fence = (Room)in.readObject();
		TieShoes = (Room)in.readObject();
		FenceLock = (Room)in.readObject();
		Tracks2 = (Room)in.readObject();
		ShoesNoise = (Room)in.readObject();
		FreakOut = (Room)in.readObject();
		Melissa = (Room)in.readObject();
		TakeLoveLock = (Room)in.readObject();
		AskMelissa = (Room)in.readObject();
		MaintenanceShed = (Room)in.readObject();
		MaintenanceRearDoor = (Room)in.readObject();
		MaintenanceRearWindow = (Room)in.readObject();
		MaintenanceShedFront = (Room)in.readObject();
		MaintShedFrontDoor = (Room)in.readObject();
		MaintShedInside = (Room)in.readObject();
		TakeCrowbar = (Room)in.readObject();
		Gazebo = (Room)in.readObject();
		BurlesqueParlor = (Room)in.readObject();
		FerrisWheel = (Room)in.readObject();
		FunnelcakeStand = (Room)in.readObject();
		PublicSquare = (Room)in.readObject();
		ArcherySidestall = (Room)in.readObject();
		BasketballSidestall = (Room)in.readObject();
		HotDogCart = (Room)in.readObject();
		MerryGoRound = (Room)in.readObject();
		PortaJon = (Room)in.readObject();
		BumperCars = (Room)in.readObject();
		TicketBooth = (Room)in.readObject();
		//read Items
		for (int i = 0;i<items.length;i++){
			items[i] = (Item)in.readObject();
		}
		changeFieldReferences();
		/*
			iYourself = (Item)in.readObject();
			iTshirt = (Item)in.readObject();
			iLoveLocks = (Item)in.readObject();
			iLoveLockKey = (Item)in.readObject();
			iThingamabob = (Item)in.readObject();
			iCrowbar = (Item)in.readObject();
			iMaintShedFD = (Item)in.readObject();
			iTissue = (Item)in.readObject();
			iChairSwingGrating = (Item)in.readObject();
			iChairSwingDoor = (Item)in.readObject();
			iChairSwingDoorHandle = (Item)in.readObject();
			iChairSwingLever = (Item)in.readObject();
			iChairSwingTowerNearTopDoor = (Item)in.readObject();
			iFlashlight = (Item)in.readObject();
		*/
		
		//read Persons
		pMelissa = (Person)in.readObject();
		
			
		
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
	
	public Room getCurrent(){
		return currentRoom;
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
