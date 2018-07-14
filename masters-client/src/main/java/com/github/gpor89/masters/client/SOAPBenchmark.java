package com.github.gpor89.masters.client;

import com.github.gpor89.masters.soap.EpgEndpointBean;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint_Service;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpg;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SOAPBenchmark {

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {

        BigInteger size = BigInteger.valueOf(1000);

        URL url = new URL("http://localhost:8082/soap/epg/1.0?wsdl");
        EpgEndpoint_Service epgEndpoint_service = new EpgEndpoint_Service(url);
        EpgEndpoint epgEndpointSOAP = epgEndpoint_service.getEpgEndpointSOAP();

        long start = new Date().getTime();

        GetEpg epg = new GetEpg();
        epg.setSize(size);

        GetEpgResponse response = epgEndpointSOAP.getEpg(epg);

        System.out.println("Received "+response.getEpg().size()+" items in " + (new Date().getTime() - start) + " ms");

    }

}
