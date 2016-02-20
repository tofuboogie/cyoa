package dtz.cyoa;
import java.io.*;

import java.util.ArrayList;

public class TawniRooms extends Rooms implements Serializable {

	/*
	1 Cotton Candy Stand
	2 Miniature train:
	3 Chairswing:
	4 High-striker (Hit-the-Bell):
	5 Water Rifle game:
	6 Balloon and Darts board:
	7 Deep-fried Twinkie stand:
	*7.5 Bathroom
	8 H(T)aunted Hou(R)se:
	9 Circus Tent:
	10 Tilt-A-Whirl:
	11 Mechanical bull:
	12 Picnic area:
	13 Cablecar:
	14 Outbuilding:
	*/

	public TawniRooms() {
	}


	public void initMemory(){

		CottonCandyStand = new Room();
		MiniatureTrainStation = new Room();
		MiniatureTrainCars = new Room();
		HiStriker = new Room();
		AirRifleSidestall = new Room();
		DeepFriedTwinkie = new Room();
		Bathroom = new Room();
		ChairSwing = new Room(); ChairSwingTower = new Room(); ChairSwingSeat = new Room();
		ChairSwingUnderGrating = new Room(); ChairSwingTowerMid = new Room(); 
		ChairSwingTowerNearTop = new Room(); ChairSwingTowerOnTop = new Room();
	}
	
	public void initializeRooms() {
			
	// Room: CottonCandyStand ---------------------------------------------------------------------------
	CottonCandyStand.setDescription(
	"Cotton Candy Stand",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The garish neon pink stand has the distinctive, cloying scent of spun sugar. There are bags of shrivelled up cotton candy lining the wall. A few look as though they have been opened and had a bite taken out of them."
					};
				break;
				case 2:
					strTemp = new String[] {
						"Upon entering the stand again, you spot a small stuffed octopus on the floor. You could swear it wasn't there before."
					};
				break;
				case 3:
					strTemp = new String[] {
						"This time when you enter the stand, the smell of sugar is too much. You greedily grab one of the sealed bags from the wall and start eating it. You are covered in sticky, sickly green slime, but it is oddly delicious."
					};
				break;
				default:
					strTemp = new String[] {
						"Eight bags of cotton candy later, your stomach explodes."
					};
				break;
			}
		
			return strTemp;
		}
	});

	CottonCandyStand.setOtherRooms(
	null,
	MiniatureTrainStation, ChairSwing, HiStriker,
	"Miniature Train","Go to the miniature train to follow the tracks. We all know how you like to follow tracks.",
	"Chair Swing","Go to the chair swing to see if you can get a higher vantage point.",
	"Hi-Striker","Go to the Hi-Striker to test your strength."
	);


	// Room: MiniatureTrainStation -----------------------------------------------------------------------
	MiniatureTrainStation.setDescription(
	"Miniature Train Station",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The sign above the admission booth reads 'RAIN IDES $2.' Only the first few feet of train tracks are visible before they are covered with overgrown grass. Train cars sit in the grass about 50 yards ahead."
					};
				break;
				case 2:
					strTemp = new String[] {
						"Popcorn litters the floor. You can feel it getting stuck in your shoe soles the same way it gets stuck in your teeth." 
					};
				break;
				case 3:
					strTemp = new String[] {
						"There is a large bumblebee spray painted on the back side of the sign."
					};
				break;
				default:
					strTemp = new String[] {
						"Nothing has changed."
					};
				break;
			}
		
			return strTemp;
		}
	});

	MiniatureTrainStation.setOtherRooms(
	null,
	MiniatureTrainCars, ChairSwing, HiStriker, 
	"MiniatureTrainCars","Go check out the train cars.",
	"Chair Swing","Go to the chair swing to see if you can get a higher vantage point.",
	"Hi-striker","Go to the hi-striker to test your strength."
	);

	//WaterGunGame, "Water Gun Game","Go see if you can find a squirt gun. Just in case."

	// Room: MiniatureTrainCars ---------------------------------------------------------------------------
	MiniatureTrainCars.setDescription(
	"Miniature Train Cars",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The train appears to have derailed at a rather high speed. The locomotive is on its side. Several of the cars have detached from each other and sit at odd angles in the grass. The caboose is the only car still on the tracks."
					};
				break;
				case 2:
					strTemp = new String[] {
						"The caboose is covered in random initials."
					};
				break;
				case 3:
					strTemp = new String[] {
						"It appears that wires have been pulled out of the control panel in the locomotive. There is a knit cap on the conductor's seat."
					};
				break;
				default:
					strTemp = new String[] {
						"Nothing has changed."
					};
				break;
			}
		
			return strTemp;
		}
	});

	MiniatureTrainCars.setOtherRooms(
	null,
	ChairSwing, HiStriker, AirRifleSidestall,
	"Chair Swing","Go to the chair swing to see if you can get a higher vantage point.",
	"Hi-striker","Go to the hi-striker to test your strength.",
	"Water Gun Game","Go see if you can find a squirt gun. Just in case."
	);
	
	MiniatureTrainCars.setStuff(
	new ArrayList<Item>() {
		{ add(iFlashlight);
		}
	},
	null
	);


	// Room: ChairSwing -----------------------------------------------------------------------------
	ChairSwing.setDescription(
	"Chair Swing",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The Chair Swing ride has a central tower with laughing clown faces painted on it. There are about 30 swings dangling from the spinner top."
					};
				break;
				case 2:
					strTemp = new String[] {
						"The underside of the spinner is painted with a giant, hypnotic spiral. Your eyes follow the steel shaft of the chairswing down to its base, at your feet. You notice that the metal grating underfoot rocks a little."
					};
				break;
				case 3:
					strTemp = new String[] {
						"Several of the clowns have knit bowties nailed under their faces."
					};
				break;
				default:
					strTemp = new String[] {
						"You walk back up to the large steel cylinder that suports the swing. You rock back and forth on the loose grating."
					};
				break;
			}
		
			return strTemp;
		}
	});

	ChairSwing.setOtherRooms(
	null,
	ChairSwingSeat, null, null,	
	"Chair Swing Seat","Go and sit in a chair swing. It might be really fun.",
	"","",
	"",""
	);
	
	ChairSwing.setStuff(
	new ArrayList<Item>() {
		{ add(iChairSwingGrating);
		}
	},
	null
	);
	
	
