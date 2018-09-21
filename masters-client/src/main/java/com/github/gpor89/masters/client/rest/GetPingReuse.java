package com.github.gpor89.masters.client.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gpor89.masters.rest.model.Status;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;

public class GetPingReuse {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger LOG = Logger.getLogger(GetPingReuse.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {
        int count = 1000;

        LOG.info("Executing ping REST api");
        long start = new Date().getTime();
        URL uri = new URL("http://localhost:8081/rest/ping");
        for (int i = 0; i < count; i++) {

            HttpURLConnection con = (HttpURLConnection) uri.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Connection", "Keep-Alive");

            con.setRequestMethod("GET");
            con.setDoOutput(true);

            InputStream inputStream = con.getInputStream();
            Status s = MAPPER.readValue(inputStream, Status.class);
            inputStream.close();
            int responseCode = con.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Smth went wrong");
            }

            if (s.getX() != 1) {
                throw new RuntimeException("Something went wrong, server returned wrong number of results");
            }

        }
        LOG.info(count + " REST requests completed in " + (new Date().getTime() - start) + " ms using existing channel");

    }

}
