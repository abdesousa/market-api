package com.market.api.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.market.api.model.PotatoesBag;
import com.market.api.model.Representation;
import com.market.api.service.PotatoesBagService;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * Resource class.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 * @since Nov, 2018
 */

@Path("/market/potatoesbag")
@Produces(MediaType.APPLICATION_JSON)
public class PotatoesBagResource {

	private final PotatoesBagService service;

	final static Logger logger = LoggerFactory.getLogger(PotatoesBagService.class);
	
	public PotatoesBagResource(PotatoesBagService service) {
		this.service = service;
	}

	@POST
    @UnitOfWork
	public Representation<PotatoesBag> create(PotatoesBag potatoesBag) {
		
		logger.info("Inserting a PotatoBag.");
	    return new Representation<PotatoesBag>(HttpStatus.OK_200, service.create(potatoesBag));

	}

	@POST
	@Path("/db")
    @UnitOfWork
	public Representation<PotatoesBag> createTables(PotatoesBag potatoesBag) {
		
		logger.info("Create / populate the tables.");
		service.createTables();
		
	    return new Representation<PotatoesBag>(HttpStatus.OK_200, null);

	}
	@GET
    @UnitOfWork
	public Representation<List<PotatoesBag>> list(@QueryParam("limit") Integer limit) {
		logger.info("limit:"+limit);

	    return new Representation<List<PotatoesBag>>(HttpStatus.OK_200, service.list(limit));
	}

}
