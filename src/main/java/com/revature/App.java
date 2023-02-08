package com.revature;

import com.revature.controllers.Login;
import com.revature.controllers.Register;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

public final class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/register", (HttpHandler) new Register());
        server.createContext("/login", (HttpHandler) new Login());

        server.setExecutor(null);
        server.start();
    }
}