// Room: ChairSwingUnderGrating --------------------------------------------------------------------------
	ChairSwingUnderGrating.setDescription(
	"Chair Swing -- Under the Grating",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION; } else { ACTION; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			 if (iChairSwingDoor.isOpen()) { a=" The hatch is open."; } else { a=" The hatch is closed"; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You have entered a little service area beneath the chairswing. The room is like a hollow concrete doughnut that circles around the heavy base of the tower. The tower has a thick steel foot that wraps around the base and is bolted down with bolts, the heads of which are as big as half of your hand.",
						"There is dust at the edges of the room, but otherwise it is surprisingly clean. There is a small but heavy steel door on the tower, which is closed. It looks like a submarine hatch, on which a rusty locking wheel is attached."
					};
				break;
				default:
					strTemp = new String[] {
						"You are back in service area underneath the chairswing."+a
					};
				break;
			}
		
			return strTemp;
		}
	});
	
	ChairSwingUnderGrating.setStuff(
	new ArrayList<Item>() {
		{ add(iChairSwingDoor);
		  add(iChairSwingDoorHandle);
		}
	},
	null
	);


	// Room: ChairSwingTower ----------------------------------------------------------------------------
	ChairSwingTower.setDescription(
	"Inside the tower",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d,e,f,g,h;
		
			if (You.areWith(pMelissa)) {
				f=" A drop falls on the back of Mel's neck and she lets out a little squeal, and jumps away.";
				g=" Mel grabs the flashlight out of your hand and holds it under her chin, making a goofy spooky noise. You drop your shoulders and give her the look. She leans her body over so that the penumbra of the light envelopes you, and then she straightens up and returns the flashlight like nothing ever happened.";
				h=" Mel stays close.";
			} else { 
				f="";g="";h="";
		    }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
			 if (You.have(iFlashlight) && iFlashlight.isOn()) { 
			 	ChairSwingTower.setNext1(ChairSwingTowerMid, "Up", "Climb the ladder");
			 	a=" In the center of the room is a large metal box, painted light grey. A steel driveshaft protrudes from the top of the box. You follow it upwards with your light. It ends a few feet above the box, but then forms a spline joint with another driveshaft, and continues upwards. The shaft looks to be about the diameter of two fists pressed together.\nThere is a ladder bolted onto the far wall."; 
			 	b=" A utility ladder is mounted on the wall here.";
			 	c="";
			 	e=" There is a ladder on the wall that goes up.";
			 } 
			 else { 
			 	ChairSwingTower.makeDeadEnd();
			 	a=""; b="";
			 	c=" You can't see your hand in front of your face, it is so dark.";
			 	e="";
			 }
			 d = " A loose cable screeching across metal sounds from above.";
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The air grows denser as you enter into the large steel tube. It is pitch black. You scuff you shoe on the floor while inching forward, and the sound echoes all around. You stop and listen.",
						"There is a continuous drip coming from somewhere up above. The sound of the droplet hitting the ground is surprisingly loud." + a + f
					};
				break;
				case 2:
					strTemp = new String[] {
						"You're back at the base of the tower. The wind blowing around outside whistles through the cracks." + b + d + h
					};
				break;
				case 3:
					strTemp = new String[] {
						"You've returned to the bottom of the chair swing, deep within the guts of the thing." + c + b
					};
				break;
				default:
					strTemp = new String[] {
						"You're back at the base of the chair swing tower." + e + h
					};
				break;
			}
		
			return strTemp;
		}
	});


