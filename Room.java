package dtz.cyoa;

import java.util.ArrayList;
import java.io.*;

public class Room implements Serializable{
	
	interface Callback {
		public void update(int numberOfVisits);
	}
	
	interface Description {
		public String[] make(int numberOfVisits);
	}

	// fields
	public String _name;
	public transient Description _description;			
	public Room _previous;			
	public Room _next1;				
	public Room _next2;				
	public Room _next3;
	public String _btn1Label, _btn2Label, _btn3Label; 	// text on the button itself
	public String _btn1Txt, _btn2Txt, _btn3Txt;			// short sentence next to the button
	public ArrayList<Item> _items; 
	public ArrayList<Person> _persons;
	private int _numberOfVisits;
	public transient Callback _callback;
	private boolean _isDoor;
	
	public String getItems(int withNums, boolean includeDoors) {					
		String itemsDescString = "";
		if (_items != null && _items.size() > 0){		// there are items in the room
			for (int i = 0; i < _items.size(); i++) {
				if (_items.get(i)._quiet == false && withNums < 0 && includeDoors==false) {
					String is = _items.get(i)._is_are;
					itemsDescString += "A " + _items.get(i)._name + " " + is + " here.\n"; 
				}
				else if (withNums >= 0 && includeDoors==true) {
					itemsDescString += "\t\t(" + (i+withNums) + ") " + _items.get(i)._name + "\n";
				}
				else if (withNums >= 0 && includeDoors==false) {
					if (_items.get(i)._isDoor==false){
						itemsDescString += "\t\t(" + (i+withNums) + ") " + _items.get(i)._name + "\n";
					}
				}
			}
		}
		return itemsDescString;
	}
	
	public int itemCount(){
		if (_items != null && _items.size() > 0){
			return this._items.size();
		}
		else {
			return 0;
		}
	}
	
	public String getPersons(int withNums) {
		String personsDescString = "";
		if (_persons != null && _persons.size() > 0) {	// there are persons in the room
			for (int i=0;i<_persons.size();i++){
				if (withNums < 0){
					personsDescString += _persons.get(i)._name + " is here" + _persons.get(i).reportHealth(true) + ".\n";
				}
				else if (withNums >= 0) {
					personsDescString += "\t\t(" + (i+withNums) + ") " + _persons.get(i)._name + "\n";
				}
			}
		}
		return personsDescString;
	}
	
	public int personCount(){
		if (_persons != null && _persons.size() > 0) {
			return this._persons.size();
		}
		else {
			return 0;
		}	
	} 
	
	public String[] getDescription(int context){ //context: 0 for enter room, 1 for look
		String[] desc;
		String lastDescInArray;
		
		if (context == 1){
			desc = _description.make(_numberOfVisits-1);	
		}
		else {
			desc = _description.make(_numberOfVisits);
		}
		
		
		lastDescInArray = desc[desc.length-1]; // grab last str in the array to append items and persons
		lastDescInArray += "\n";
		lastDescInArray += getItems(-1,false);
		lastDescInArray += getPersons(-1);
		desc[desc.length-1] = lastDescInArray; // overwrite last array string, adding items/persons
		
		if (context == 0){
			_numberOfVisits +=1;
		}
		
		return desc;
	}
	
	public void updateState(){

		if (_callback != null) {
			_callback.update(_numberOfVisits);
		}
	}	
	
	public void makeDeadEnd() {
		this._next1 = null;				
		this._next2 = null;				
		this._next3 = null;
		this._btn1Label = "";
		this._btn2Label = "";
		this._btn3Label = "";
		this._btn1Txt = "";
		this._btn2Txt = "";
		this._btn3Txt = "";	
	}

	// default constructor
	public Room() {
		_name = "Nuttin construct\'d";
		_description = null;
		_previous = null;
		_next1 = null;
		_next2 = null;
		_next3 = null;
		_btn1Label = "";
		_btn2Label = "";
		_btn3Label = "";
		_btn1Txt = "";
		_btn2Txt = "";
		_btn3Txt = "";
		_items = new ArrayList<Item>();
		_persons = new ArrayList<Person>();
		_numberOfVisits = 1;
		_callback = null;
		_isDoor = false; 
	}
	
	public void set(String name, Description description, Room previous, Room next1, Room next2, Room next3, String btn1Label, String btn1Txt, String btn2Label, String btn2Txt, String btn3Label, String btn3Txt, ArrayList<Item> items, ArrayList<Person> persons, Callback callback, boolean isDoor) {
		this._name = name;
		this._description = description;
		this._previous = previous;
		this._next1 = next1;
		this._next2 = next2;
		this._next3 = next3;
		this._btn1Label = btn1Label;
		this._btn2Label = btn2Label;
		this._btn3Label = btn3Label;
		this._btn1Txt = btn1Txt;
		this._btn2Txt = btn2Txt;
		this._btn3Txt = btn3Txt;
		this._items = items;
		this._persons = persons;
		this._callback = callback;
		this._isDoor = isDoor;
	}
	
