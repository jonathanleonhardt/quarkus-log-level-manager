package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
@Path("/tag")
public class SyoTagResource {
	
	//private Logger LOGGER = Logger.getLogger(SyoTagResource.class);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SyoTag> listTags() {
    	log.debug("log Lombok DEBUG /tag/list");
    	log.info("log Lombok INFO /tag/list");
    	log.error("log Lombok ERROR /tag/list");
    	//log.log(Level.TRACE, "TRACE LOGGING");
    	
    	
//    	LOGGER.info("log INFO /tag/list");
//    	LOGGER.debug("log DEBUG /tag/list");
//    	LOGGER.error("log ERRO /tag/list");

    	
    	SyoTagRepository repo = new SyoTagRepository();
        return repo.listAll();
    }
    
}