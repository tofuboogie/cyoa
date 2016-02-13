package dtz.cyoa;

import java.util.ArrayList;

public final class Globals {

	interface Callback {
		public String updateState();	 		// String returned must describe any changes made
	}

	private static int clock;  			 		// increments after every action
	public static ArrayList<Callback> listener; // array of functions to check game state after every action

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

}