	public void setDescription(String name, Description description){
		this._name = name;
		this._description = description;
	}
	
	// deprecated
	public void setOtherRooms(Room previous, Room next1, Room next2, Room next3, String btn1Label, String btn1Txt, String btn2Label, String btn2Txt, String btn3Label, String btn3Txt){
		if (!Globals.restoredFromSavedState){
			this._previous = previous;
			this._next1 = next1;
			this._next2 = next2;
			this._next3 = next3;
			this._btn1Label = btn1Label;
			this._btn2Label = btn2Label;
			this._btn3Label = btn3Label;
			this._btn1Txt = btn1Txt;
			this._btn2Txt = btn2Txt;
			this._btn3Txt = btn3Txt;
		}
	}
	
	
	// setOtherRooms overload (no previous)
	public void setOtherRooms(Room next1, Room next2, Room next3, String btn1Label, String btn1Txt, String btn2Label, String btn2Txt, String btn3Label, String btn3Txt){
	
		if (!Globals.restoredFromSavedState){
			this._next1 = next1;
			this._next2 = next2;
			this._next3 = next3;
			this._btn1Label = btn1Label;
			this._btn2Label = btn2Label;
			this._btn3Label = btn3Label;
			this._btn1Txt = btn1Txt;
			this._btn2Txt = btn2Txt;
			this._btn3Txt = btn3Txt;
		}
	}	
	
	public void setStuff(ArrayList<Item> items, ArrayList<Person> persons) {
		if (!Globals.restoredFromSavedState){
			this._items = items;
			this._persons = persons;
		}
	}
	
	public void setAction(Callback callback){
		this._callback = callback;
	}
	
	/*
	public void setPrevious(Room previous){
		this._previous = previous;
	}
	*/
	
	public void setNext1(Room next1, String btn1Label, String btn1Txt){
		this._next1 = next1;
		this._btn1Label = btn1Label;
		this._btn1Txt = btn1Txt;
	}
	
	public void setNext2(Room next2, String btn2Label, String btn2Txt){
		this._next2 = next2;
		this._btn2Label = btn2Label;
		this._btn2Txt = btn2Txt;
	}
	
	public void setNext3(Room next3, String btn3Label, String btn3Txt){
		this._next3 = next3;
		this._btn3Label = btn3Label;
		this._btn3Txt = btn3Txt;
	}
	
	public boolean hasItem(Item it){
		if (_items!=null && _items.contains(it)){
			return true;
		}
		return false; 	
	}
	
	//deprecated
	public Item getItem(int index, boolean includeDoors){ 					//deprecated; always incl doors
		if (index>-1 && index<_items.size() && _items.isEmpty()==false) {	//and other non-takeables;
			int i=0;														//check the item returned
			if (includeDoors == false){
				for (i=0;i<_items.size();i++){
					if (_items.get(index)._isDoor != true) { break; }
				}
			}
			return _items.get(index+i);
		}
		else {
			return null;
		}
	}
	
	public Item getItem(int index){ 		// starts at 0			
		if (index>-1 && index<_items.size() && _items.isEmpty()==false) {	
			return _items.get(index);
		}
		else {
			return null;
		}
	}
	
	public Person getPerson(int index){
		if (index>-1 && index<_persons.size() && _persons.isEmpty()==false) {
			return _persons.get(index);
		}
		else {
			return null;
		}
	}
	
	public boolean hasPerson(Person p){
		if (_persons!=null && _persons.contains(p)){
			return true;
		}
		return false;
	}
	
	public void updatePerson(Person person, int action){
		
		switch (action) {
			case 1:			//add
				if (person != null) {
					_persons.add(person);
				}
			break;
			
			case 0:			//remove
				if (person != null && _persons!=null && _persons.contains(person)) {
					_persons.remove(person);
				}
			break;
		}
	}
	
	public void updateItem(Item item, int action){
		
		switch (action) {
			case 1:			//add
				if (item != null) {
					_items.add(item);
				}
			break;
			
			case 0:			//remove
				if (item != null && _items!=null && _items.contains(item)) {
					_items.remove(item);
				}
			break;
		}
	}
	
	public void makeIntoAdoor() {
		_isDoor = true;
	}
	
	public boolean isAdoor() {
		return _isDoor;
	}
	
	public boolean isEmpty(){
		if (_items == null || _items.size() == 0) { return true; }
		for (int i=0; i < _items.size(); i++){
			if (_items.get(i)._isDoor == false) { return false; } // ignore doors
		}
		return true;
	} 

}
