package dtz.cyoa;
import java.io.*;

public class Persons extends Rooms implements Serializable {

	public Persons() {
	}
	
	public void initMemory(){
		pMelissa = new Person();
	}
	
	public void initializePersons() {
	
		/*
		<Person object>.set(String name,String description) */
		pMelissa.set("Melissa","Melissa, your found long-lost half-sister.");
	}
}
