package com.revature;

import com.revature.controllers.Login;
import com.revature.controllers.ManageTickets;
import com.revature.controllers.ManagerSpace;
import com.revature.controllers.Register;
import com.revature.controllers.TicketViewer;
import com.revature.controllers.Ticketing;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

// This class starts an HTTP Server for testing purposes
public final class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/register", (HttpHandler) new Register());
        server.createContext("/login", (HttpHandler) new Login());
        server.createContext("/ticketing", (HttpHandler) new Ticketing());
        server.createContext("/ticketviewer", (HttpHandler) new TicketViewer());
        server.createContext("/manager", (HttpHandler) new ManagerSpace());
        server.createContext("/managetickets", (HttpHandler) new ManageTickets());

        server.setExecutor(null);
        server.start();
    }
}