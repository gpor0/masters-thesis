package com.github.gpor89.masters.client.soap;

import com.github.gpor89.masters.client.util.ObjectUtils;
import https.github_com.gpor89.jax_ws.cxf.epg._1.Empty;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint_Service;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgListResponse;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CreateListSequential {

    private static Logger LOG = Logger.getLogger(CreateListSequential.class.getName());

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

            GetEpgListResponse r = new GetEpgListResponse();
            r.getEpg().addAll(ObjectUtils.getSoapEpgResponse(10000));
            Empty e = epgEndpointSOAP.sendEpgList(r);

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
