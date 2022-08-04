package org.acme;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/* 
/logging/com.example.mypackage
 
And set the log level like this:
/logging/com.example.mypackage?level=DEBUG
*/

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
   public String logger(@PathParam("logger") String loggerName, @QueryParam("level") String level) {
      // get the logger instance
      Logger logger = Logger.getLogger(loggerName);

      // change the log-level if requested
      if (level != null && level.length() > 0)
         logger.setLevel(Level.parse(level));

      // return the current log-level
      return getLogLevel(logger).toString();
   }
}