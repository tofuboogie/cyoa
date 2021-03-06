package dtz.cyoa;
import java.io.*;

import java.util.ArrayList;

public class DaveRooms extends Rooms implements Serializable {
//add You.drop(ITEM,ROOM);

	public DaveRooms() {
	}
	
	public void initMemory(){
		for (int i=0;i<dt_rooms.length;i++){
			dt_rooms[i] = new Room();
		}
		Rooms_bulk.changeFieldReferences_dt();
	}
	
	public void initializeRooms() {
	
// Room: Begin ----------------------------------------------------------------------------------------
Begin.setDescription(
"Begin",

new Room.Description() {
	public String[] make(int numberOfVisits){
		String[] strTemp;
		String a,b,c,d;
		
		// variables
		a = "According to locals, the park ";
		b=" Blondisha ";
		c=" productivity will not look good on ";
		d=" to work AGAIN? Go away";
		
		switch(numberOfVisits){		
			case 1:					// 1st visit
				strTemp= new String[] {
					"Ever since you moved to Whedonlost, you\'ve heard rumors about an abandoned theme park somewhere outside of town." + a + "used to be a major attraction and arguably the only reason for going to the town, but had closed down some 20-odd years ago after an economic downturn.",
					"You\'ve always been curious about the park, but didn\'t know how to find it. One day at work, your co-worker" + b + "tells you about how she used to go to the park when she was a kid. She said that if you walk behind the antique store in town, there\'s an old railway track that leads through a thicket, into the woods.", 
					"If you follow it for a few miles, you\'ll come upon the park.\nAfter work, you:"
				};
			break;
			
			case 2:					// 2nd visit
				strTemp = new String[] {
					"You're back at the beginning of the story, which is to say, you're back at work. What the heck are you doing?",
					"This attentiveness to"+ c + "your slacker resume."
				};
			break;
			
			case 3:					// 3rd visit
				strTemp = new String[] {
					"You must be kidding. You've returned" +d+"!"
				};
			break;
			
			default:				// >3 visits
				strTemp = new String[]{
					"You have finally miffed" + b + "and crew to the point that they have decided to whip you.",
					"The warped gang wraps you in packing tape and lay you across a pair of raised forklift tines. Half of the crew whips you gleefully with little vermicelli, chanting \"Vermin...Vermin...\", while the other half throw dry pasta shells in your face.",
					"You have died."
				};	
			break;
		}
	return strTemp;	
	}
});
	
Begin.setOtherRooms(
	Home, Work, Tracks,

	"Home",
	"Go home and forget about the park. There\'s a new episode of \"The World\'s Next Very Best Person Alive\" on tonight.",

	"Work",
	"Stay late to work on a project. There\'s no way you\'ll get a quarterly bonus if you don\'t get it done.",

	"Antique Store",
	"Go to the antique store, ostensibly looking for treasures, then sneak out the back door and head down the tracks."
);

Begin.setAction(
	new Room.Callback() {  
		public void update(int numberOfVisits) { 
			You.setDescription("",true,true);	// include with all rooms, to update player description
			if (numberOfVisits > 10){
				You._dead = true;
			}
		}  
});


// Room: Home ----------------------------------------------------------------------------------------
Home.setDescription(
"Home",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		if (Home._items != null && Home._items.contains(iThingamabob)) {
			a = " put the thingamabob on the desk,";
			b = " You think about how you would both raise the thingamabob to maturity together.";
			c = " The thingamabob edges closer to you.";
			d = " the thing the thingamabob did to you last summer.";
		}
		else {
			a = "";
			b = "";
			c = "";
			d = " your mommy.";
		}
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You go home, watch the show, climb lazily into your bed," + a + " and have a dream in which you meet a new love deep within an abandoned amusement park." + b
				};
			break;
			case 2:
				strTemp = new String[] {
					"You are still at home, sleeping." + c
				};
			break;
			case 3:
				strTemp = new String[] {
					"You roll over on one side and start mumbling about" + d
				};
			break;
			default:
				strTemp = new String[] {
					"You toss and turn for all eternity."
				};
			break;
		}
		
		return strTemp;
	}
});

Home.setStuff(
	new ArrayList<Item>() {
		{ add(iThingamabob);
		}
	},
	null
);


// Room: Work ----------------------------------------------------------------------------------------
Work.setDescription(
"Work", 
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"After finishing your project, you go home and go to sleep. You do this over and over again until one day when you wake up and realize that your whole life has passed you by.",
					"You return to the office to find a new project in your mailbox."
				};
			break;
			case 2:
				strTemp = new String[] {
					"Your whole life has already passed you by. There's really nothing more to report."
				};
			break;
			case 3:
				strTemp = new String[] {
					"Your new project, which was to organize a warehouse full of paperclips into 492 piles separated by shape, size, color, and manufacturer, is still ongoing.",
					"You have only so far organized 2435 paperclips and your supervisor is pressuring you to move more quickly."
				};
			break;
			default:
				strTemp = new String[] {
					"You have now organized over " + 2435*numberOfVisits + " paperclips. Hooray!"
				};
			break;
		}
		
		return strTemp;
	}
});
	
Work.setOtherRooms(
Begin,
null, null, null,

"","",
"","",
"",""
);
	
