package com.github.gpor89.masters.client;

import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GRPCBenchmark {

    // what to benchmark:
    // serialization/deserialization time
    // payload size
    // connection init time
    //overall

    public static void main(String... args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8083).usePlaintext().maxInboundMessageSize(30715938)
                .build();

        EpgGrpc.EpgStub stub = EpgGrpc.newStub(channel);

        EpgOuterClass.EpgRequest request = EpgOuterClass.EpgRequest.newBuilder()
                .setSize(1000)
                .build();

        long start = new Date().getTime();
        stub.getEpg(request, new StreamObserver<EpgOuterClass.EpgResponse>() {
            @Override
            public void onNext(EpgOuterClass.EpgResponse epgResponse) {
                System.out.println("Received "+epgResponse.getItemsCount()+" items in " + (new Date().getTime() - start) + " ms");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {

            }
        });

        Thread.sleep(2000);
        channel.shutdown().awaitTermination(2, TimeUnit.SECONDS);

    }

}
