package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// This class is where we will handle the HTTP request for logging in
public class Login implements HttpHandler {
    // Field
    private final Service service = new Service();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        String verb = exchange.getRequestMethod();

        switch (verb) {
            case "POST":
                postRequest(exchange);
                break;
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

    // Login
    public void postRequest(HttpExchange exchange) throws IOException {
        // A message that tells the user that their information is invalid
        String loginMessage = "Invalid login information. Please enter the correct credentials.";

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

        // However, if an employee's information was indeed valid
        if (service.employeeLogin(convertToString.toString()) != true) {
            // Then change the loginMessage to tell the user they've successfully logged in
            exchange.sendResponseHeaders(404, loginMessage.getBytes().length);
        } else {
            loginMessage = "You've successfully logged in.";
            exchange.sendResponseHeaders(200, loginMessage.getBytes().length);
        }

        // Using OutputStream to send a response
        OutputStream os = exchange.getResponseBody();
        os.write(loginMessage.getBytes());
        os.close();
    }
}