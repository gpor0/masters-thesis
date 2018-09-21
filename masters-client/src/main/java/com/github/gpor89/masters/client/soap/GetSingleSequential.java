package com.github.gpor89.masters.client.soap;

import https.github_com.gpor89.jax_ws.cxf.epg._1.Empty;
import https.github_com.gpor89.jax_ws.cxf.epg._1.Epg;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint_Service;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GetSingleSequential {

    private static Logger LOG = Logger.getLogger(GetSingleSequential.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {

        int c = 11;
        Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < c; i++) {
            long start = new Date().getTime();
            URL url = new URL("http://localhost:8082/soap/epg/1.0?wsdl");
            EpgEndpoint_Service epgEndpoint_service = new EpgEndpoint_Service(url);
            EpgEndpoint epgEndpointSOAP = epgEndpoint_service.getEpgEndpointSOAP();

            Epg epg = epgEndpointSOAP.getEpg(new Empty());
            epg.getIdEpg();

            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("Results for SOAP: " + result + " avg: " + avg);
    }

}