// Room: Tracks ----------------------------------------------------------------------------------------
Tracks.setDescription(
"Tracks",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		// check for Melissa
		if (You._companions != null && You._companions.contains(pMelissa)) {
			a = "";
			c = " You tell Mel, \"Step on a track, break your back.\"\nShe\'s not super amused by this little rhymelet.";
		}
		else {
			a = " Your shoeslaces keep flapping around.";
			c="";
		}
		
		// check for love locks
		if (You._items != null && You._items.contains(iLoveLocks)){
			b = "";
		}
		else {
			b = " The lock glints glaringly at you and your shadow as you walk on by.";
		}
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You walk along an old railway track. It is late at night, and the crescent moon hangs above. There is a tall chain link fence that runs along one side of the tracks. You walk for over an hour.\nPeering through the fence you notice what looks like the shadow of a ferris wheel in the distance.",
					"As you trip on your shoelaces, you notice that there is a small lock on the fence nearby."
				};
			break;
			case 2:
				strTemp = new String[] {
					"You return to the tracks. You enjoy watching your long gray shadow as it walks in front of you." + a + b
				};
			break;
			case 3:
				strTemp = new String[] {
					"The tracks seem to welcome you home. You keep walking." +b+a
				};
			break;
			default:
				strTemp = new String[] {
					"You return to the tracks, yet again. You've begun to play a little game with yourself, walking in the gravel bed between the tracks themselves." + c
				};
			break;
		}
		
		return strTemp;
	}
});

Tracks.setOtherRooms(
Begin,
Fence, TieShoes, FenceLock,
"Walk", "Keep walking down the tracks, in the direction of the ferris wheel.",
"Tie Shoes", "Stop and tie your shoes.",
"Lock", "Take a closer look at the lock."
);

// Room: TieShoes ----------------------------------------------------------------------------------------
TieShoes.setDescription(
"Tying Shoes",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You stop and bend on one knee to tie your shoelaces. There is a noise from the bushes behind you."
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOM_DESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOM_DESC"
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

TieShoes.setOtherRooms(
Tracks,
Fence, ShoesNoise, FreakOut,

"Walk away",
"You get up quickly and start walking away, down the tracks. You try to act like you aren\'t scared, but your flapping shoelaces give you away.",

"Follow the noise",
"You decide to be bold, and walk towards the bushes.",

"Freak out",
""
);

// Room: FreakOut ----------------------------------------------------------------------------------------
FreakOut.setDescription(
"Freaking Out",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			default:
				strTemp = new String[] {
					"You freak the fuck out."
				};
			break;
		}
		
		return strTemp;
	}
});

FreakOut.setOtherRooms(
null, null, null,
"","",
"","",
"",""
);

// Room: Fence ----------------------------------------------------------------------------------------
Fence.setDescription(
"Fence",
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
			 if (comingFrom(Tracks) || comingFrom(Tracks2)) { 
			 	a="You walk facing down at the gravel between the tracks, and look up again to see that you are back at the hole in the fence. A little frog hops right through."; 
			 	b="Your eyes watch the fence as you walk along it. If you dull your focus in just the right way, you can see little three-dimensional zig-zags in it. You are back at hole in it.";
			 	c="You walk back down the tracks to the hole in the fence.";
			 } else { 
			 	a="You exit the park through the hole in the fence, and have returned to the tracks."; 
			 	b="You exit the park again, and are back on the tracks."; 
			 	c="You have made your egress yet again, and climb up the little gravel rise to the train tracks."; 
			 }
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You keep walking along the tracks. Your eyes run along the fence, just above the top bar of the chain link, where you can keep the ferris wheel in sight. The wheel bobs up and down with each of your steps.",
					"You notice a small hole in the fence."
				};
			break;
			case 2:
				strTemp = new String[] {
					a
				};
			break;
			case 3:
				strTemp = new String[] {
					b
				};
			break;
			default:
				strTemp = new String[] {
					c
				};
			break;
		}
		
		return strTemp;
	}
});

Fence.setOtherRooms(
Tracks,
FenceHole, Tracks2, null,
"Hole",
"Try squeezing through the hole. It looks like it was torn open by a couple of kids, but you just might fit.",
"Keep walking",
"Keep walking down the tracks. Its worked for you so far, might as well keep doing it.",
"",
""
);


// Room: ShoesNoise --------------------------------------------------------------------------------------
ShoesNoise.setDescription(
"In the bushes",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"As you step down from the tracks and peer into the bushes, you notice a young girl, moving around rapidly in the moonlight. She is roughly your own age. As you part the bushes and begin to walk towards her, you notice she is spray-painting some old concrete chunks that appear to have once been part of an old building.",
				  "At first glance, you can't really make out what she's painting. As you take another step forward, your eyes refocus and the image momentarily takes on the appearance of a topographical map. After another step the map is gone. Now all that you can see is a gradient purple and yellow tag saying \"Bumble\" with a bee swirling around it."
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOM_DESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOM_DESC"
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

ShoesNoise.setOtherRooms(
Tracks,
Tracks, Melissa, null,
"Leave",
"You decide you don\'t want anything to do with a tagger, and walk back to the tracks.",
"Approach",
"You decide to approach her. What\'s the worst that could happen?",
"",
""
);

// Room: Melissa ----------------------------------------------------------------------------------------
Melissa.setDescription(
"Melissa",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You approach the girl and call out \"Hello there?\" Unstartled, she looks over her shoulder at you. \"I was just walking down the tracks here, and noticed you were doing some painting, which is really nice, by the way. Actually, its really really awesome.\" You find yourself being weirdly flattering to this stranger, but it is true that you like the artwork and, also weirdly, she doesn't feel like a stranger.", 
				"She turns to face you fully. \"It\'s weird to meet you here like this,\" she says, with a little smirk. And then it all comes back to you.\n\"Melissa?\""
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOM_DESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOM_DESC"
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

Melissa.setOtherRooms(
AskMelissa, null, null,
"Ask",
"Ask Melissa to join you",
"","","",""
);

Melissa.setStuff(
null,
new ArrayList<Person>() {
	{ add(pMelissa);
	}
}
);


// Room: AskMelissa --------------------------------------------------------------------------------------
AskMelissa.setDescription(
"Asking Melissa",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		
		switch (numberOfVisits){
			default:
				strTemp = new String[] {
					"You ask Melissa to join you on your walk. She declines, muttering, with a little spritz of spray paint in the air as if it were air freshener, that she\'s not a walker. You ask her if instead she\'d like to join you on a quest, and she accepts."
				};
			break;
		}
		return strTemp;
	}
});

