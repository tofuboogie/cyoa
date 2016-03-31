package dtz.cyoa;
import java.io.*;

public class Items extends Rooms implements Serializable {

	public Items() {
	}

	public void initMemory(){
		for (int i=0;i<items.length;i++){
			items[i] = new Item();
		}
		Rooms_bulk.changeFieldReferences_items();
	}	

	public void initializeItems() {	
		initializeItemsActions();
		/*
		<Item object>.set(String name, String description, String is_are, Item requires,
				String requirementMetText, boolean quiet, boolean open, boolean isDoor, boolean takeable); 
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
		iChairSwingGrating.set("grating","A heavy galvanized steel grating, whose cross-supports make a grid of a few hundred rectangles, each about two finger-widths wide.",
					"is",null,"",false,false,true);	
		iChairSwingTowerNearTopDoor.set("roof hatch","A thin square metal door on the ceiling. It has a lever on the right side, which rattles when the wind blows outside."); 
		iChairSwingTowerNearTopDoor.makeIntoAdoor(iYourself,ChairSwingTowerNearTop, 		ChairSwingTowerOnTop, "Up","Go to the roof"); 
		
		iFlashlight.set("flashlight",
			"An old metal flashlight with fluted indentions running its length. It's weight feels good in your hand.", "is", null,"",false,false,false);
		iTshirt.set("a corny t-shirt","The t-shirt is black with an airbrushed image of Yanni's face, which covers your entire chest. He is staring blissfully into the distance, as his hair blows in the wind.", "is",null,"",false,false,false); 

		iBurlesqueSofa.set("sofa",
			"The sofa appears of Victorian vintage, hand-tufted red velvet with a dark mahogany frame and legs. The armrests are carved in such a way that the ends appear to roll into themselves. Behind the back-cushions, the wooden frame rises in the middle into a little arch, carved into what looks like a setting sun, flattening out at the edges.","is",true,false);
		iBurlesqueSwitch.set("switch","A simple switch with two black circular buttons. Push the top one, and bottom on pops out. Push the bottom one... you get the picture.",true,false);
		iBurlesquePiano.set("little piano","A vintage Schoenhut toy piano. It is a tiny upright, with only 22 keys, and has some simple vine and leaf stenciling on the upper panel.",true,false);
		iBurlesquePainting.set("painting","The painting is that of a semi-nude woman, dressed only in a diaphanous peignoir. She is leaning back against a large stone, set on a pitch black background, with her wrist limply pressed against her forehead.",true,false);
		iBurlesqueManikin.set("dress form","An old dress form, which looks like a headless and limbless mannequin skewered on a steel pole that rolls on a cast base with four little caster wheels. Someone painted a little red dot on one breast, and a green dot on the other. \"Christmas nipples,\" you mutter to yourself, with an ineluctable grin.",true,false);
		iBumperCar1.set("blue bumper car","A powder blue bumper car, modeled on the Model A Phaeton, mid-1930s. It has a cracked and dried out rubber surround, and a metal pole in the back that reaches up to the ceiling.",true,false);
		iBumperCar2.set("maroon bumper car","A maroon bumper car, modeled on the Graham Business Coupe, circa 1930. It has little brackets for its rubber surround, which is missing, and a metal pole in the back that is bent so that it doesn't quite make it to the ceiling.",true,false);
		iBumperCar3.set("gold bumper car","A gold bumper car, modeled on 1930s Packard roadster. Its bumper is mostly white and looks like you could flake off the dried rubber with your fingernail. A pole at the rear leads up to the ceiling, and has little wire bristles on the end.",true,false);
		iArrows.set("arrows","A bundle of 5 arrows, tied together with a black shoestring. All but one of them are so dull that their tips look like pencils whose erasers are just nubs. The fifth one, however is very sharp, and must be handled with utmost care.",false,true);
		iBasketball.set("basketball","A worn old Wilson ball. The black rubber in the ribs is fraying a bit, and although severly underinflated, it is still usable.",false,true);
		/*
		<Item object>.set(String name, String description, String is_are, Item requires,
				String requirementMetText, boolean quiet, boolean open, boolean isDoor, boolean takeable);
		<Item object>.set(String name, String description, String is_are, boolean quiet, boolean takeable) 			
		*/
	}
					
		
	public void initializeItemsActions() {	
		/*
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
					
				
		// BEGIN iYourself ------------------------------------------------------------------------------
		iYourself.addAction(
			iChairSwingGrating, 
			new String[] { "You pull on the grating and it budges. You pull harder and get it to rise up on one side, and you slide it a little to the right before releasing it and stumbling forward a little. \"Whew,\", you say, as you stand up straight to catch your breath.", "You pull on the grating again and this time you slide it all the way over onto the adjoining grass, exposing a service hole.","" }, 
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
		
		
		iYourself.addAction(
			iBurlesqueSwitch, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					switch(numberOfTries){
						default: 
							if (iBurlesqueSwitch.toggleOnOff() == 1) {
								str += "You push the button, and the other one pops out. A red light within the chandelier glows, casting light over the stage and surrounding area.";
							}
							else { str+="You push the button and turn off the red light.";}
						break;
					}
					return str;
				}
			}
		);	
		
		iYourself.addAction(
			iBumperCar1, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					String a,b,c,d;
					if (You.areWith(pMelissa)) {
						a=" Mel jumps in beside you.";
						b=" Mel exits, popping her bubblegum and stretching out the word, \"Laaaaaaaaaaaaaaaaaaaaaaaaaaaame.\"";
						c=" This time Mel doesn't join you.";
						d=" to Mel's footsteps whisking across the floor";
					} else {a="";b="";c="";d="";}
					switch(numberOfTries){
						case 1: 
							str = "You enter the Phaeton and settle in." +a+ " The steering wheel is just bare metal with two short strips of duct tape randomly wrapped around parts of it. You flick a switch on the dash, the headlights come on, and the car moves forward slowly. You turn the wheel to the left, and the car lurches slowly to the right. Then the headlights dim as you slow down inch by painful inch, and go out as you come to a full stop. One of the fluorescent bulbs overhead also goes out."+b;
						break;
						default: 
							str = "You sit back in the Phaeton."+c+" The switch is still in the 'ON' position from last time. Expecting, or at least hoping for, a miracle, you flick it off and then back on. The fossilized faux Phaeton just sits there. You sit there for a while, listening"+d+ ", and step back out.";
						break;
					}
					return str;
				}
			}
		);	
		
		iYourself.addAction( 
			iBumperCar3, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "";
					String a,b;
					if (You.areWith(pMelissa)) {
						a=" Mel jumps in beside you.";
						b=" Mel discovers a cigarette butt in the crack of the seat and jokingly tries to shove it up your nose, and then exits the car laughing a little. \"What a little roadster jokester,\" you stupidly think to yourself.";
					} else {a="";b="";}
					switch(numberOfTries){
						case 1: 
							str = "You enter the roadster and sit down." +a+ " You flick the sole switch on the dash, and surprise! Nothing happens."+b+" You exit the vehicle.";	
						break;
						default: 
							str = "You lean back over the seat and toggle the switch a few times, not bothering to get in the car. As expected, no dice, so, oh well. You walk away.";
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

		
		iYourself.addAction(
		iBasketball, 
			new String[] { "You shoot at the basket and miss. You were always more a of ping-pong pro anyhow.", "You shoot. The ball hurls through the air. Time slows down. It spins over and over, slowly, through the dense atmosphere and lands on the rim. The sphere deforms, and ever so microscopically it wraps itself around the upper edge of the rim, then regains its turgidity and bounces upward. It comes down in the same place, but slightly more toward the hole, and bounces up again, and over. It drops through the net, time normalizes, and you hear the whoosh as it slides through the net.","You shoot again and miss.","You shoot again and miss, again.","You shoot and hit the backboard hard and the ball bounces back to you.","You shooot and whang it off the rim.","You shoot, free-throw style, and nail it. Nothing but net.","You shoot. You miss. You cry a little inside.","You shoot again and miss. Your arms are getting tired, and you tuck the ball between your legs as you stretch out your shoulder.","You shoot again and miss." }, 
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


	// End item definitions
	}	
		
// End Items class
}