// Room: ChairSwingTowerMid -------------------------------------------------------------------------------
	ChairSwingTowerMid.setDescription(
	"Halfway up the ladder",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			 if (You.areWith(pMelissa)) { a=" and Mel"; c="Melissa and you";} 
			 	else { a=""; c="You"; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			 if (comingFrom(ChairSwingTower)) { b=" up"; } else { b=" down"; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You" + a + " have climbed halfway"+b+" the ladder."
					};
				break;
				case 2:
					strTemp = new String[] {
						c + " have climbed"+b+" to the midpoint of the tower."
					};
				break;
				case 3:
					strTemp = new String[] {
						"You" + a + " have climbed halfway"+b+". You feel so brave."
					};
				break;
				default:
					strTemp = new String[] {
						"You" + a + " have climbed halfway"+b+" the ladder."
					};
				break;
			}
		
			return strTemp;
		}
	});
	
	ChairSwingTowerMid.setOtherRooms(
	ChairSwingTowerNearTop, null, null,
	"Climb","Keep climbing the ladder","","","",""
	);
	
	// Room: ChairSwingTowerNearTop -----------------------------------------------------------------------
	ChairSwingTowerNearTop.setDescription(
	"Near the top",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d,e,f;
		
			 if (You.areWith(pMelissa)) { 
			 	a=" Melissa lets out a sigh from below you."; 
			 	b=" and Mel"; 
			 	d=" are both";
			 } else { a=""; b=""; d="";}
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			 if (iChairSwingTowerNearTopDoor.isOpen()) { 
			 	e=" The hatch is open."; 
			 	f=" open";
			 } else { 
			 	e=" The hatch is closed."; 
			 	f=" closed";
			 }	
			if (comingFrom(ChairSwingTowerOnTop)) { 
				c=" You feel so refreshed after your visit to the roof."; 
			} else { c=""; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You have made it to the top of the ladder, and stop to take a rest." + a + " There is another hatch door here, which is"+f+" and leads up."
					};
				break;
				case 2:
					strTemp = new String[] {
						"You" + b + " have returned to the top of the ladder." +c + e
					};
				break;
				default:
					strTemp = new String[] {
						"You" + d+" back at the top of the ladder." +e
					};
				break;
			}
		
			return strTemp;
		}
	});
	
	ChairSwingTowerNearTop.setStuff(
	new ArrayList<Item>() {
		{ add(iChairSwingTowerNearTopDoor);
		}
	},
	null
	);
	
	// Room: ChairSwingTowerOnTop ----------------------------------------------------------------------
	ChairSwingTowerOnTop.setDescription(
	"On top of the tower",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d,e;
		
			 if (You.areWith(pMelissa)) { 
			 	a=" and Melissa"; 
			 	b="Melissa and you lean on the railing, watching the world for what feels like hours, but was probably only a few minutes. You steal a glance at her. She is staring into the distance, rapt. Her hands are clasped at her fingertip-most knuckles, and she is lightly rubbing her thumb on her forefinger. You see a bit of your mother in the moonlit shadow on her face, and your heart aches a bit.";
			 	c=" Mel just hangs out by the door, refusing to walk back to the edge. She says she doesn't want to spoil it by looking again.";
			 	d=" This time Mel doesn't even come all the way out of the hole in the floor, she just pokes her head and shoulders through it. She plants her elbows on the roof and rests her head in her palms with a slightly annoyed look.";
			 	e=" Mel stays on the ladder below.";
			 } else { 
			 	a=""; 
			 	b="You hang out on the railing for a while, watching this fine and quiet place. A light breeze blows your hair across your face, and you tuck it behind your ear.";
			 	c="";
			 	d=""; e="";
			 }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You"+a +" climb through the door and onto the roof of the tower. The roof is circular and relatively small, about 12 feet in diameter. A waist-high railing of rolled sheet steel circles the perimeter. The shaft from inside the tower emerges in the center of the roof, and has eight hollow spokes which radiate out through the only eight holes in the otherwise completely opaque railing. They extend about a foot out beyond the edge of the roof and end in a roller, from which the swing seat cable exits.",
						"The view of the world from up here is stunning. The moon, although only crescent, still seems to illuminate everything as if in a crisp old black-and-white photo. To the west, you see the spooky outline of the ferris wheel looking vacant and forgotten. To the north is the bone glow of the circus tent, and beyond that the railroad tracks and the glittering ripples on Emerald Lake. The hills beyond the lake are so low-lying, that the diamonds in the lake merge seamlessly with the twinkling diamonds in the sky.",
						b
					};
				break;
				case 2:
					strTemp = new String[] {
						"You"+a +" return to the top of the tower." +c+ " You walk to the edge and look out. You look to the south. The ticket booth, shuttered, is near the front gate, and beyond that a thicket of piney-woods."
					};
				break;
				case 3:
					strTemp = new String[] {
						"You"+a +" return to the roof." +d+ " You start to walk to the railing once more, but decide not to overdo it, and return to the roof hatch."
					};
				break;
				default:
					strTemp = new String[] {
						"You are back at the roof."+e+" You sit with your back against the shaft and look up at the night sky."
					};
				break;
			}
		
			return strTemp;
		}
	});

	// Room: ChairSwingSeat -------------------------------------------------------------------------------
	ChairSwingSeat.setDescription(
	"Chair Swing Seat",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The chair swing chains appear rusty but stable."
					};
				break;
				case 2:
					strTemp = new String[] {
						"You sit on a swing, and immediately crash to the ground. Your butt is bruised, but you are okay."
					};
				break;
				case 3:
					strTemp = new String[] {
						"On closer inspection, the chain appears to have been cut most of the way through with a saw."
					};
				break;
				default:
					strTemp = new String[] {
						"You sit in another seat. This time, you land on your head. You have died."
					};
				break;
			}
		
			return strTemp;
		}
	});

	ChairSwingSeat.setOtherRooms(
	null,
	null, null, null,
	"","","","","",""
	);

	// Room: DeepFriedTwinkie ----------------------------------------------------------------------------
	DeepFriedTwinkie.setDescription(
	"A gross deep fried Twinkie stand",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You have found the most disgusting thing on earth, minus ten year old butt funk: A deep fried Twinkie stand."
					};
				break;
				case 2:
					strTemp = new String[] {
						"Seriously? You're back for more?"
					};
				break;
				case 3:
					strTemp = new String[] {
						"After one one last breath of toxic Twinkie fryer sludge, you fall to the floor. You are dead."
					};
				break;
				default:
					strTemp = new String[] {
						"ROOM_DESC"
					};
				break;
			}
		
			return strTemp;
		}
	});

	DeepFriedTwinkie.setOtherRooms(
	FenceHole,
	Bathroom, null, null,
	"Bathroom","Go to the bathroom to vomit.",
	"","",
	"",""
	);

	// Room: Bathroom ----------------------------------------------------------------------------------------
	Bathroom.setDescription(
	"Bathroom",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION } else { ACTION }
			// if (You.have(ITEM)) { ACTION } else { ACTION }
			// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
			// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"A nice place to poop."
					};
				break;
				case 2:
					strTemp = new String[] {
						"ROOM_DESC2"
					};
				break;
				case 3:
					strTemp = new String[] {
						"ROOM_DESC3"
					};
				break;
				default:
					strTemp = new String[] {
						"ROOM_DESC_MORE"
					};
				break;
			}
		
			return strTemp;
		}
	});

	Bathroom.setOtherRooms(
	DeepFriedTwinkie,
	null, null, null,
	"","","","","",""
	);



	
	// End room definitions
	}
	
// End class TawniRooms
}