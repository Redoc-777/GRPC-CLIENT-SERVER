package org.example.server;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.services.BankGrpcService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GrpcServer {
    Server server ;
    private void start() throws IOException, InterruptedException {

        int port=9999 ;
        server = ServerBuilder.forPort(port)
                .addService((BindableService) new BankGrpcService())
                .build()
                .start();

        System.out.println("Server started, listening on " + port);
    }
    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }

}