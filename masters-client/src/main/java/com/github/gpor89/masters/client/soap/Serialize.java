package com.github.gpor89.masters.client.soap;

import com.github.gpor89.masters.client.util.CountOutputStream;
import com.github.gpor89.masters.client.util.ObjectUtils;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgListResponse;
import https.github_com.gpor89.jax_ws.cxf.epg._1.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Serialize {

    private static Logger LOG = Logger.getLogger(Serialize.class.getName());


    public static void main(String... args) throws IOException, JAXBException {
        long size = -1;

        int c = 11;
        Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < c; i++) {
            CountOutputStream stream = new CountOutputStream();
            Long start = new Date().getTime();

            GetEpgListResponse r = new GetEpgListResponse();
            r.getEpg().addAll(ObjectUtils.getSoapEpgResponse(10000));

            ObjectFactory o = new ObjectFactory();
            JAXBElement<GetEpgListResponse> getEpgListResponse = o.createGetEpgListResponse(r);


            JAXBContext jaxbContext = JAXBContext.newInstance(JAXBElement.class, GetEpgListResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.marshal(getEpgListResponse, stream);

            size = stream.getSize();

            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("SOAP serialization time: " + avg + "ms rawSize in bytes:" + size);
    }

}
