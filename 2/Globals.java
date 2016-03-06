package dtz.cyoa;

import java.util.ArrayList;
import java.io.*;

public final class Globals implements Serializable{

	interface Callback {
		public String updateState();	 		// String returned must describe any changes made
	}

	private static int clock;  			 		// increments after every action
	public static ArrayList<Callback> listener; // array of functions to check game state after every action
	public static boolean restoredFromSavedState; // if true, don't run state inits

	public static void updateTime(){
		clock++;
	}
	
	public static int getTime() {
		return clock;
	}
	
	public static String check(){	// checks game state. Runs all the callbacks, updates state, and returns a String describing any changes made
		String str = "";
		if (listener != null && listener.size()>0){
			for (int i = 0; i<listener.size(); i++){
				str += listener.get(i).updateState() + "\n";
			}
		}
		return str;
	}
	
	public static void addListener(Callback callback){
		listener.add(callback);
	}
	
	public static void save(String filename, Rooms rooms){
		try {
			File file = new File("tmp/" + filename);
			boolean fileCreated = file.createNewFile();
			FileOutputStream fs = new FileOutputStream(file);
			ObjectOutputStream os = new ObjectOutputStream(fs);
					
			//os.writeObject(rooms.You);
			os.writeObject(rooms);
			
			os.close();
			fs.close();
			System.out.println("saved successfully.");
		}
		catch(Exception e){
			System.out.println(e.toString() + " : save failed.");
		}
	}
	
	public static Rooms restore(String filename){
		try {
			File file = new File("tmp/" + filename);
			FileInputStream fs = new FileInputStream(file);
			ObjectInputStream os = new ObjectInputStream(fs);
		
			Rooms rooms = (Rooms) os.readObject();
			//layer you = (Player)os.readObject();

			restoredFromSavedState = true;
			rooms.initAfterRestore();

			os.close();
			fs.close();
			System.out.println("restored successfully.");
			return rooms;
		}	
		catch (Exception e){
			System.out.println(e.toString() + " : restore failed.");
			e.printStackTrace();	
		}
		return null;
	}	

}
