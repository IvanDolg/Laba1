package application;

import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleHttpServer {

    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/first.txt", exchange -> {
            File file = new File("/Users/ivandolgolaptev/IdeaProjects/Laba1/src/main/java/utils/first.txt");
            byte[] response = Files.readAllBytes(file.toPath());
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        });

        server.createContext("/second.txt", exchange -> {
            File file = new File("/Users/ivandolgolaptev/IdeaProjects/Laba1/src/main/java/utils/second.txt");
            byte[] response = Files.readAllBytes(file.toPath());
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:8000");
    }
}