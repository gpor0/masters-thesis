package com.github.gpor89.masters.client.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gpor89.masters.rest.model.EpgItem;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GetList {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger LOG = Logger.getLogger(GetList.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {
        int size = 100000;
        int step = 10000;

        System.gc();
        LOG.info("Executing epg list REST api with 0 to " + size + " items with step " + step);
        final Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i <= size; i += step) {
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

        LOG.info("Results for REST: " + result + " avg: " + avg);

    }

    private static int fetch(int size) throws IOException {

        URL uri = new URL("http://localhost:8081/rest/epg?size=" + size);

        List<EpgItem> response = MAPPER.readValue(uri, new TypeReference<List<EpgItem>>() {
        });

        if (response.size() != size) {
            throw new RuntimeException("Something went wrong, server returned wrong number of results");
        }

        return response.size();
    }

}
