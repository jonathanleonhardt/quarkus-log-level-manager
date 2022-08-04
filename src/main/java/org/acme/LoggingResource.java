package org.acme;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.hibernate.engine.jdbc.internal.Formatter;

@Path("/logging")
public class LoggingResource {

	private static Level getLogLevel(Logger logger) {
		for (Logger current = logger; current != null;) {
			Level level = current.getLevel();
			if (level != null)
				return level;
			current = current.getParent();
		}
		return Level.INFO;
	}

   @GET
   @Path("/{logger}")
   @Produces("text/plain")
   public String setLogLevel(@PathParam("logger") String loggerName, @QueryParam("level") String level) {
      Logger logger = Logger.getLogger(loggerName);
      
      if (level != null && level.length() > 0) {
         logger.setLevel(Level.parse(level));
      }
      
      return getLogLevel(logger).toString();
   }
   
   @GET
   @Path("/{logger}")
   @Produces("text/plain")
	public String saveLogToFile(@PathParam("logger") String loggerName, @QueryParam("file") String fileName) {
		Logger logger = Logger.getLogger(loggerName);
		
		if (fileName != null && fileName.length() > 0) {
			try {
				Handler handler = new FileHandler(fileName);
				logger.addHandler(handler);
			} catch (SecurityException | IOException e) {}
		}
		
		return getLogLevel(logger).toString();
	}
   
   /*
    * mudar format
    * ???
    * Arrays.stream(logger.getHandlers()).forEach(
        		 handler -> logger.removeHandler(handler));
    * */
   
   @GET
   @Path("/{logger}")
   @Produces("text/plain")
   public String setLogFormat(@PathParam("logger") String loggerName, @QueryParam("level") String level) {
      Logger logger = Logger.getLogger(loggerName);

      if (level != null && level.length() > 0) {
         logger.setLevel(Level.parse(level));
         try {
     		Handler handler = new FileHandler("trace_handler.log", true);
     		SimpleFormatter formatter = new SimpleFormatter();
     		handler.setFormatter( formatter );
     		// https://www.programcreek.com/java-api-examples/?class=java.util.logging.FileHandler&method=setFormatter
     		// https://www.logicbig.com/tutorials/core-java-tutorial/logging/customizing-default-format.html
     		logger.addHandler(handler);
     	} catch (SecurityException | IOException e) {}
      }     
	
      return getLogLevel(logger).toString();
   }
   
}