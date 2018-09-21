package com.github.gpor89.masters.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class ReadInterceptor implements ContainerRequestFilter {

    private Logger LOG = Logger.getLogger(ReadInterceptor.class.getName());

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        LOG.info("request received");

    }
}
