package dtz.cyoa;

public class Items extends Rooms{

	public Items() {
	}

	public void initMemory(){
	
		iYourself = new Item();
		iLoveLocks = new Item(); iLoveLockKey = new Item(); iThingamabob = new Item();
		iCrowbar = new Item(); iMaintShedFD = new Item(); iTissue = new Item();
		iChairSwingGrating = new Item(); iChairSwingDoor = new Item(); iChairSwingLever = new Item();
		iChairSwingDoorHandle = new Item(); iChairSwingTowerNearTopDoor = new Item();
		iFlashlight = new Item(); iTshirt = new Item();
	
	}	

		
	public void initializeItems() {
		
		/*
		<Item object>.set(String name, String description, String is_are, Item requires,
					String requirementMetText, boolean quiet, boolean open, boolean isDoor); 
					
		<Item object>.addAction(
			Object itemTarget, 
			new String[] { "ACTIONDESC1", "ACTIONDESC2", "ACTIONDESC3", ... }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						case 1: 	
						break;
						case 2: break;
						case 3: break;
						default: 
						break;
					}
					return str;
				}
			}
		);
			
		*/
		iYourself.set("yourself",
			"You, and everything that makes you you. Your eyes, ears, mouth, hands, feet. The things you feel with. The things you push and pull with. You, indefatiguably you.", 
			"is", null, "",false,false,false);
		iLoveLocks.set("love locks",
			"The locks are happiest when they\'re together.","are", iLoveLockKey,
			"The love locks are both unlocked.",false,false,false);
		iLoveLockKey.set("love lock key",
			"An ordinary key, with a painted purple heart on the bow","is",null,"",false,false,false);
		iThingamabob.set("thingamabob",
			"Just another damn thingamabob. It doesn't quite fit in any of your pockets and is uncomfortable to hold","is",null,"",false,false,false);
		iCrowbar.set("crowbar", "A handy metal crowbar, about 14 inches long", 
					"is", null,"", false, false,false);
		iMaintShedFD.set("Maintenance Shed Front Door", "A heavy wooden door", "is", iCrowbar,
					"You have pryed the door open.", true, false, true);
					
		//Chair Swing Tower items			
		iChairSwingDoor.set("hatch door","A heavy steel door, which looks like it belongs in a submarine. It is a small rectangle with rounded corners, with a steel wheel in the center",
					"is",null,"",false, false, true);
		iChairSwingDoorHandle.set("hatch wheel","A steel wheel in the center of the hatch door. It has four thick spokes, and is a mixture of flaking olive paint and ruddy rust",
					"is",null,"",false, false, false);	
		iChairSwingGrating.set("grating","A heavy stainless steel grating, whose cross-supports make a grid of a few hundred rectangles, each about two finger-widths wide.",
					"is",null,"",false,false,true);	
		iChairSwingTowerNearTopDoor.set("roof hatch","A thin square metal door on the ceiling. It has a lever on the right side, which rattles when the wind blows outside."); 
		iChairSwingTowerNearTopDoor.makeIntoAdoor(iYourself,ChairSwingTowerNearTop, 		ChairSwingTowerOnTop, "Up","Go to the roof");
		
		
		iFlashlight.set("flashlight",
			"An old metal flashlight with fluted indentions running its length. It's weight feels good in your hand.", "is", null,"",false,false,false);
		iTshirt.set("a corny t-shirt","The t-shirt is black with an airbrushed image of Yanni's face, which covers your entire chest. He is staring blissfully into the distance, as his hair blows in the wind.", "is",null,"",false,false,false); 

					
		/*
		<Item object>.set(String name, String description, String is_are, Item requires,
					String requirementMetText, boolean quiet, boolean open, boolean isDoor); */
					
					
				
		// BEGIN iYourself ------------------------------------------------------------------------------
		iYourself.addAction(
			iChairSwingGrating, 
			new String[] { "You pull on the grating and it budges. You pull harder and get it to rise up on one side, and you slide it a little to the right before releasing it and stumbling forward a little. \"Whew,\", you say, as you stand up straight to catch your breath.", "You pull on the grating again and this time you slide it all the way over onto the adjoining grass, exposing a service hole." }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						case 1: // do nothing
						break;
						case 2: 
							ChairSwing.setNext2(ChairSwingUnderGrating, "Service Tunnel", "Enter the hole.");
						break;
						default: 
							str += "You've already moved the grating. Moving it again would only risk throwing out your back.";
						break;
					}
					return str;
				}
			}
		);
		
		iYourself.addAction(
			iChairSwingDoorHandle, 
			new String[] { "You turn the handle with great effort, a quarter turn counter-clockwise. It clicks, which sounds to you like it means that it is unlocked."}, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						case 1: 
								iChairSwingDoorHandle._requirementMet = true;
						break;
						default: 
						break;
					}
					return str;
				}
			}
		);
		
		
		
		// END iYourself ----------------------------------------------------------------------------------
			
		iCrowbar.addAction(
			iChairSwingDoor, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						default: 
							if (iChairSwingDoorHandle._requirementMet == true){
								str += "You pry open the rusty hatch door. Nice job.";
								iChairSwingDoor._requirementMet = true;
								iChairSwingDoor._open = true;
								ChairSwingUnderGrating.setNext1(ChairSwingTower, "Tower","Enter the tower");
								
							}
							else {
								str += "You pry and pry, but the door doesn't budge. Maybe it's not unlocked?";
							}
						break;
					}
					return str;
				}
			}
		);
			
					
		iTissue.set("Tissue","A light and fluffy tissue, multiuseful","is",null,"",false,false,false);
		iTissue.addAction(
			pMelissa, 
			new String[] {
				"You give the tissue to Mel, and she blows her nose. Thank you.",
				"You give another tissue to Mel, despite her protestations. She drops it in the mud.",
				"You give yet another tissue to her. She eats it, and then says, \"Yum. Vanilla.\"",
				"Melissa takes the tissue and blows her nose really hard."
			},
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						case 1: 	
							pMelissa._health -= 5;
						break;
						case 2: pMelissa._health -= 5; break;
						case 3: pMelissa._health -= 5; break;
						default: pMelissa._health -=1; break;
					}
					
					if (pMelissa.isDead()) {
						str += "Mel has depleted her oxygen from overblowing, and died.";
						You.updatePerson(pMelissa,0);
						currentRoom.updatePerson(pMelissa,1);
					}
				return str;	
				}
			}
		);

		iYourself.addAction(
			iFlashlight, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						default:
							if (iFlashlight.toggleOnOff() == 1) {
								str += "You have turned the flashlight on.";
							}
							else { str+="You turn the flashlight off.";}
						break;
					}
					return str;
				}
			}
		);


	// End item definitions
	}
// End Items class
}

