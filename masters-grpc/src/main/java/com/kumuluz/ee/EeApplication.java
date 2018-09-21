package com.kumuluz.ee;

import com.github.gpor89.masters.grpc.EpgServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class EeApplication {

    private static Logger LOG = Logger.getLogger(EeApplication.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 8083;

        LOG.info("Starting server at port " + port + "...");
        server = ServerBuilder.forPort(port)
                .addService(new EpgServiceImpl())
                .build()
                .start();
        LOG.info("Server started on port "+port+" at " + new Date().getTime());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                EeApplication.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static public void main(String[] args) throws IOException, InterruptedException {

        final EeApplication server = new EeApplication();
        server.start();
        server.blockUntilShutdown();
    }

}