AskMelissa.setOtherRooms(
null, null, null,"","","","","","");

AskMelissa.setAction(
new Room.Callback() {  
	public void update(int numberOfVisits) { 
		if (!You.areWith(pMelissa)) {
			You.updatePerson(pMelissa, 1);
			Melissa.updatePerson(pMelissa,0);
			removePreviousRooms(3);
			Tracks.setNext2(null,"","");
			You.updateItem(iTissue, 1);
		}
	}  
});


// Room: FenceLock ----------------------------------------------------------------------------------------
FenceLock.setDescription(
"Fence Lock",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You step down the little gravel embankment, and walk up to the fence to peer closely at the lock. You notice that it is actually two locks locked together. The second one had been tucked behind the first, so that you didn't see it from the tracks. One of the locks has a dial; the other requires a key.",
					"On a whim, you spin the dial and start to walk back to the tracks, when the lock clicks open and the other one falls to the ground."
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOM_DESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOM_DESC"
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


FenceLock.setOtherRooms(
Tracks,
Tracks, TakeLoveLock, null,
"Go back",
"You decide to stop messing with the love locks and get back to your adventure",
"Take Locks",
"Feeling a bit sentimental, you decide to take the locks",
"",
""
);

FenceLock.setStuff(
new ArrayList<Item>() {
	{ add(iLoveLocks);
	}
},
null
);


// Room: TakeLoveLock ------------------------------------------------------------------------------------
TakeLoveLock.setDescription(
"Love Locks: Taken",
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
					"You lean over and pick up the lovelock. You give a little tug on the latch, but it stays locked. The other lock is still hanging open on the fence, and you pull it off. Its dial rests on the number 42. You put them both in your pocket and walk back up to the tracks"
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOM_DESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOM_DESC"
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

TakeLoveLock.setOtherRooms(
Tracks,
null, null, null,
"","","","","",""
);


TakeLoveLock.setAction(
new Room.Callback() {  
	public void update(int numberOfVisits) {
		if (!You.have(iLoveLocks)){
			You.updateItem(iLoveLocks, 1);
			FenceLock.updateItem(iLoveLocks,0);
			FenceLock.makeDeadEnd();
			Tracks.setNext3(null,"","");
		}
	}  
});


// Room: FenceHole ----------------------------------------------------------------------------------------
FenceHole.setDescription(
"Hole in the Fence",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		if (You.areWith(pMelissa)) { a=" Melissa follows you with ease."; } else { a=""; }
		// if (You.have(ITEM)) { ACTION } else { ACTION }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You walk down to the fence and, nimble as ever, squeeze thru the tear in the chain link fence." + a
				};
			break;
			case 2:
				strTemp = new String[] {
					"You've returned to the fence, a portal between the carnies and the outside world."
				};
			break;
			case 3:
				strTemp = new String[] {
					"You are back at the fence."
				};
			break;
			default:
				strTemp = new String[] {
					"The fence feels safe. You can escape out of or into the park, whatever your heart desires."
				};
			break;
		}
		
		return strTemp;
	}
});


FenceHole.setOtherRooms(
Tracks,
Outbuilding, MaintenanceShed, CottonCandyStand,
"Outbuilding","Walk over to the outbuilding",
"Maintenance Shed","Check out the shed",
"Cotton Candy Stand","Examine the old cotton candy stand. With luck, you won't find anything edible."
);

// Room: Tracks2 ----------------------------------------------------------------------------------------
Tracks2.setDescription(
"More Tracks",
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
					"You continue walking. The night is warm, but a cool breeze blows towards you from further down the track. You look out across the open fields, all dun and grey under the moonlight. You close your eyes and lift your arms up and out to your sides, stepping confidently along the tracks.",
					"After another quarter-hour or so, you come to a railroad trestle. It spans a yawning ravine of Oregon grape, tasselbush, and mock orange, with occasional evergreens like fir and hemlock jutting from outcrops. At the bottom of the ravine, river rapids chase each other downstream. They sound like fuzzy AM radio.",
					"You look out across the trestle. You aren't especially afraid of heights, but it being so late, and you having already walked so much...and the bridge being so long, you decide that you'll just hang here a bit and enjoy watching the world, and then turn back."
				};
			break;
			case 2:
				strTemp = new String[] {
					"You return to the bridge. You feel the little urge inside you once again push you to cross it. But, it seems to you like there are probably better adventures to be had."
				};
			break;
			case 3:
				strTemp = new String[] {
					"You cannot deny the attraction of this place. The old creaky railroad trestle under the crescent moon. The water churning below, as backdrop to all the symphonies and mutinies of flora and fauna that live out their quiet drama all around you. You pop a few Skittles in your mouth, and suck them."
				};
			break;
			case 4:
				strTemp = new String[] {
					"You revisit the bridge. It draws you like a fly to flame, like slugs to kale. It draws you to it like a fly to sh**, and speaking of sh**, if you are still fantasizing of crossing the bridge, well, \"sh** or get off of the pot.\""
				};
			break;
			default:
				strTemp = new String[] {
					"You are back at that place you know so well, a bridge to somewhere. You neglect to cross it."
				};
			break;
		}
		
		return strTemp;
	}
});


