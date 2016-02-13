package dtz.cyoa;

public class Person {
	
	public String _name;
	public String _description;	
	public int _health;
	public boolean _dead;

	public Person(){
		_name = "No name";
		_description = "The person with no name";
		_health = 20;
		_dead = false;
	}
	
	public void set(String name,String description){
		this._name = name;
		this._description=description;
	}
	
	public String reportHealth(boolean withParens){
		if (withParens == false){
			if (_dead == true) { return "dead"; }
			if (_health < 15) { return "looking a little sick"; }
			else if (_health < 10) { return "looking very ill"; }
			else if (_health < 5) { return "looking extremely unhealthy"; }
			else if (_health < 2) { return "on the brink of death"; }
			return "";
		}
		else {
			if (_dead == true) { return "(dead)"; }
			if (_health < 15) { return "(looking a little sick)"; }
			else if (_health < 10) { return "(looking very ill)"; }
			else if (_health < 5) { return "(looking extremely unhealthy)"; }
			else if (_health < 2) { return "(on the brink of death)"; }
			return "";
		}
	}
	
	
	
	public boolean isDead(){ //check
		if (_health <= 0) {_dead=true;}
		if (_dead == true){
			return true;
		} else {return false;}
	}
	
	public void isDead(boolean TF){ //set
		_dead = TF;
	}

}

