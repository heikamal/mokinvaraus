package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Asiakas;
import com.example.mokinvaraus_backend.services.AsiakasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for customer data. Contains endpoints for fetching, creating, updating and deleting customer data.
 */
@RestController
@RequestMapping(path = "/api/customers")
public class AsiakasController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private AsiakasService asiakasService;

    /**
     * GET-endpoint for returning all customers in the database.
     * @return A ResponseEntity containing all the customers in an iterable format.
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getAllAsiakas(){
        return new ResponseEntity<>(asiakasService.getAll(), HttpStatus.OK);
    }

    /**
     * POST-endpoint for adding a new customer to the database.
     * @param asiakas - The customer to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewAsiakas(@RequestBody Asiakas asiakas) {
        Asiakas response = null;
        try {
            response = asiakasService.saveAsiakas(asiakas);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for finding a customer by its ID.
     * @param id - The ID of the customer to find. Gotten from the URL path.
     * @return A ResponseEntity containing the found customer or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAsiakas(@PathVariable(value = "id") int id) {
        Asiakas asiakas = null;
        try {
            asiakas = asiakasService.findAsiakas(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(asiakas, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a customer by its ID.
     * @param id - The ID of the customer to delete. Gotten from the URL path.
     * @return A ResponseEntity with HTTP status OK, regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAsiakas(@PathVariable(value = "id") int id) {
        asiakasService.removeAsiakas(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT-endpoint for updating a customer by its ID. Doesn't support partial updates yet.
     * @param id - The ID of the customer to update. Gotten from the URL path.
     * @param asiakas - The updated customer data. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the updated entity or an error.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAsiakas(@PathVariable(value = "id") int id, @RequestBody Asiakas asiakas) {
        Asiakas toBeModified = null;
        Asiakas response = null;
        // TODO: Add better validation
        // TODO: Possibility for partial update.
        try{
            toBeModified = asiakasService.findAsiakas(id);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>("Invalid cutomer ID!", HttpStatus.NOT_FOUND);
        }
        toBeModified.setPostalCode(asiakas.getPostalCode());
        toBeModified.setFirstName(asiakas.getFirstName());
        toBeModified.setLastName(asiakas.getLastName());
        toBeModified.setStreetAddress(asiakas.getStreetAddress());
        toBeModified.setEmail(asiakas.getEmail());
        toBeModified.setPhoneNumber(asiakas.getPhoneNumber());

        try {
            response = asiakasService.saveAsiakas(toBeModified);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
