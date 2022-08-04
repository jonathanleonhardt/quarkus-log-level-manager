package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tag")
public class SyoTagResource {
	
	//private Logger LOGGER = Logger.getLogger(SyoTagResource.class);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SyoTag> hello() {
    	SyoTagRepository repo = new SyoTagRepository();
        return repo.listAll();
    }
    
}