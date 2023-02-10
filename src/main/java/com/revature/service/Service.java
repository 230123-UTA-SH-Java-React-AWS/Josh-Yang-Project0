package com.revature.service;

import com.revature.models.Employee;
import com.revature.models.Tickets;
import com.revature.repository.Repository;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
    The service layer is responsible for holding behavior driven classes
    It might have further validation or information process within the service
*/
public class Service {
    // Fields
    private final Repository repo = new Repository();
    private final ObjectMapper mapper = new ObjectMapper();

    /* 
     *
     * Successful 
     * 
    */
    // Registering an employee
    public String updateTicketStatus(String updatedTicketJson) throws JsonParseException, JsonMappingException, IOException {
        // converts json string to an object
        Tickets ticket = mapper.readValue(updatedTicketJson, Tickets.class);
        
        // stores the returned string message into registered variable
        String newTicketStatus = repo.updateTicketStatus(ticket);

        // return the registered message
        return newTicketStatus;
    }
    
    /* 
     *
     * Successful 
     * 
    */
    // If a user attempts to login, the system will check if their email address exists first
    public boolean employeeLogin(String employeeJson) throws JsonParseException, JsonMappingException, IOException {
        // convert json string into an object
        Employee isEmployee = mapper.readValue(employeeJson, Employee.class);

        boolean result = false;
        result = repo.loginVerification(isEmployee);
        
        return result;
    }

    /* 
     *
     * Successful 
     * 
    */
    // If an employee attempts to login, the system will check if their email address exists first and if they are a manager
    public boolean managerLogin(String managerJson) throws JsonParseException, JsonMappingException, IOException {
        // convert json string into an object
        Employee isManager = mapper.readValue(managerJson, Employee.class);

        boolean result = false;
        result = repo.isManager(isManager);
        
        return result;
    }

    public String registration(String employeeJson) throws JsonParseException, JsonMappingException, IOException {
        // converts json string to an object
        Employee employee = mapper.readValue(employeeJson, Employee.class);
        
        // stores the returned string message into registered variable
        String registered = repo.registerEmployee(employee);

        // return the registered message
        return registered;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Submitting a ticket
    public String submitTicket(String ticketValues) {
        String submission = "";

        try {
            // converts json string to an object
            Tickets ticket = mapper.readValue(ticketValues, Tickets.class);
            submission = repo.ticketSubmission(ticket);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return a message (either ticket has/hasn't been submitted)
        return submission;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Viewing all the tickets
    public String getAllTickets() {
        List<Tickets> listOfTickets = repo.getAllTickets();

        String employeeJson = "";

        try {
            employeeJson = mapper.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeJson;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - PENDING
    public String pendingTickets() {
        List<Tickets> listOfTickets = repo.pendingTickets();

        String employeeJson = "";

        try {
            employeeJson = mapper.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeJson;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - APPROVED
    public String approvedTickets() {
        List<Tickets> listOfTickets = repo.approvedTickets();

        String employeeJson = "";

        try {
            employeeJson = mapper.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeJson;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - DENIED
    public String deniedTickets() {
        List<Tickets> listOfTickets = repo.deniedTickets();

        String employeeJson = "";

        try {
            employeeJson = mapper.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeJson;
    }

    /* 
     *
     * Need to implement 
     * 
    */
    // Updating ticket status
    // public String updateTicketStatus() {
    //     List<Tickets> listOfTickets = repo.updateTicketStatus();

    //     String employeeJson = "";

    //     try {
    //         employeeJson = mapper.writeValueAsString(listOfTickets);
    //     } catch (JsonGenerationException e) {
    //         e.printStackTrace();
    //     } catch (JsonMappingException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return employeeJson;
    // }


    /*
     * 
     * Need to implement
     * 
    */
    // public String ticketsByEmail(String email) {
    //     List<Tickets> listOfTickets = repo.ticketsByEmail(email);

    //     String employeeJson = "";

    //     try {
    //         employeeJson = mapper.writeValueAsString(listOfTickets);
    //     } catch (JsonGenerationException e) {
    //         e.printStackTrace();
    //     } catch (JsonMappingException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return employeeJson;
    // }
}