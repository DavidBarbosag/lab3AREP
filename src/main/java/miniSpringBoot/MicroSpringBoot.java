package miniSpringBoot;

import httpserver.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MicroSpringBoot {

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println();
        HttpServer.runServer(args);
    }
}
