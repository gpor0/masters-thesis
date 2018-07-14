package com.kumuluz.ee;

import com.github.gpor89.masters.data.MemCache;
import com.github.gpor89.masters.grpc.EpgServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class EeApplication {

    static public void main(String[] args) throws IOException, InterruptedException {

        MemCache.load(10000);

        String portStr = System.getenv("PORT");

        int port = 8080;

        try {
            port = Integer.valueOf(portStr);
        } catch (Exception e) {

        }

        Server server = ServerBuilder.forPort(port)
                .addService(new EpgServiceImpl()).build();

        System.out.println("Starting server at port " + port + "...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }

}
