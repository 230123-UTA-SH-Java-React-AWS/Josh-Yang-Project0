package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.models.Tickets;
import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// This class is where we will handle the HTTP request for logging in
public class ManageTickets implements HttpHandler {
    // Field
    private final Service service = new Service();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        String verb = exchange.getRequestMethod();

        switch (verb) {
            case "PUT":
                putRequest(exchange);
            default:
                String invalidVerb = "HTTP Verb not supported";

                exchange.sendResponseHeaders(404, invalidVerb.getBytes().length);

                // Using OutputStream to send a response
                OutputStream os = exchange.getResponseBody();
                os.write(invalidVerb.toString().getBytes());
                os.close();
                break;
        }
    }

    /*
     *
     * Able to filter the status, but not the email part yet 
     * 
    */
    // Filtering out the tickets by their current status
    public void putRequest(HttpExchange exchange) throws IOException {
        // InputStream is not a String; it has a bunch of bytes
        // Retrieving a body request
        InputStream is = exchange.getRequestBody();

        // Creating our StringBuilder to convert our InputStream into a String later on
        StringBuilder convertToString = new StringBuilder();
        
        // Convert the binaries into letters
        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            // then read each letters until there is no more left, which .read() method will produce a -1
            while ((c = reader.read()) != -1) {
                // while reading each letters, add them into convertedToString
                convertToString.append((char) c);
            }
        }
        // String jsonCurrentList = "";

        String ticketUpdated = service.updateTicketStatus(convertToString.toString());

        // if (status.contains("pending")) {
        //     jsonCurrentList = service.pendingTickets();
            
        // } else if (status.contains("approved")) {
        //     jsonCurrentList = service.approvedTickets();

        // } else if (status.contains("denied")) {
        //     jsonCurrentList = service.deniedTickets();

        // }

        exchange.sendResponseHeaders(200, ticketUpdated.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(ticketUpdated.getBytes());
        os.close();
    }
}