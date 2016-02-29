package dtz.cyoa;

import java.util.ArrayList;
import java.io.*;

public class Player implements Serializable {

	public String _name;
	public ArrayList<Person> _companions;
	public ArrayList<Item> _items;
	public ArrayList<Item> _wearing;
	public int _health;
	public boolean _dead;
	public String _description;
	
	public Player(){
		_name = "You";
		_companions = new ArrayList<Person>();
		_items = new ArrayList<Item>();				//{ {add(iYourself);} }
	 	_wearing = new ArrayList<Item>();
		_health = 20;
		_dead = false;
		_description = "";
	}
	
	public void setDescription(String txt, boolean inclHealth, boolean inclWearing){
		txt += "\n";
		if (inclHealth){
			txt += getHealth();
		}
		if (inclWearing){
			txt += getWearing();
		}
		_description = txt;
	}
	
	public String getHealth(){
		if (_health > 30) { return "You are feeling amazing. Whatever you are doing to improve your health, please write a book about it!\n"; }
		else if (_health > 15) { return "Your health is good.\n"; }
		else if (_health > 10 ) { return "You are feeling OK.\n"; }
		else if (_health > 5 ) { return "You are sick. Ugh. Its getting harder and harder to move about.\n"; }
		else if (_health > 2 ) { return "You extremely ill. The thin wraith of death is hovering nearby.\n"; }
		else { return "You are are on your deathbed.\n"; }
	}	
	
	public String getWearing(){
		String str = "You are wearing";
		if (_wearing != null && _wearing.size()>0){
			for (int i=0;i<_wearing.size();i++){
				str += " " + _wearing.get(i)._name;
				if (i == _wearing.size()-1) {
					str += ".\n";
				}
				else {
					str += ",";
				}
			}
		}
		else { str = ""; }
		return str;
	}
	
	public String take(Item item, Room room){
		if (item == null) { return "No item matches that number!\n"; }
		else {
			updateItem(item,1);
			room.updateItem(item,0);
			item._takenStartTime = Globals.getTime();
			return "Taken.\n";
		}
	}
	
	public void updatePerson(Person companion, int action){
		
		switch (action) {
			case 1:			//add
				if (companion != null) {
					_companions.add(companion);
				}
			break;
			
			case 0:			//remove
				if (companion != null && _companions!=null && _companions.contains(companion)) {
					_companions.remove(companion);
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
	
	public void updateWearing(Item item, int action){
		
		switch (action) {
			case 1:			//add
				if (item != null) {
					_wearing.add(item);
				}
			break;
			
			case 0:			//remove
				if (item != null && _wearing!=null && _wearing.contains(item)) {
					_wearing.remove(item);
				}
			break;
		}
	}
	
	public String use(Item item, Item itemTarget, Room inRoom){	// returns success/fail statement
		if (this.have(item) && itemTarget._requires == item) {
			itemTarget._requirementMet = true;
			if (itemTarget._isDoor) { itemTarget.makeOpen(); }
			inRoom.updateState();
			return itemTarget._requirementMetText;
		}
		else {
			return "You use the " + item + "on the " + itemTarget + "without success";
		}
	}

	public Item get(int index){ // starts at 1
		index = index-1;
		if (index > -1 && index < _items.size()) {
			return _items.get(index);
		}
		else {
			return null;
		}
	}
	
	public Person getPerson(int index){
		if (index > -1 && index < _companions.size()) {
			return _companions.get(index);
		}
		else {
			return null;
		}
	}
	
	public Item getClothing(int index){
		if (index > -1 && index < _wearing.size()) {
			return _wearing.get(index);
		}
		else {
			return null;
		}
	}
	
	
	public int haveNumberOfItems(){
		if (_items!=null && _items.size()>0){
			return _items.size();
		}
		else {
			return 0;
		}
	}
	
	public int wearingNumberOfItems(){
		if (_wearing!=null && _wearing.size()>0){
			return _wearing.size();
		}
		else {
			return 0;
		}
	}	
	
	public String have(int withNums, boolean inclWearing){
		String str = "";
		int x=0;
		boolean items = false; 
		boolean clothes = false;
		if (withNums>0){ x += withNums; }
		else { x +=1; }

		if (_items!=null && _items.size()>0){
			items = true;
			for (int i=0;i<_items.size();i++){
				str += "\t\t(" + x + ") " + _items.get(i)._name + "\n";
				x +=1;
			}
		}
		
		if (inclWearing){
			if (_wearing!=null && _wearing.size()>0){	
				clothes = true;
				for (int i=0;i<_wearing.size();i++){
					str += "\t\t(" + x + ") " + _wearing.get(i)._name + "\n";
					x +=1;
				}
			
			}
		}
		
		if (!items && !clothes) {
			str+="\t\tnothing\n";
		}
		return str;	
	}
	
	
	
	
	public String inventory(){
		String str = "\tYour Health: " + _health + "\n";
		str += "\tYou are carrying:\n";
		str += this.have(0,false);
		str += "\tYou are traveling with:\n";		
		if (_companions!=null && _companions.size()>0){
			for (int i=0;i<_companions.size();i++){
				str += "\t\t- " + _companions.get(i)._name;
				if (_companions.get(i)._health > 0){
					str += " (health: " + _companions.get(i)._health + ")\n";
				} else {
					str += " (dead)\n";
				}
			}
		}
		else {
			str+="\t\tyou yourself and you";
		}
		
		return str;
	}
	
	public String areWith(int withNums){
		String str = "";
		if (_companions!=null && _companions.size()>0){
			for (int i=0;i<_companions.size();i++){
				if (withNums>0){
					str += "\t\t(" + (i+withNums) + ") " + _companions.get(i)._name + "\n";
				}
				else {
					str += "\t\t(" + (i+1) + ") " + _companions.get(i)._name + "\n";
				}
			}
		}
		return str;	
	}
	
	public int companionCount(){
		if (_companions!=null && _companions.size()>0){
			return this._companions.size();
		}
		else {
			return 0;
		}
	} 
	
	public boolean areWith(Person p){
		if (_companions!=null && _companions.contains(p)){
			return true;
		}
		return false;
	}
	
	public boolean have(Item it){
		if (_items!=null && _items.contains(it)){
			return true;
		}
		return false; 
	}
	
	public boolean areDead(){
		if (_dead == true) {
			return true;
		}
		else {
			return false;
		}
	}

}
