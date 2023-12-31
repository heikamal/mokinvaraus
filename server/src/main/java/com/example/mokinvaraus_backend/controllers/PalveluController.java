package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Palvelu;
import com.example.mokinvaraus_backend.services.PalveluService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for services. Contains endpoint for fetching, creating, updating and deleting entries in the database.
 */
@RestController
@RequestMapping(path = "/api/services")
public class PalveluController {
    @Autowired
    private PalveluService palveluService;

    /**
     * GET-endpoint for returning all services in the database.
     * @return A ResponseEntity containing all the services in an iterable format.
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getAllPalvelu(){
        return new ResponseEntity<>(palveluService.getAll(), HttpStatus.OK);
    }

    /**
     * POST-endpoint for adding a new service to the database.
     * @param palvelu The service to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewPalvelu(@RequestBody Palvelu palvelu){
        Palvelu response = null;
        try {
            response = palveluService.savePalvelu(palvelu);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for finding a service by its ID.
     * @param id The ID of the service to find. Gotten from the URL path.
     * @return A ResponseEntity containing the found service or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPalvelu(@PathVariable(value = "id") int id){
        Palvelu palvelu = null;
        try {
            palvelu = palveluService.findPalvelu(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(palvelu, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a service by its ID.
     * @param id The ID of the service to delete. Gotten from the URL path.
     * @return A ResponseEntity with HTTP status OK regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePalvelu(@PathVariable(value = "id") int id){
        palveluService.removePalvelu(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT-endpoint for updating a service by its ID.
     * @param id The ID of the service to update. Gotten from the URL path.
     * @param palvelu The service to be updated. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the updated entity or an error.
     */
    public ResponseEntity<?> updatePalvelu(@PathVariable(value = "id") int id, @RequestBody Palvelu palvelu){
        Palvelu toBeUpdated = null;
        try {
            toBeUpdated = palveluService.findPalvelu(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        //TODO: Validation
        Palvelu response = null;
        palvelu.setServiceId(toBeUpdated.getServiceId());
        try {
            response = palveluService.savePalvelu(palvelu);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
