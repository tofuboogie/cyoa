package dtz.cyoa;
class Rooms_bulk extends Rooms {

	public static void initBulkArrays(){
		tz_rooms = new Room[] {CottonCandyStand,MiniatureTrainStation,MiniatureTrainCars,ChairSwing,ChairSwingTower,ChairSwingSeat,ChairSwingUnderGrating,ChairSwingTowerMid,ChairSwingTowerNearTop,ChairSwingTowerOnTop,HiStriker,WaterGunGame,DeepFriedTwinkie,Outbuilding,TauntedHoRse,MechanicalBull,CableCar,PicnicArea,CircusTent,TiltAWhirl};

		dt_rooms = new Room[] {Begin,FenceHole,Home,Work,Tracks,Fence,TieShoes,FenceLock,Tracks2,ShoesNoise,FreakOut,Melissa,TakeLoveLock,AskMelissa,MaintenanceShed,MaintenanceRearDoor,MaintenanceRearWindow,MaintenanceShedFront,MaintShedFrontDoor,MaintShedInside,TakeCrowbar,Gazebo,BurlesqueParlor,FerrisWheel,FunnelcakeStand,PublicSquare,ArcherySidestall,ArcherySidestallInside,BasketballSidestall,HotDogCart,MerryGoRound,PortaJon,BumperCars,TicketBooth,BurlesqueStage};

		items = new Item[] {iYourself,iTshirt,iLoveLocks,iLoveLockKey,iThingamabob,iCrowbar,iMaintShedFD,iTissue,iChairSwingGrating,iChairSwingDoor,iChairSwingDoorHandle,iChairSwingLever,iChairSwingTowerNearTopDoor,iFlashlight,iBurlesqueSofa,iBurlesqueSwitch,iBurlesquePiano,iBurlesquePainting,iBurlesqueManikin,iBumperCar1,iBumperCar2,iBumperCar3,iArrows,iBasketball,iBasketballHoop};
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
		WaterGunGame = tz_rooms[11];
		DeepFriedTwinkie = tz_rooms[12];
		Outbuilding = tz_rooms[13];
		TauntedHoRse = tz_rooms[14];
		MechanicalBull = tz_rooms[15];
		CableCar = tz_rooms[16];
		PicnicArea = tz_rooms[17];
		CircusTent = tz_rooms[18];
		TiltAWhirl = tz_rooms[19];
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
		ArcherySidestallInside = dt_rooms[27];
		BasketballSidestall = dt_rooms[28];
		HotDogCart = dt_rooms[29];
		MerryGoRound = dt_rooms[30];
		PortaJon = dt_rooms[31];
		BumperCars = dt_rooms[32];
		TicketBooth = dt_rooms[33];
		BurlesqueStage = dt_rooms[34];
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
		iBurlesqueSofa = items[14];
		iBurlesqueSwitch = items[15];
		iBurlesquePiano = items[16];
		iBurlesquePainting = items[17];
		iBurlesqueManikin = items[18];
		iBumperCar1 = items[19];
		iBumperCar2 = items[20];
		iBumperCar3 = items[21];
		iArrows = items[22];
		iBasketball = items[23];
		iBasketballHoop = items[24];
	}
}