Tracks2.setOtherRooms(
null, null, null,
"","","","","",""
);

// Room: MaintenanceShed ----------------------------------------------------------------------------------
MaintenanceShed.setDescription(
"Behind the Maintenace Shed",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		if (You.areWith(pMelissa)) { a="and Melissa "; b=" Melissa hops up in the wheelbarrow and does a quick little dance, to no one in particular."; } else { a="";b=""; }
		// if (You.have(ITEM)) { ACTION } else { ACTION }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You " +a+ "sneak up to the back side of the maintenance shed. There are a couple of old shovels, and hoes, and other rusty tools leaned up against it or lying on the ground. A wheelbarrow is tipped on its side, with tall grass growing around it. There is a wooden door here, and a small window up high, with most of its panes broken out."
				};
			break;
			case 2:
				strTemp = new String[] {
					"You're back behind the maintenance shed. You turn the wheelbarrow right side up, just because it feels right."
				};
			break;
			case 3:
				strTemp = new String[] {
					"You're back behind the maintenance shed."+b
				};
			break;
			default:
				strTemp = new String[] {
					"You're back behind the maintenance shed."
				};
			break;
		}
		
		return strTemp;
	}
});

MaintenanceShed.setOtherRooms(
MaintenanceRearDoor, MaintenanceShedFront, null,
"Door","Try to open the door.",
"Maintenance Shed (more)","Walk around and explore the shed some more.",
"",""
);

MaintenanceShed.setStuff(
new ArrayList<Item>() { {add(iCrowbar);} },
null
);

// Room: MaintenanceRearDoor -----------------------------------------------------------------------------
MaintenanceRearDoor.setDescription(
"Maintenance shed rear door",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		if (You.areWith(pMelissa)) { a=" Mel rolls her eyes and says to give it a rest."; } else { a=""; }
		if (You.have(iCrowbar)) { 
			   c=" You try prying on the door with your crowbar, but can't find a good leverage point.";
			   b=" You try again with your handy crowbar, but again can find no purchase.";  } 
		else { c=""; b=""; }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You walk up to the maintenance shed and try to open the door. It is locked. You turn the knob and slam against it repeatedly with your shoulder, but the door doesn't budge."+c+" It seems like an awfully secure door for this cheesy building, which makes you think it might be permanently nailed shut from the inside."
				};
			break;
			case 2:
				strTemp = new String[] {
					"You try to open the door again. You search around for something to pick the lock with, and find an old rusty nail on the ground. You fish around in the keyhole for a while." + a + " You give up."
				};
			break;
			case 3:
				strTemp = new String[] {
					"You try to open the door again."+b+" You slam it with your shoulder, and then kick it so hard you've probably now caused a microfracture somewhere in your foot. Someday this injury may worsen, confining you to a wheelchair. I hope it was worth it."
				};
			break;
			default:
				strTemp = new String[] {
					"You try to open the door again. As usual, it doesn't budge one millimeter."
				};
			break;
		}
		return strTemp;
	}
});


// Room: MaintenanceRearWindow	UNUSED ----------------------------------------------------------------------------
MaintenanceRearWindow.setDescription(
"Maintenance shed rear window",
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
					"This broken window is up too high to crawl through, and besides, you'd slice yourself up pretty good trying it."
				};
			break;
			case 2:
				strTemp = new String[] {
					"The window hasn't changed since your last visit. Its too high up, and it is like an angry maw just waiting to bite you if you tried to crawl through it."
				};
			break;
			default:
				strTemp = new String[] {
					"The window is really too dangerous to mess with."
				};
			break;
		}
		
		return strTemp;
	}
});

// Room: MaintenanceShedFront ---------------------------------------------------------------------------
MaintenanceShedFront.setDescription(
"In front of the Maintenance Shed",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		// if (You.areWith(PERSON)) { ACTION } else { ACTION }
		// if (You.have(ITEM)) { ACTION } else { ACTION }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		if (iMaintShedFD.isOpen()) { a=" The door is open."; b="(open)"; } 
							  else { a=" The door is closed."; b=""; }
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You walk around to the front of the maintenance shed. A garage door spans most of the width of the building, leaving just enough space for a heavy wooden door beside it. The door has a hand-painted sign on it that reads, \"Authorized Personal Only.\"" + a
				};
			break;
			case 2:
				strTemp = new String[] {
					"You return to the front of the shed. It's almost as functional and drab as you remembered it, except this time around you notice a small ceramic pot up against the wall between the garage door and the wooden door" + b +". There is a crooked little stick poking out of it."
				};
			break;
			default:
				strTemp = new String[] {
					"You are back at the front of the shed." + a
				};
			break;
		}
		
		return strTemp;
	}
});

MaintenanceShedFront.setOtherRooms(
MaintShedFrontDoor, Outbuilding, BurlesqueParlor,
"Wooden Door","Open the door",
"Outbuilding","Check out a different building",
"Burlesque Parlor","Try the burlesque side of life"
);



// Room: MaintShedFrontDoor -------------------------------------------------------------------------
MaintShedFrontDoor.setDescription(
"Front door of the maintenance shed",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		 if (You.areWith(pMelissa))	{ a="Melissa takes a little half-hearted flying kick at the door, barely tapping it with her foot, and then she laughs a little to herself and walks around the corner."; } 			 else { a=""; }
		// if (You.have(ITEM)) { ACTION } else { ACTION }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }

		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You try to open the wooden door. It opens just a crack, and then catches on a little security chain, at about eye-level. You slam your shoulder against it to try and break through it, but only succeed in bruising yourself."
				};
			break;
			case 2:
				strTemp = new String[] {
					"You walk back up to the door."+a
				};
			break;
			case 3:
				strTemp = new String[] {
					"You return to the door, and give it a hefty push. The chain holds. You kick the door in disgust, as if it were a stubborn mule, and walk away."
				};
			break;
			default:
				strTemp = new String[] {
					"You are back at the wooden door. It is slightly ajar, but secured with a brass chain."
				};
			break;
		}
		
		return strTemp;
	}
});

