package com.github.gpor89.masters.client.soap;

import com.kumuluz.ee.EeApplication;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint_Service;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgList;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgListResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GetList {

    private static Logger LOG = Logger.getLogger(EeApplication.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {
        int size = 100000;
        int step = 10000;


        LOG.info("Executing epg list SOAP api with 0 to " + size + " items with step " + step);
        final Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < size; i += step) {
            long start = new Date().getTime();
            int count = fetch(step);
            long elapsed = new Date().getTime() - start;
            LOG.info("Received " + count + " items in " + elapsed + " ms");
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());

        LOG.info("Results for SOAP: " + result + " avg: " + avg);
    }

    private static int fetch(int size) throws IOException {

        URL url = new URL("http://localhost:8082/soap/epg/1.0?wsdl");
        EpgEndpoint_Service epgEndpoint_service = new EpgEndpoint_Service(url);
        EpgEndpoint epgEndpointSOAP = epgEndpoint_service.getEpgEndpointSOAP();

        GetEpgList epg = new GetEpgList();
        epg.setSize(BigInteger.valueOf(size));

        GetEpgListResponse response = epgEndpointSOAP.getEpgList(epg);

        if (response.getEpg().size() != size) {
            throw new RuntimeException("Something went wrong, server returned wrong number of results");
        }

        return response.getEpg().size();
    }

}
