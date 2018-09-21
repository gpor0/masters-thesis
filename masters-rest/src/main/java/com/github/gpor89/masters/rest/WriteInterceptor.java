package com.github.gpor89.masters.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class WriteInterceptor implements WriterInterceptor {


    private Logger LOG = Logger.getLogger(WriteInterceptor.class.getName());

    @Override
    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
        LOG.info("response returned");

        writerInterceptorContext.proceed();
    }

}
