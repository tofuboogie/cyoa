package dtz.cyoa;
import java.util.Scanner;
import java.util.*;


public class Main {

// need a way to relook at current room, and look at things
// make room run callback when you enter it AND when you LOOK at it (so a flashlight can make new things seeable without haveing to leave and reenter)
	
	static Scanner stdin = new Scanner(System.in);
	static Rooms allRooms = new Rooms();
	static Room currentRoom;
	static int dir = 0;                                //4,1,2,3,5,9  prv,rm1,rm2,rm3,more,quit
	static boolean yes = true;
	static String boldBlue = "\033[01;34m";
    static String boldGreen = "\033[01;32m";
    static String boldYellow = "\033[01;33m";
    static String boldMagenta = "\033[01;35m";
    static String boldRed = "\033[01;31m";
    static String boldCyan="\033[01;36m";
    static String normal = "\033[00;00m";

	public static void main(String[] args) {
	
		allRooms.init();
		currentRoom = allRooms.Begin;
		clearScreen();
		printRoom(currentRoom,0,null);
		
		while(yes) {
			yes = input();
		}
	}
	
	private static String cmdText(){
		String str = "Next(1) - Next(2) - Next(3) - Prev(4) - More(5) - Inventory(6) - Use(7) - Take(8) - Exit(9) - Look(10)\n";
		return str;
	}
	
	private static boolean input() {
		Globals.updateTime();	
        
        if (dir == 0) {
		    System.out.print(boldRed+"Enter a command:"+normal); 
		    try {   
		    	dir = stdin.nextInt();
		    }
		    catch(Exception e){
		    	System.out.println("Whoops. Numbers only please");
		    	dir = 0;
		    }
        }
        
        switch(dir){
        	case 4: 
        		if (allRooms.yesPrevious()) {
					clearScreen();
					allRooms.priorRoom = currentRoom;
		    		currentRoom = allRooms.getPrevious();
		    		allRooms.setCurrent(currentRoom);
		    		printRoom(currentRoom,0,null);
        		}
        		else {
        			System.out.println("No previous room to visit!");
        			dir = 0; 
        		}
        	break;
        		
			case 1:
				if (currentRoom._next1 != null) { 
					clearScreen();
					allRooms.setPrevious(currentRoom);
					currentRoom = currentRoom._next1;
		    		allRooms.setCurrent(currentRoom);
		    		printRoom(currentRoom,0,null);
        		}
        		else {
        			System.out.println("No next room to visit!");
        			dir = 0; 
        		}
        	break;
        		
			case 2:
				if (currentRoom._next2 != null) { 
					clearScreen();
					allRooms.setPrevious(currentRoom);
		    		currentRoom = currentRoom._next2;
		    		allRooms.setCurrent(currentRoom); 
		    		printRoom(currentRoom,0,null);
        		}
        		else {
        			System.out.println("No next room to visit!");
        			dir = 0; 
        		}
        	break;
        	
			case 3:
				if (currentRoom._next3 != null) { 
					clearScreen();
					allRooms.setPrevious(currentRoom);
		    		currentRoom = currentRoom._next3;
		    		allRooms.setCurrent(currentRoom); 
		    		printRoom(currentRoom,0,null);
        		}
        		else {
        			System.out.println("No next room to visit!");
        			dir = 0; 
        		}
        	break;
        		
			case 5:
				System.out.println("\tNothing more to say about this place.");
				dir = 0;
			break;
			
			case 6: 
				System.out.println(allRooms.You.inventory());
				dir = 0;
			break;
			
			case 7:
				int stuff = useStuff();
				//dir = 0;
			break;
			
			case 8:
				int takeStuff = takeStuff();
				//dir = 0;
			break;
			
			case 9:
				clearScreen();
				return false;	//exit
				
			case 10:
				int look = look();
				//dir = 0;
			break;
			
			case 11:
				allRooms.save("savedCyoa");
			break;
			
			case 12:
				allRooms.restore("savedCyoa");
			break;
				
			default: 
				System.out.println("\tWhoops. Try something else.");
				dir = 0;
			break;
        }
        return true;
	}
	
