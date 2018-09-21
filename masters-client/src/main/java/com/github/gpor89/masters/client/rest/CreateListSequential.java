package com.github.gpor89.masters.client.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gpor89.masters.client.util.ObjectUtils;
import com.github.gpor89.masters.rest.model.EpgItem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import static com.github.gpor89.masters.data.MemCache.*;

public class CreateListSequential {

    private static final ObjectMapper MAPPER = new ObjectMapper();
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
            URL uri = new URL("http://localhost:8081/rest/epg");

            HttpURLConnection con = (HttpURLConnection) uri.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(MAPPER.writeValueAsBytes(ObjectUtils.getRestEpgResponse(10000)));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Smth went wrong");
            }

            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("Results for REST: " + result + " avg: " + avg);

    }

}
