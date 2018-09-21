package com.github.gpor89.masters.client.soap;

import https.github_com.gpor89.jax_ws.cxf.epg._1.Empty;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint_Service;
import https.github_com.gpor89.jax_ws.cxf.epg._1.Pong;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;

public class GetPingNew {

    private static Logger LOG = Logger.getLogger(GetPingNew.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {
        int count = 1000;

        LOG.info("Executing ping SOAP api");
        long start = new Date().getTime();
        for (int i = 0; i < count; i++) {
            URL url = new URL("http://localhost:8082/soap/epg/1.0?wsdl");
            EpgEndpoint_Service epgEndpoint_service = new EpgEndpoint_Service(url);
            EpgEndpoint epgEndpointSOAP = epgEndpoint_service.getEpgEndpointSOAP();

            Pong response = epgEndpointSOAP.ping(new Empty());

            if (!BigInteger.ONE.equals(response.getX())) {
                throw new RuntimeException("Something went wrong, server returned wrong number of results");
            }
        }
        LOG.info(count + " SOAP requests completed in " + (new Date().getTime() - start) + " ms using new channel");


    }

}