	private static int look(){

		System.out.println();
		System.out.println("What item would you like to look at?");
		System.out.println("\t\t(1) Yourself");
		System.out.println("\t\t(2) This room");
		System.out.print(currentRoom.getItems(3,true));
		int itemCount = currentRoom.itemCount();
		
		System.out.print(currentRoom.getPersons(itemCount+3));
		int personCount = currentRoom.personCount();
		
		System.out.print(allRooms.You.areWith(itemCount+personCount+3));
		int companionCount = allRooms.You.companionCount();
		
		System.out.println("\tYour stuff:");
		System.out.print(allRooms.You.have(itemCount+personCount+companionCount+3,true));
		int itemsHeldCount = allRooms.You.haveNumberOfItems();
		int wearingCount = allRooms.You.wearingNumberOfItems();
	
		System.out.print("Number: ");
	
		// Look at yourself
		int itemTargetNum = stdin.nextInt();
		
		if (itemTargetNum == 1) {
			String desc = allRooms.You._description;
			clearScreen();
			printOtherMessage(currentRoom, desc);
			return 0;
		}
		
		// Look at the room
		if (itemTargetNum == 2) {
			printRoom(currentRoom,1,null);
			return 0;
		}
		
		// Look at items in the room
		if (itemTargetNum>2 && itemTargetNum<itemCount+3) {
			Item itemTarget = currentRoom.getItem(itemTargetNum-3,true);
			if (itemTarget == null) {
				System.out.println("This item doesn't appear to be in the room.");
				return 2;
			}
			else {
				String desc = itemTarget._description;
				clearScreen();
				printOtherMessage(currentRoom, desc);
				return 0;
			}
		}
	
		// Look at person in the room
		if (itemTargetNum >= itemCount+3 && itemTargetNum < (itemCount+personCount+3)) {
			Person personTarget = currentRoom.getPerson(itemTargetNum-itemCount-3);
			if (personTarget == null) {
				System.out.println("This person doesn't appear to be in the room.");
				return 2;
			}
			else {
				String desc = personTarget._description;
				clearScreen();
				printOtherMessage(currentRoom, desc);
				return 0;
			}
		}

		// Look at companion
		if (itemTargetNum >= (itemCount+personCount+3) && itemTargetNum < (itemCount+personCount+companionCount+3)) {
			Person personTarget = allRooms.You.getPerson(itemTargetNum-itemCount-personCount-3);
			if (personTarget == null) {
				System.out.println("This person doesn't appear to be with you.");
				return 2;
			}
			else {
				String desc = personTarget._description;
				clearScreen();
				printOtherMessage(currentRoom, desc);
				return 0;
			}
		}
		
		// Look at your stuff
		if (itemTargetNum >= (itemCount+personCount+companionCount+3) && itemTargetNum < (itemCount+personCount+companionCount+itemsHeldCount+3)) {
			Item stuffTarget = allRooms.You.get(itemTargetNum-itemCount-personCount-companionCount-2);
			if (stuffTarget == null) {
				System.out.println("You don't appear to have this");
				return 2;
			}
			else {
				String desc = stuffTarget._description;
				clearScreen();
				printOtherMessage(currentRoom, desc);
				return 0;
			}
		}
		
		// Look at what you're wearing
		if (itemTargetNum >= (itemCount+personCount+companionCount+itemsHeldCount+3) && 
		itemTargetNum < (itemCount+personCount+companionCount+itemsHeldCount+wearingCount+3)) {
			Item clothingTarget = 
			allRooms.You.getClothing(itemTargetNum-itemCount-personCount-companionCount-itemsHeldCount-3);
			if (clothingTarget == null) {
				System.out.println("You don't appear to be wearing this");
				return 2;
			}
			else {
				String desc = clothingTarget._description;
				clearScreen();
				printOtherMessage(currentRoom, desc);
				return 0;
			}
		}
	
		else {
			String response = "Hmm...I can't find a target with that number";
			clearScreen();
			printOtherMessage(currentRoom, response);
			return 1;
		}		
	}
	
	private static int takeStuff(){
		if (currentRoom.isEmpty() == false){
			System.out.print("Available to take:\n");
			System.out.print(currentRoom.getItems(1,false));	
			System.out.print("Number: ");
			int itemGetNum = stdin.nextInt();
			Item item = currentRoom.getItem(itemGetNum-1);
			if (item == null) {
				System.out.println("This doesn't appear to be in the room.");
				return 1;
			}
			else if (item._isDoor == true) {
				System.out.println("Try as you might, you cannot take a door or other portal.");
				return 1;				
			}
			else {
				printOtherMessage(currentRoom, allRooms.You.take(item,currentRoom));
				return 0;
			}
		}
		else {
			System.out.println("There aren't any items in the room to take.");
		}
		return 1;
	}
	
