package com.github.gpor89.masters.client.grpc;

import com.github.gpor89.masters.client.util.ObjectUtils;
import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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

    public static void main(String... args) {
        int c = 11;
        Map<Integer, Long> result = new HashMap<>();
        for (int i = 0; i < c; i++) {

            long start = new Date().getTime();
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8083).usePlaintext().maxInboundMessageSize(20715938)
                    .build();
            try {
                EpgGrpc.EpgBlockingStub stub = EpgGrpc.newBlockingStub(channel);
                EpgOuterClass.X response = stub.sendEpgList(ObjectUtils.getGrpcEpgResponse(10000));

                response.getN();

            } finally {
                channel.shutdownNow();
            }
            long elapsed = new Date().getTime() - start;
            System.gc();
            result.put(i, elapsed);
        }

        double avg = result.entrySet().stream().filter(x -> x.getKey() != 0).map(x -> x.getValue()).mapToInt
                (Long::intValue).sum() * 1.00
                / (result.size());
        LOG.info("Results for gRPC: " + result + " avg: " + avg);
    }

}
