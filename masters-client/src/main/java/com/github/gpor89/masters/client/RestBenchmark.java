package com.github.gpor89.masters.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gpor89.masters.rest.model.EpgItem;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class RestBenchmark {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {

        URL uri = new URL("http://localhost:8081/rest/epg?size="+1000);

        long start = new Date().getTime();
        List<EpgItem> response = MAPPER.readValue(uri, new TypeReference<List<EpgItem>>() {
        });

        System.out.println("Received "+response.size()+" items in " + (new Date().getTime() - start) + " ms");

    }

}
