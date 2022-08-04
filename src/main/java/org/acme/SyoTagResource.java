package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
@Path("/tag")
public class SyoTagResource {
	
	private Logger LOGGER = Logger.getLogger(SyoTagResource.class);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SyoTag> hello() {
    	log.info("log Lombok INFO /tag/list");
    	log.debug("log Lombok DEBUG /tag/list");
    	log.error("log Lombok ERROR /tag/list");
    	LOGGER.info("log INFO /tag/list");
    	LOGGER.debug("log DEBUG /tag/list");
    	LOGGER.error("log ERRO /tag/list");

    	log.log(Level.TRACE, "erro");
    	
    	SyoTagRepository repo = new SyoTagRepository();
        return repo.listAll();
    }
    
}