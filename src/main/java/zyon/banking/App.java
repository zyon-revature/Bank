package zyon.banking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zyon.client.ConsoleApp;



public class App {
	
	public static final Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String[] args) {
		ConsoleApp console = new ConsoleApp();
		logger.info("Starting Application ...");
		console.start();
		logger.info("Application Exited");
		
		
	}

}


