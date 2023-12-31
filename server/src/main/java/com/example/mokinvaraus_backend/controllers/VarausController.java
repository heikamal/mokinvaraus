package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Varaus;
import com.example.mokinvaraus_backend.services.VarausService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for reservations. Contains endpoints for fetching, creating, updating and deleting reservations in the database.
 */
@RestController
@RequestMapping(path = "/api/reservations")
public class VarausController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private VarausService varausService;

    /**
     * GET-endpoint for returning all reservations in the database.
     * @return A ResponseEntity containing all the reservations in an iterable format.
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getAllVaraukset() {
        return new ResponseEntity<>(varausService.getAll(), HttpStatus.OK);
    }

    /**
     * POST-endpoint for adding a new reservation to the database.
     * @param varaus - The reservation to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewVaraus(@RequestBody Varaus varaus) {
        Varaus response = null;
        try {
            response = varausService.saveVaraus(varaus);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for finding a reservation by its ID.
     * @param id The ID of the reservation to find. Gotten from the URL path.
     * @return A ResponseEntity containing the found reservation or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getVaraus(@PathVariable(value = "id") int id){
        Varaus varaus = null;
        try {
            varaus = varausService.findVaraus(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(varaus, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a reservation by its ID.
     * @param id The ID of the reservation to delete. Gotten from the URL path.
     * @return A ResponseEntity with HTTP status ok regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVaraus(@PathVariable(value = "id") int id){
        varausService.removeVaraus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT-endpoint for updating a reservation by its ID.
     * @param id The ID of the reservation to update. Gotten from the URL path.
     * @param varaus The reservation to be updated. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the updated entity or an error.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateVaraus(@PathVariable(value = "id") int id, @RequestBody Varaus varaus){
        Varaus toBeUpdated = null;
        try {
            toBeUpdated = varausService.findVaraus(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        //TODO: Validation
        varaus.setReservationId(toBeUpdated.getReservationId());
        Varaus response = null;
        try{
            response = varausService.saveVaraus(varaus);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET-endpoint for returning all services for the reservation.
     * @param id - The ID of the reservation. Gotten from the URL path.
     * @return A ResponseEntity containing all the services for the reservation in an iterable format.
     */
    @GetMapping(path = "/{id}/palvelut")
    public ResponseEntity<?> getPalvelut(@PathVariable(value = "id") int id){
        return new ResponseEntity<>("Moi, olen placeholder", HttpStatus.OK);
    }
}
