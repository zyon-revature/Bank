package zyon.common.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {
	
	private static final Logger logger = LogManager.getLogger(DBUtil.class);
	
	private static DBUtil _instance;
	private Connection conn = null;
	
	private DBUtil() {
		
	}
	
	public static DBUtil getInstance() {
		if(_instance == null) {
			_instance = new DBUtil();
		}
		return _instance;
	}
	
	public Connection getConnection() throws Exception {
		if(this.conn == null) {
			
			String configFilePath = "/Users/zyonsavery/Documents/Java/JavaDB/zyon.banking/src/main/resources/config.proprties";
			
			logger.info("Reading config file in location: "+configFilePath);
			
			try(FileInputStream fis = new FileInputStream(configFilePath)){
				Properties props = new Properties();
				props.load(fis);
				
				String URL = (String) props.get("db_url");
				String USERNAME = (String) props.get("db_user");
				String PASSWORD = (String) props.get("db_password");
				
				this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
 
				
			} catch (Exception e) {
				logger.warn("Unable to get database connection",e);
				throw e;
			}
			
		}
		return this.conn;
	}

}