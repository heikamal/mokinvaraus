package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Lasku;
import com.example.mokinvaraus_backend.services.LaskuService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for invoices. Contains endpoints for fetching, creating, updating and deleting invoices in the database.
 */
@RestController
@RequestMapping(path = "/api/invoices")
public class LaskuController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private LaskuService laskuService;

    /**
     * GET-endpoint for returning all invoices in the database.
     * @return A ResponseEntity containing all the invoices in an iterable format.
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getAllLaskut(){
        return new ResponseEntity<>(laskuService.getAll(), HttpStatus.OK);
    }

    /**
     * POST-endpoint for adding a new invoice to the database.
     * @param lasku - The invoice to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewLasku(@RequestBody Lasku lasku){
        Lasku response = null;
        try {
            response = laskuService.saveLasku(lasku);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for finding an invoice by its ID.
     * @param id - The ID of the invoice to find. Gotten from the URL path. Is the same as the primary key in the database.
     * @return A ResponseEntity containing the found invoice or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getLasku(@PathVariable(value = "id") int id){
        Lasku lasku = null;
        try {
            lasku = laskuService.findLasku(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lasku, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a invoice by its ID.
     * @param id - The ID of the invoice to delete. Gotten from the URL path.
     * @return A ResponseEntity with HTTP status ok regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteLasku(@PathVariable(value = "id") int id){
        laskuService.removeLasku(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT-endpoint for updating a invoice by its ID. Doesn't support partial updates yet.
     * @param id - The ID of the invoice to update. Gotten from the URL path.
     * @param lasku - The updated invoice data. Gotten from the request body.
     * @return A ResponseEntity containing the updated invoice or error message if the ID is not found.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateLasku(@PathVariable(value = "id") int id, @RequestBody Lasku lasku){
        Lasku toUpdate = null;
        try{
            toUpdate = laskuService.findLasku(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        //TODO: Validation
        Lasku response = null;
        lasku.setInvoiceId(toUpdate.getInvoiceId());

        try {
            response = laskuService.saveLasku(lasku);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
