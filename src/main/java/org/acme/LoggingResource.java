package org.acme;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
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

@Path("/logging")
public class LoggingResource {

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
	@Path("/{logger}/changetype")
	@Produces("text/plain")
	public String changeType(@PathParam("logger") String loggerName, @QueryParam("type") String type) {
		Logger logger = Logger.getLogger(loggerName);
		
		if (type != null && type.length() > 0) {
			try {
				if (type.equals("file")) {
					Arrays.stream(logger.getHandlers()).forEach(handler -> logger.removeHandler(handler));
					Handler handler = new FileHandler("log-"+ loggerName +".log", true);
					handler.setFormatter(new SimpleFormatter());
					System.setProperty("java.util.logging.SimpleFormatter.format", "%d{HH:mm:ss} %-5p [%c{2.}] (%t) %s%e%n");
					logger.addHandler(handler);
				} else if (type.equals("console")) {
					Arrays.stream(logger.getHandlers()).forEach(handler -> logger.removeHandler(handler));
					Handler handler = new ConsoleHandler();
					logger.addHandler(handler);
				}
			} catch (SecurityException | IOException e) {}
		}
		return "Log alterado para: " + type.toUpperCase();
	}
	
   
//	@GET
//	@Path("/{logger}/format")
//	@Produces("text/plain")
//	public String setFormat(@PathParam("logger") String loggerName, @QueryParam("format") String format) {
//		Logger logger = Logger.getLogger(loggerName);
//
//		if (format != null && format.length() > 0) {
//			try {
//				Handler logHandler = null;
//				Arrays.stream(logger.getHandlers()).forEach(handler -> {
//					if (handler instanceof ConsoleHandler || handler instanceof FileHandler) {
//						handler.setFormatter(new SimpleFormatter());
//						System.setProperty("java.util.logging.SimpleFormatter.format", format);
//						logger.addHandler(logHandler);
//						;
//					}
//				});
//				// https://www.programcreek.com/java-api-examples/?class=java.util.logging.FileHandler&method=setFormatter
//				// https://www.logicbig.com/tutorials/core-java-tutorial/logging/customizing-default-format.html
//			} catch (SecurityException e) {
//			}
//		}
//
//		return getLogLevel(logger).toString();
//	}

	@GET
	@Path("/{logger}/gravalog")
	@Produces("text/plain")
	public String saveToFile(@PathParam("logger") String loggerName) {
		Logger logger = Logger.getLogger(loggerName);

		try {
			Handler handler = new FileHandler("log-salvo-" + loggerName + ".log", true);
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {}

		return "Log será salvo em " + "log-salvo-" + loggerName + ".log";
	}
	
	private static Level getLogLevel(Logger logger) {
		for (Logger current = logger; current != null;) {
			Level level = current.getLevel();
			if (level != null)
				return level;
			current = current.getParent();
		}
		return Level.INFO;
	}
   
}