MaintShedFrontDoor.setStuff(
new ArrayList<Item>() { {add(iMaintShedFD);} },
null
);

MaintShedFrontDoor.setAction(
new Room.Callback() {  
	public void update(int numberOfVisits) { 
		if (iMaintShedFD.isOpen()) {
			MaintShedFrontDoor.setNext1(MaintShedInside, "Inside", "Go inside");
			removePortal(MaintShedFrontDoor);
		}
	}  
});
MaintShedFrontDoor.makeIntoAdoor();

// Room: MaintShedInside --------------------------------------------------------------------------------
MaintShedInside.setDescription(
"Inside the Shed",
new Room.Description() {
	public String[] make(int numberOfVisits) {
		String strTemp[];
		String a,b,c,d;
		
		// if (You.areWith(PERSON)) { ACTION } else { ACTION }
		// if (You.have(ITEM)) { ACTION } else { ACTION }
		// if (ROOM.hasItem(ITEM) { ACTION } else { ACTION }
		// if (ROOM.hasPerson(PERSON) { ACTION } else { ACTION }
		// if (ITEM.isOpen()) { ACTION } else { ACTION }			// useful for doors
		
		switch (numberOfVisits){
			case 1:
				strTemp = new String[] {
					"You are inside the maintenance shed."
				};
			break;
			case 2:
				strTemp = new String[] {
					"ROOMDESC"
				};
			break;
			case 3:
				strTemp = new String[] {
					"ROOMDESC"
				};
			break;
			default:
				strTemp = new String[] {
					"ROOMDESC"
				};
			break;
		}
		
		return strTemp;
	}
});

	// Room: FerrisWheel -----------------------------------------------------------------------------
	FerrisWheel.setDescription(
	"Ferris Wheel",
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
	
	FerrisWheel.setOtherRooms(
	Gazebo, ArcherySidestall, CircusTent,
	"Gazebo","",
	"Archery Sidestall","",
	"Circus Tent",""
	);
	
	// Room: Gazebo -----------------------------------------------------------------------------
	Gazebo.setDescription(
	"Gazebo",
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

	Gazebo.setOtherRooms(
	MaintenanceShed, FerrisWheel, BurlesqueParlor,
	"Maintenance Shed","Check out the shed.",
	"Ferris Wheel","Walk over to the ferris wheel.",
	"Burlesque Parlor","Explore your naughty side."
	);
	
	// Room: BurlesqueParlor -----------------------------------------------------------------------------
	BurlesqueParlor.setDescription(
	"Burlesque Parlor",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d,e,f,g,h,i;
			
				if (iBurlesqueSwitch.isOn()) {
					g = " The red glow warms her face.";
					h = " The light from the red chandelier seems to float into the room like a fog. It flickers occasionally.";
					i = " The diamonds refract and reflect the red light.";
				} else {g="";h="";i="";}
				if (You.areWith(pMelissa)) { c=" and Mel"; f=" Melissa tromps over to the sofa and sits on it, then flops comically over on her side and pretends to doze deeply."+g;} else { c="";f="";}
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
				if ((You.have(iFlashlight) && iFlashlight.isOn()) 
					|| iBurlesqueSwitch.isOn()) {
			   		a=" The room is small. Near the door is a light switch. Three round tables, with three white wireframe chairs leaned up against each one, fill the floor of the room. A little stage is on the far end. A plush curtain is drawn up and clasped to the left wall; the other curtain hangs flush, blocking the right side of the stage."+h; 
			   		b="Next and to the left of the stage is an arched recession in the wall, just deep enough, and wide enough, to house a long velvet sofa. There is a painting on the wall behind the sofa. A chandelier hangs glitteringly above the stage.";
			   		d=" Three round tables, with three chairs leaned up against each one, are in the middle of the room. A stage is on the far end. A plush curtain splits in the middle and opens to the left; the other curtain hangs fully extended, screening out the right side of the stage."; 
			   		e="Next and to the left of the stage is an arched recession in the wall. In it is a long sofa. There is a painting on the wall behind the sofa." +f+ " A chandelier with clear glass diamonds hangs above the stage."+i;
			   	} 
			   	
			   	else { 
			   		a=" You" +c+ " cannot see anything. You feel along the wall near the door and discover a light switch."; 
			   		b="";
			   		d=" You" +c+ " can't see a thing. You feel flitter your fingers around the wall near the door and discover a button that may be pushable."; 
			   		e="";
			   	}
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You" +c+ " open the door and walk into the parlor." + a, b
					};
				break;
				case 2:
					strTemp = new String[] {
						"You" +c+ " return to the parlor." + d,e
					};
				break;
				default:
					strTemp = new String[] {
						"You" +c+ " return to the parlor." +a,b
					};
				break;
			}
			return strTemp;
		}
	});

	BurlesqueParlor.setOtherRooms(
	MaintenanceShed, FerrisWheel, BurlesqueStage,
	"Maintenance Shed","Go to the maintenance shed.",
	"Ferris Wheel","See what you can find at the ferris wheel.",
	"Burlesque Stage","Explore the stage. What could lie behind the curtain?"
	);
	
	BurlesqueParlor.setStuff(
	new ArrayList<Item>() {{ 
		add(iBurlesqueSofa);
		add(iBurlesqueSwitch);
		add(iBurlesquePainting);
	}},
	null);

	// Room: BurlesqueStage -------------------------------------------------------------------------------
	BurlesqueStage.setDescription(
	"Burlesque Stage",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) { 
				b=" and Melissa"; 
				c=" Mel call out from behind you, \"You OK?,\" and you respond that you are.";
				d=" Mel leans in close to read it with you.";
			} else { b="";c="";d=""; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
			if ((You.have(iFlashlight) && iFlashlight.isOn()) 
					|| iBurlesqueSwitch.isOn()) { 
				a="You" +b+ " walk up to the stage, and set up onto it. You walk behind the curtain and see a little piano and a dressing form. A corn broom leans against the wall in the corner. You notice a little piece of paper on the floor, poking out from underneath the piano. You pick it up and flip it over." +d+ " Scribbled in smudged pencil, the note reads, \"if u notice him around here again call my cell\"";
			}
			else {
				a="You fumble your way up to the stage, tripping on a little space heater and slamming your crotch into a chair, which you knock over with a crash." +c+ " You stop for a second, with your hands on your knees, more frustrated than injured. You feel around and find a curtain, which you pull aside, but cannot see a thing. You pull the curtain to your nose and it smells of begonias.";
			}
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						a
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
	
	BurlesqueStage.setStuff(
	new ArrayList<Item>() {{
		add(iBurlesqueManikin);
		add(iBurlesquePiano);
	}},
	null);

	// Room: FunnelcakeStand ------------------------------------------------------------------------
	FunnelcakeStand.setDescription(
	"Funnelcake Stand",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) { c=" and Mel"; } else {c="";}
			if (You.have(iFlashlight) && iFlashlight.isOn()) { 
				b=" Your flashlight is bright enough to illiminate most of the stand all at once. You" +c+ " walk inside. There is a long table near the serving windows, and some mostly empty shelves behind it. There is a deep and wide deep-fryer under the shelves, and a double-compartment commercial sink next to it. A dirty rag is folded over the edge of the sink."; 
			} else { b=" You cannot see anything inside."; }
			if (You.areWith(pMelissa)) { 
				a="Mel walks around the side, and you follow, finding a door that is only kept closed by a chair wedged under the doorknob. You pull the chair out and the door creaks, millimeter by creaking millimeter, completely open."+b; 
				d=" \"This is boring\", Mel says. \"Let's go somewhere else.\" You agree that this stand is boring.";
			} else { a=""; d="";}

			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The large billboard atop the stand reads \"Whirlin' Tornado Funnel Cakes\", and has two hand-painted tornadoes on opposite sides of the sign. The tornadoes have short curved lines near them that are supposed to convey motion, but end up looking like double parentheses. The billboard is lined by a series of small clear incandescent bulbs, all of which are off. Two sheets of well-weathered plywood are nailed over the serving windows.", a
					};
				break;
				case 2:
					strTemp = new String[] {
						"You" +c+ " return to the stand."+d+" You throw a little pebble at the plywood, and it goes \"plub\" and falls to the ground."
					};
				break;
				default:
					strTemp = new String[] {
						"You" +c+ " return to the stand. Why people ever ate the squished up little patty-cakes of intestine-looking fried batter, you'll never understand."
					};
				break;
			}
		
			return strTemp;
		}
	});

	FunnelcakeStand.setOtherRooms(
	PublicSquare, ArcherySidestall, BasketballSidestall,
	"Public Square","",
	"Archery Sidestall","",
	"Basketball Sidestall",""
	);
	
	// Room: PublicSquare --------------------------------------------------------------------------------
	PublicSquare.setDescription(
	"Public Square",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			 if (You.areWith(pMelissa)) { 
			 	a = " Melissa wanders off.";
			 	pMelissa.goesTo(BumperCars,You); 
			 } 
			 else { a=""; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You walk into the wide open public square. The area is about half the size of a football field. It is a patchwork of dusty dirt and overgrown grass, with a central section of cracked asphalt and little concrete pavers that radiate out from the middle." + a
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

	PublicSquare.setOtherRooms(
	CircusTent, FunnelcakeStand, MerryGoRound,
	"Circus Tent","",
	"Funnelcake Stand","",
	"Merry-Go-Round",""
	);

	// Room: ArcherySidestall ---------------------------------------------------------------------
	ArcherySidestall.setDescription(
	"Archery Sidestall",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) { 
				a=" and Mel"; } else { a=""; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The sidestall is a simple plywood booth with two round targets hanging on the back wall. The targets are shredded from continual exposure to blunt arrows. The stall has two American flag banners stapled along the lower front panel, and scalloped red rolls of corrugated cardboard trim lining the tops and sides of the two openings. It has the feel of a game you might see at a bad prom. The booth is really shallow, so the professed archers must have been required to stand at least ten feet away."
					};
				break;
				default:
					strTemp = new String[] {
						"You" +a+ " are back at the archery sidestall. It's really boring here."
					};
				break;
			}
		
			return strTemp;
		}
	});

	ArcherySidestall.setOtherRooms(
	FerrisWheel, ArcherySidestallInside, BasketballSidestall,
	"Ferris Wheel","",
	"Archery Booth","Enter the archery sidestall.",
	"Basketball Sidestall",""
	);


	
	// Room: ArcherySidestallInside ---------------------------------------------------------------------------------------
	ArcherySidestallInside.setDescription(
	"Inside the Archery Sidestall",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa) && ArcherySidestallInside.hasItem(iArrows)){ 
				a=" Melissa watches, then leans over the side to peer into the structure. \"Hey, what's that\", she says, pointing at the floor."; } else { a=""; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			if (ArcherySidestallInside.hasItem(iArrows)) { 
				b=" You look into the corner and see a bundle of arrows."; 
				c=" You nearly step on a pile of arrows as you land.";
				d=" There are still some arrows on the floor, although you could swear the pile used to be bigger.";
				} else { b="";c="";d=""; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
			// move NPC to another room: PERSON.goesTo(ROOM,You); 
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"Placing your hand on the sill of the opening, you gracefully hop through it and into the booth." +a+ b 
					};
				break;
				case 2:
					strTemp = new String[] {
						"You jump back into the archery stall." +c
					};
				break;
				case 3:
					strTemp = new String[] {
						"Instead of hopping into the stand, this time you just lean over the side and look in. You get a little dizzy, and start to think you might puke."+d
					};
				break;
				default:
					strTemp = new String[] {
						"You enter the archery sidestall, again."
					};
				break;
			}
		
			return strTemp;
		}
	});

	ArcherySidestallInside.setStuff(
	new ArrayList<Item>() {{ 
		add(iArrows);
	}},
	null);

	// Room: BasketballSidestall ----------------------------------------------------------------------
	BasketballSidestall.setDescription(
	"Basketball Sidestall",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d,e;
		
			if ((You.areWith(pMelissa)) && (You.have(iBasketball))) {
				c=" Mel sneaks up behind you and grabs the basketball. You protest a bit, but smile inwardly as she goofily tries to dribble her bibble, and then she shoots in this awkward leggy way that makes her body look like a falling chromosome. The basketball lands in the corner made by the hoop bracket and the backboard, and bounces out. It lands on the ground dully, and bounces three times with effort in your direction.";
			} 
			else { c=" You fumble around and drop the basketball."; }
 
			if (You.areWith(pMelissa)) {a=" and Mel"; } else { a=""; }
			if (You.have(iBasketball)) { 
				b=" You are holding the basketball against your hip, and leaning into in in a way that looks pretty sassy.";
				d=" You are holding the basketball like its a baby, scratching it a little."; 
				e=" You are holding a basketball.";
				} else { b=""; d="";e=""; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The game is bascially a regular basketball hoop, but with a plywood wall behind it, and two sidewalls that come out to the sides. There are a few rusty and bent bolts that extend from some concrete in the ground a few feet in front of you" +a+ ", which look like they once were part of a barrier to keep the balls in the stall."
					};
				break;
				case 2:
					strTemp = new String[] {
						"You are back at the basketball stand."+b+c
					};
						You.updateItem(iBasketball,0);
						BasketballSidestall.updateItem(iBasketball,1);			
				break;
				case 3:
					strTemp = new String[] {
						"You are back at the basketball stand."+d
					};
				break;
				default:
					strTemp = new String[] {
						"You are back at the basketball stand."+e
					};
				break;
			}
		
			return strTemp;
		}
	});

	BasketballSidestall.setOtherRooms(
	FunnelcakeStand, ArcherySidestall,HotDogCart,
	"Funnelcake Stand","",
	"Archery Sidestall","",
	"Hot-Dog Cart",""
	);
	
	BasketballSidestall.setStuff(
	new ArrayList<Item>() {{ 
		add(iBasketball);
		add(iBasketballHoop);
	}},
	null);

	// Room: HotDogCart --------------------------------------------------------------------------------
	HotDogCart.setDescription(
	"Hot-Dog Cart",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			// if (You.areWith(PERSON)) { ACTION; } else { ACTION; }
			if (You.areWith(pMelissa)) {a=" and Mel"; } else { a=""; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You" +a+ " walk up to the hot-dog stand. The cart is small and rusty. On one side, it has an open window, and next to that a wall with faded images of hot-dogs, amongst other wonders. There are simple, thick hot-dogs with braise marks on them ensconced in buns. The other images surrounding this one are variations on the theme: we have the same simple hot-dog, but with a squiggle of mustard down the length of it. There are hot-dogs with sauerkraut, hot-dogs with chili, hot-dogs with some unrecognizable goo glopped on it.","There are also things that don't make much sense to you, like hot dogs sitting at the base of what looks like a giant fried pillow, and another image of a bowl with fried spheres in it, which are the exact same color as hot-dogs. On the other side of the stand is a series of doors that lock with one central combination lock. The vendor probably would swing these all open and stand outside of the cart to do all of their work."
					};
				break;
				case 2: strTemp = new String[] {"You walk up to the cart, and are mesmerized by the kraut-dog."};break;
				case 3: strTemp = new String[] {"You return to the dog-stand, and are mesmerized by the chili-dog."};break;
				case 4: strTemp = new String[] {"You are back at the hot-dog stand. Your eyes focus on the large fried pillow, with lil' dogs hanging out nearby."};break;
				case 5: strTemp = new String[] {"Like a fly into the flame, you cannot ignore the cart, and you walk back to it, never moving your eyes off of the donut-holes, made out of hot-dogs"};break;
				case 6: strTemp = new String[] {"You return to the hot-dog cart, and finally, the images have lost their novelty, and start to properly turn your stomach."};break;
				default:
					strTemp = new String[] {
						"You"+a+" are back at the hot-dog cart."
					};
				break;
			}
		
			return strTemp;
		}
	});

	HotDogCart.setOtherRooms(
	BasketballSidestall, MerryGoRound, PortaJon,
	"Basketball Sidestall","",
	"Merry-Go-Round","",
	"Porta-Jon",""
	);
	
	// Room: MerryGoRound --------------------------------------------------------------------------------
	MerryGoRound.setDescription(
	"Merry-Go-Round",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) {a=" and Mel"; } else { a=""; }
			// if (You.areWith(PERSON)) { ACTION; } else { ACTION; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
			// move NPC to another room: PERSON.goesTo(ROOM,You); 
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You" +a+ " walk up to the merry-go-round. Everything within the ride is in shadow, blocked from the moonlight by a large circular roof. You can make out what look like three horses skewered on poles, galloping in a row at various heights. You suspect this means that there are probably about 8-10 horses encircling the turnstile.  
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

	MerryGoRound.setOtherRooms(
	PublicSquare, BumperCars, TicketBooth,
	"Public Square","",
	"Bumper Cars","",
	"Ticket Booth",""
	);
	
	// Room: PortaJon --------------------------------------------------------------------------------
	PortaJon.setDescription(
	"Porta-Jon",
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

	PortaJon.setOtherRooms(
	HotDogCart, MerryGoRound, BumperCars, 
	"Hot-Dog Cart","",
	"Merry-Go-Round","",
	"Bumper Cars",""
	);
	
	// Room: BumperCars --------------------------------------------------------------------------------
	BumperCars.setDescription(
	"Bumper Cars",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) { 
				a=" and Mel";
			} else { 
			 	a=""; 
			}
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			if (BumperCars.hasPerson(pMelissa)) { 
				b=" Mel is here, and she waves to you with a little wry smile. \"I'd wondered where you'd snuck off to,\" you say to her, and she replies, \"Just being a cat.\"";
				You.updatePerson(pMelissa, 1); 
				BumperCars.updatePerson(pMelissa, 0);
			} else { b=""; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"You" +a+ " enter the bumper car building."+b+" There are two 8-foot fluorescent tubes already lit, one overhead, and another on the ceiling in the middle of the room. They are just bright enough so that you can make out most of the details in the room.",
						"Ahead of you is a walkway along the wall, with access to restrooms and a closed concession stand. The bumper car field is to your right, down a couple of steps. The floor is some sort of metal, and you notice that there is sheets of conductive mesh hanging from the low ceiling. There are five cars parked haphazardly in the far corner."
					};
				break;
				case 2:
					strTemp = new String[] {
						"You" +a+ " are back with the bumper cars. One of the lights above buzzes, and then flickers out. Then it flickers back on."
					};
				break;
				case 3:
					strTemp = new String[] {
						"You" +a+ " are back with the bumper cars. One of the lights above flickers and pops. For a second, your eye catches some movement over by the bumper cars."
					};
				break;
				default:
					strTemp = new String[] {
						"You" +a+ " are back with the bumper cars. One of the lights above crackles, and then flickers. You notice that it makes your shadow dance around."
					};
				break;
			}
		
			return strTemp;
		}
	});

	BumperCars.setOtherRooms(
	MerryGoRound, PortaJon, TicketBooth,
	"Merry-Go-Round","Take a spin on the merry-go-round.",
	"Porta-Jon","The tacky name speaks for itself.",
	"Ticket Booth","Go to the ticket booth."
	);
	
	BumperCars.setStuff(
	new ArrayList<Item>() {{ 
		add(iBumperCar1);
		add(iBumperCar2);
		add(iBumperCar3);
		add(iCoatHanger);
	}},
	new ArrayList<Person>()
	);
	
	
	// Room: TicketBooth --------------------------------------------------------------------------------
	TicketBooth.setDescription(
	"Ticket Booth",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
		
			if (You.areWith(pMelissa)) { 
				a=" and Mel"; 
				b= "Mel pushes her finger under the glass";
			} 
			else { 
				a=""; 
				b=" You squeeze your finger through the little gap, pressing on the sign,";
			}
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
		
			switch (numberOfVisits){
				case 1:
					strTemp = new String[] {
						"The ticket booth is a small building, about 8' square. It has a large thick glass at the front, facing the gate that keeps this park from the rest of the world. There is a counter below the glass, which juts out to make a little shelf, useful for pushing cash under the window and receiving tickets in return. There is a little piece of siding with the painted word, \"Closed,\" sitting on the counter leaning against the opposite side of the glass. The sign conveys the obvious, and also serves to block the little gap below the window and above the counter." +b+ " and it falls backward into the booth."
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

	TicketBooth.setOtherRooms(
	MerryGoRound, CottonCandyStand, BumperCars,
	"Merry-Go-Round","Visit the carousel.",
	"Cotton Candy Stand","If you are searching for gross confection, he it is.",
	"Bumper Cars","Check out the bumper cars."
	);
	
		// Room: ROOM ----------------------------------------------------------------------------------------
	TicketBoothInside.setDescription(
	"Inside the Ticket Booth",
	new Room.Description() {
		public String[] make(int numberOfVisits) {
			String strTemp[];
			String a,b,c,d;
	
			if (You.areWith(pMelissa)) {a=" and Mel"; } else { a=""; }
			// if (You.areWith(PERSON)) { ACTION; } else { ACTION; }
			// if (You.have(ITEM)) { ACTION; } else { ACTION; }
			// if (ROOM.hasItem(ITEM) { ACTION; } else { ACTION; }
			// if (ROOM.hasPerson(PERSON) { ACTION; } else { ACTION; }
			// if (ITEM.isOpen()) { ACTION; } else { ACTION; }			// useful for doors
			// if (ITEM.isOn()) { ACTION; } else { ACTION; }
			// if (comingFrom(ROOM)) { ACTION; } else { ACTION; }
			// move NPC to another room: PERSON.goesTo(ROOM,You); 
		
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

// End rooms
}

// End class DaveRooms
}


