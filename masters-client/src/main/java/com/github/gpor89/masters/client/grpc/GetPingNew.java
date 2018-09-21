package com.github.gpor89.masters.client.grpc;

import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class GetPingNew {

    private static Logger LOG = Logger.getLogger(GetPingNew.class.getName());

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws IOException {
        int count = 1000;

        LOG.info("Executing ping gRPC api");
        long start = new Date().getTime();
        for (int i = 0; i < count; i++) {

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8083).usePlaintext().maxInboundMessageSize(20715938)
                    .build();
            try {
                EpgGrpc.EpgBlockingStub stub = EpgGrpc.newBlockingStub(channel);
                EpgOuterClass.X request = EpgOuterClass.X.newBuilder()
                        .setN(1).build();
                EpgOuterClass.X response = stub.ping(request);

                if (response.getN() != 1) {
                    throw new RuntimeException("Something went wrong, server returned wrong number of results");
                }

            } finally {
                channel.shutdown();
            }

        }
        LOG.info(count + " gRPC requests completed in " + (new Date().getTime() - start) + " ms using new channel");


    }

}