	private static int useStuff(){
		if (allRooms.You.haveNumberOfItems() > 0) {
			System.out.print("Available to use:\n");
			System.out.print(allRooms.You.have(0,false));
			System.out.print("Which item would you like to use?\nNumber: " );
			int itemNum = stdin.nextInt();
			Item item = allRooms.You.get(itemNum);
			if (item == null) {
				System.out.println("You don't appear to be holding this."); // no esc needed for apostrophe?
				return 1;
			}
		
			System.out.println();
			System.out.println("What item would you like to use the "+ item._name +" on?");
			System.out.println("  Available items in the room:");
			System.out.println("\t\t(1) Unused");
			System.out.print(currentRoom.getItems(2,true));
			int itemCount = currentRoom.itemCount();
			System.out.print(currentRoom.getPersons(itemCount+2));
			int personCount = currentRoom.personCount();
			System.out.print(allRooms.You.areWith(itemCount+personCount+2));
			int companionCount = allRooms.You.companionCount();
			System.out.println("  Your stuff:");
			System.out.print(allRooms.You.have(itemCount+personCount+companionCount+2,true));
			int itemsHeldCount = allRooms.You.haveNumberOfItems();
			int wearingCount = allRooms.You.wearingNumberOfItems();
		
			System.out.print("Number: ");
		
			// Use on yourself
			int itemTargetNum = stdin.nextInt();
			/*
			if (itemTargetNum == 1) {
				String response = allRooms.You.use(item,You,currentRoom);	// stub
				clearScreen();
				printOtherMessage(currentRoom, response);
				return 0;
			}
			*/
		
			// Use item on item
			if (itemTargetNum>1 && itemTargetNum<itemCount+2) {
				Item itemTarget = currentRoom.getItem(itemTargetNum-2,true);
				if (itemTarget == null) {
					System.out.println("This item doesn't appear to be in the room.");
					return 2;
				}
				else {
					//String response = allRooms.You.use(item, itemTarget, currentRoom);
					String response = item.useOn(itemTarget);
					clearScreen();
					printOtherMessage(currentRoom, response);
					return 0;
				}
			}
		
			// Use item on person
			if (itemTargetNum >= itemCount+2 && itemTargetNum < (itemCount+personCount+2)) {
				Person personTarget = currentRoom.getPerson(itemTargetNum-itemCount-2);
				if (personTarget == null) {
					System.out.println("This person doesn't appear to be in the room.");
					return 2;
				}
				else {
					//String response = allRooms.You.use(item, personTarget, currentRoom);
					String response = item.useOn(personTarget);
					clearScreen();
					printOtherMessage(currentRoom, response);
					return 0;
				}
			}

			// Use item on companion
			if (itemTargetNum >= (itemCount+personCount+2) && itemTargetNum < (itemCount+personCount+companionCount+2)) {
				Person personTarget = allRooms.You.getPerson(itemTargetNum-itemCount-personCount-2);
				if (personTarget == null) {
					System.out.println("This person doesn't appear to be with you.");
					return 2;
				}
				else {
					//String response = allRooms.You.use(item, personTarget, currentRoom);
					String response = item.useOn(personTarget);
					clearScreen();
					printOtherMessage(currentRoom, response);
					return 0;
				}
			}
			
			// Use item on your stuff
			if (itemTargetNum >= (itemCount+personCount+companionCount+2) && itemTargetNum < (itemCount+personCount+companionCount+itemsHeldCount+2)) {
				Item stuffTarget = allRooms.You.get(itemTargetNum-itemCount-personCount-companionCount-1);
				if (stuffTarget == null) {
					System.out.println("You don't appear to have this");
					return 2;
				}
				else {
					String response = item.useOn(stuffTarget);
					clearScreen();
					printOtherMessage(currentRoom, response);
					return 0;
				}
			}
			
			// Look at what you're wearing
			if (itemTargetNum >= (itemCount+personCount+companionCount+itemsHeldCount+2) && 
			itemTargetNum < (itemCount+personCount+companionCount+itemsHeldCount+wearingCount+2)) {
			Item clothingTarget = 
			allRooms.You.getClothing(itemTargetNum-itemCount-personCount-companionCount-itemsHeldCount-2);
				if (clothingTarget == null) {
					System.out.println("You don't appear to be wearing this");
					return 2;
				}
				else {
					String response = item.useOn(clothingTarget);
					clearScreen();
					printOtherMessage(currentRoom, response);
					return 0;
				}
			}
		
			else {
				String response = "Hmm...I can't find a target with that number";
				clearScreen();
				printOtherMessage(currentRoom, response);
				return 0;
			}
		}
		return 1;
	}
	
