package introsde.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("sdelab")
public class StorageServiceConfig extends ResourceConfig {
    public StorageServiceConfig () {
        packages("introsde");
    }
}
