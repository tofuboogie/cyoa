package dtz.cyoa;

class Rooms_bulk extends Rooms {

	public static void initBulkArrays(){
		tz_rooms = new Room[] {CottonCandyStand, 
	MiniatureTrainStation, MiniatureTrainCars, ChairSwing, ChairSwingTower, ChairSwingSeat,
	ChairSwingUnderGrating, ChairSwingTowerMid, ChairSwingTowerNearTop, ChairSwingTowerOnTop, HiStriker,
	AirRifleSidestall, Bathroom, DeepFriedTwinkie, Outbuilding,TauntedHoRse, MechanicalBull, CableCar, PicnicArea
		};

		dt_rooms = new Room[] { Begin, 
	FenceHole, Home, Work, Tracks, Fence, 
	TieShoes, FenceLock, Tracks2, ShoesNoise, FreakOut, 
	Melissa, TakeLoveLock, AskMelissa, MaintenanceShed, MaintenanceRearDoor, 
	MaintenanceRearWindow, MaintenanceShedFront, MaintShedFrontDoor, MaintShedInside, TakeCrowbar, 
	Gazebo, BurlesqueParlor, FerrisWheel, FunnelcakeStand, PublicSquare, 
	ArcherySidestall,BasketballSidestall, HotDogCart, MerryGoRound, PortaJon, 
	BumperCars, TicketBooth
		};

		items = new Item[] { iYourself, 
	iTshirt, iLoveLocks, iLoveLockKey, iThingamabob,iCrowbar, 
	iMaintShedFD, iTissue, iChairSwingGrating, iChairSwingDoor, iChairSwingDoorHandle, 
	iChairSwingLever, iChairSwingTowerNearTopDoor, iFlashlight
		};
	}
	
	public static void changeFieldReferences(){ // bc Java has no iYourself**
		changeFieldReferences_tz();
		changeFieldReferences_dt();
		changeFieldReferences_items();
	}
	
	public static void changeFieldReferences_tz() {
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
	}
		
	public static void changeFieldReferences_dt() {
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
	}
	
	public static void changeFieldReferences_items() {
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


}
