package com.indix.resources;

/**
 * Created by indix on 20/4/17.
 */

import com.codahale.metrics.annotation.Timed;
import com.indix.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class DemowizzardResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public DemowizzardResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") String name) {
        final String value = String.format(template, name==null?defaultName:name);
        return new Saying(counter.incrementAndGet(), value);
    }
}