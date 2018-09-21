package com.github.gpor89.masters.client.grpc;

import com.github.gpor89.masters.client.util.CountOutputStream;
import com.github.gpor89.masters.client.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Serialize {

    private static Logger LOG = Logger.getLogger(Serialize.class.getName());


    public static void main(String... args) throws IOException {
        long size = -1;

        int c = 11;
        Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < c; i++) {
            CountOutputStream stream = new CountOutputStream();
            Long start = new Date().getTime();

            ObjectUtils.getGrpcEpgResponse(10000).writeTo(stream);
            size = stream.getSize();

            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("gRPC serialization time: " + avg + "ms rawSize in bytes:" + size);
    }

}
