package com.github.gpor89.masters.client.grpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.EeApplication;
import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
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

        System.gc();
        LOG.info("Executing epg list GRPC api with 0 to " + size + " items with step " + step);
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

        LOG.info("Results for gRPC: " + result + " avg: " + avg);
    }

    private static int fetch(int size) {


        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8083).usePlaintext().maxInboundMessageSize(20715938)
                .build();
        try {
            EpgGrpc.EpgBlockingStub stub = EpgGrpc.newBlockingStub(channel);
            EpgOuterClass.EpgRequest request = EpgOuterClass.EpgRequest.newBuilder()
                    .setSize(size).build();
            EpgOuterClass.EpgResponse response = stub.getEpgList(request);

            if (response.getItemsCount() != size) {
                throw new RuntimeException("Something went wrong, server returned wrong number of results");
            }

            return response.getItemsCount();

        } finally {
            channel.shutdown();
        }
    }

}