	private static int innerLoopInput(int innerOpt) {	// 0 all out, 1 first loop, 2 second loop
        
        if (innerOpt < 5 || innerOpt == 9) { 
        	dir = innerOpt;
        	return 0; 
        }
        
        Globals.updateTime();
        
        switch(innerOpt){
			case 5: 
			dir = innerOpt;
			return 1;
			
			case 6:
				System.out.println(allRooms.You.inventory());
			return 2;
			
			case 7:
				int stuff = useStuff();					
				if (stuff > 0) return 2;
			return 0;
			
			case 8:
				int takeStuff = takeStuff();
				if (takeStuff > 0) return 2;
			return 0;
			
			case 10:
				int look = look();
				if (look > 0) return 2;
			return 0;
			
			case 11:
				allRooms.save("savedCyoa");
			return 2;
			
			case 12:
				allRooms.restore("savedCyoa");
			return 2;
				
			default:
				System.out.println("\tWhoopsie. Try something else.");
			return 2;
        }
	}
	
	private static void clearScreen(){
		try {
			//Runtime.getRuntime().exec("clear");
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		catch (Exception e){
			System.out.println("didn't clear.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static void printWraps(String str){
		String [] lines = TextUtils.wrapText(str,100);
		for (int a=0; a<lines.length; a++){
			System.out.println(lines[a]);
		}
	}
	
	private static void printRoom(Room room, int context, String[] extraText) {
	    room.updateState();
	    
		/*
		String txt1;
	    if (extraText != null) {
	    	txt1 = extraText[0];
	    } else {txt1 = "";}
	    */
	    
	    String[] str;
	    str = room.getDescription(context);
	    int strLen = str.length;


	    for (int i=0;i<strLen;i++) { 
			clearScreen();
			System.out.println(boldBlue+ "\"" +room._name + "\"" + normal);
			System.out.println();
			printWraps(str[i]);

			if (strLen>1 && i<strLen-1) {
                System.out.print("\n");
                System.out.println(boldYellow+"<MORE>"+normal);
            }

			System.out.println("-----------------------------------------------------------------------------------------------------\n");
			if (!allRooms.You.areDead()) {
				int x = printButtons(room, extraText);
				if (x==0) break;
			}
			else { 
				int temp;
				System.out.print(boldRed+"Enter any number:"+normal); 
				temp = stdin.nextInt();
			}
	    }
	    
	    if (allRooms.You.areDead()) {
			int temp = 0;
			System.out.println();System.out.println();System.out.println();
			System.out.println("\t\t\t" + boldGreen + "** You have perished, completely and forevermore ** " + normal);
			System.out.print("\t\t\t             Press any number to exit: ");
			temp = stdin.nextInt();
			dir = 9;
	    }
	}
	
	public static int printButtons(Room room, String[] extraText){
		
		String buttonText = "";
        if (allRooms.yesPrevious()) {
        	System.out.println(boldCyan+"<< Previous"+normal);
        	System.out.println();
        }
        if (room._next1 != null) {
        	System.out.println(boldGreen+"Next(1): "+ room._btn1Label + normal);
        	buttonText = room._btn1Txt;
        	printWraps(buttonText);
        	System.out.println();
        }
        if (room._next2 != null) {
        	System.out.println(boldMagenta+"Next(2): "+ room._btn2Label+normal);
        	buttonText = room._btn2Txt;
        	printWraps(buttonText);
        	System.out.print("\n");
        }
        if (room._next3 != null) {
        	System.out.println(boldBlue+"Next(3): "+ room._btn3Label+normal);
        	buttonText = room._btn3Txt;
        	printWraps(buttonText);
        	System.out.println("\n");
        }
        
        System.out.print(cmdText());
        
        int innerSentry = 2;
        int innerOpt;
        while (innerSentry == 2) {					// inner input loop
			System.out.print(boldRed+"Enter a command:"+normal);  
        	innerOpt = stdin.nextInt();
        	innerSentry =  innerLoopInput(innerOpt);
        }
        
        return innerSentry;
    }
        
   	public static void printOtherMessage(Room room, String msg){	// eg., item used successfully
		clearScreen();
		System.out.println();
		printWraps(msg);
		System.out.println("-----------------------------------------------------------------------------------------------------\n");
		int x = printButtons(room, new String[1]);
	}	

}
	

                                                                                       

