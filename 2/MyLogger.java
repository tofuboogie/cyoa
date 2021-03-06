package dtz.cyoa;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.io.*;

public final class MyLogger {

	private static Logger logger = Logger.getLogger("Cyoa");
	private static FileHandler fh;
	
	public static void log(String str){
	
		try{
			fh = new FileHandler("tmp/log",true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
		}
		catch (SecurityException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	
		logger.log(Level.ALL,str + "\n");
	}
	
	/*
	String str = "";
	for (StackTraceElement ste : Thread.currentThread().getStackTrace()){
		str += ste.getClassName() + ":" + ste.getMethodName() + ":" + ste.getLineNumber() + "\n";
	}	
	*/
}
		

