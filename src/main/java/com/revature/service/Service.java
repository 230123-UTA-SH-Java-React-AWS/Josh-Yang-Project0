package com.revature.service;

import com.revature.models.Employee;
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

    // Register an employee
    public String registration(String employeeJson) throws JsonParseException, JsonMappingException, IOException {
        // converts json string to an object
        Employee employee = mapper.readValue(employeeJson, Employee.class);
        
        // stores the returned string message into registered variable
        String registered = repo.registerEmployee(employee);

        // return the registered message
        return registered;
    }
    
    // Employee will login and the system will check if email and password exists
    public boolean employeeLogin(String employeeJson) throws JsonParseException, JsonMappingException, IOException {
        // convert json string into an object
        Employee isEmployee = mapper.readValue(employeeJson, Employee.class);

        boolean result = false;
        result = repo.loginVerification(isEmployee);
        
        return result;
    }

    // Retrieve all current employees (similar from lecture)
    public String getAllEmployees() {
        // storing all employees into listOfEmployees
        List<Employee> listOfEmployees = repo.getAllEmployees();
        String employeeJson = "";

        try {
            // return and store the listOfEmployees as a string
            employeeJson = mapper.writeValueAsString(listOfEmployees);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return all the employees
        return employeeJson;
    }
}
