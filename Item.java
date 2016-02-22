package dtz.cyoa;
import java.util.ArrayList;
import java.io.*;

public class Item implements Serializable {

	public String _name;					// eg, lock
	public String _description;
	public String _is_are;					// "is" for singular name; "are" for plural
	public Item _requires;					// eg, key1
	public boolean _requirementMet;
	public String _requirementMetText;		// eg, "The padlock is unlocked."
	public boolean _quiet;					// keep scenery, like doors, from announcing themselves
	public boolean _open;
	public boolean _isDoor;					// is the item a door?
	public transient ArrayList<Action> _actions;		// array of possible targets and actions on targets
	public int _switchedOnStartTime;		// mark time item is turned on
	public int _switchedOnTotalTime;	
	public int _takenStartTime;				// mark time the item is taken
	
	
	interface Callback {
		public String update(int numberOfTries);
		// use -1 to indicate action to take when requirement met
	}

	class Action {
		private int numberOfTries;
		public Object itemTarget;
		public String[] actionText;
		public Callback callback;	
		
		public Action(Object target, String[] text, Callback callbk){
			numberOfTries = 1;
			itemTarget = target;
			actionText = text;
			callback = callbk;
		}
	}
	
	public void addAction(Object itemTarget1, String[] actionText1, Callback callback1){
		_actions.add(new Action(itemTarget1, actionText1, callback1));
	}
	
	public String useOn(Object target) {
		
		if (target instanceof Item) {
			target = (Item)target;
			Item itemTarget = (Item)target;
			
			if (itemTarget._requirementMet == true){
				return "Using " + this._name + " on the " + itemTarget._name + " again does nothing.";
			}
			
			if (itemTarget._requires == this) {
				itemTarget._requirementMet = true;
				if (itemTarget._isDoor) { itemTarget._open = true; }
				for (Action act : _actions){
					if (act.itemTarget == target) {	// other actions to take when requirement met
						String tmp = act.callback.update(-1);
					}
				}
				return itemTarget._requirementMetText;
			}
		}
		else if (target.getClass() == Person.class) {
			 target = (Person)target;
		}
		else if (target instanceof Player) {
			 target = (Player)target;
		}
		
		for (Action act : _actions){
			if (act.itemTarget == target) {
				int index = 0;
				if (act.numberOfTries > act.actionText.length){
					index = act.actionText.length-1;
				}
				else{
					index = act.numberOfTries-1;
				}
				String str = act.actionText[index] +"\n";
				str += act.callback.update(act.numberOfTries);
				act.numberOfTries += 1;
				return str;
			}
		}
		return "Using " + this._name + " has no effect.";
	}
	
	public Item() {
		_name = "";
		_description = "";
		_is_are = "is";
		_requires = null;
		_requirementMet = false;		
		_requirementMetText = "";
		_quiet = false;	
		_open = false;
		_isDoor = false;
		_actions = new ArrayList<Action>();
		_switchedOnStartTime = 0;
		_switchedOnTotalTime = 0;	
		_takenStartTime = 0;	
	}
	
	public void set(String name, String description, String is_are, Item requires,
					String requirementMetText, boolean quiet, boolean open, boolean isDoor) {
		if (!Globals.restoredFromSavedState){
			this._name = name;
			this._description = description;
			this._is_are = is_are;
			this._requires = requires;
			this._requirementMetText = requirementMetText;
			this._quiet = quiet;
			this._open = open;
			this._isDoor = isDoor;
		}
	}
	
	public void set(String name, String description){
		this._name = name;
		this._description = description;
	}
	
	public void requirementMet(boolean TF){
		this._requirementMet = TF;
	}
	
	public boolean isOpen(){
		if (_open == true){
			return true;
		}
		return false;
	}
	
	public void makeOpen(){
		this._open = true;
	}
	
	public boolean isOn(){
		if (_switchedOnStartTime > 0){
			return true;
		}
		else { return false; }
	}
	
	public int toggleOnOff(){	// returns 0 for off, 1 for on
		if (_switchedOnStartTime == 0){	//item is off
			_switchedOnStartTime = Globals.getTime();
			return 1;
		}
		else {
			int timeElapsed = Globals.getTime() - _switchedOnStartTime;
			_switchedOnTotalTime += timeElapsed;
			_switchedOnStartTime = 0;
			return 0;
		}
	}
	
	public int getTotalSwitchedOnTime(){
		int timeOn = 0;
		if (_switchedOnStartTime != 0){ //item is on
			timeOn = Globals.getTime() - _switchedOnStartTime;
		}
		timeOn += _switchedOnTotalTime;
		return timeOn;
	}
	
	public void makeIntoAdoor(Item you, final Room thisRoom, final Room nextRoom, final String btn1Label, final String btn1Txt){
		this._isDoor = true;
		this._quiet = true;
		
		you.addAction(
			this, 
			new String[] { "" }, 
			new Item.Callback(){
				public String update(int numberOfTries){
					String str = "You have opened the door.";
					Item.this._open = true;
					thisRoom.setNext1(nextRoom, btn1Label, btn1Txt);
					return str;
				}
			}
		);
		
	}
	
	public void makeIntoAkeyedDoor(Item you, final Room thisRoom, final Room nextRoom, final String btn1Label, final String btn1Txt, Item key, String unlockedText){
		makeIntoAdoor(you,thisRoom,nextRoom,btn1Label,btn1Txt);
		_requires = key;					
		_requirementMetText = unlockedText;
	}
	
}
