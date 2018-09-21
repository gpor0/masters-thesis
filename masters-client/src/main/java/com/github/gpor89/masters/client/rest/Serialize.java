package com.github.gpor89.masters.client.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gpor89.masters.client.util.CountOutputStream;
import com.github.gpor89.masters.client.util.ObjectUtils;
import com.github.gpor89.masters.rest.model.EpgItem;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static com.github.gpor89.masters.data.MemCache.*;

public class Serialize {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger LOG = Logger.getLogger(Serialize.class.getName());


    public static void main(String... args) throws IOException {
        long size = -1;

        int c = 11;
        Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < c; i++) {
            CountOutputStream stream = new CountOutputStream();
            Long start = new Date().getTime();

            stream.write(MAPPER.writeValueAsBytes(ObjectUtils.getRestEpgResponse(10000)));

            size = stream.getSize();

            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("REST serialization time: " + avg + "ms rawSize in bytes:" + size);
    }

}
