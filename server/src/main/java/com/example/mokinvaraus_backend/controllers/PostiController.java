package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Posti;
import com.example.mokinvaraus_backend.services.PostiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for the postal code entities. Includes endpoints for fetching, creating, updating and deleting postal areas.
 */
@RestController
@RequestMapping(path = "/api/postal")
public class PostiController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private PostiService postiService;

    /**
     * GET-endpoint for retrieving all postal areas saved in the database.
     * @return All postal areas in an iterable format.
     */
    @GetMapping(path = "")
    public @ResponseBody Iterable<Posti> getAllPostit(){
        return postiService.getAll();
    }

    /**
     * POST-endpoint for adding a new postal area to the database.
     * @param posti - the Posti object to be added.
     * @return A ResponseEntity containing the added Posti object.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewPosti(@RequestBody Posti posti){
        Posti response = null;
        try{
            response = postiService.savePosti(posti);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for retrieving a specific postal area from the database.
     * @param id - the ID of the postal area. Gotten from the URL path.
     * @return A ResponseEntity with the HTTP status of the operation and the found Posti object or an error message.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPosti(@PathVariable(value = "id") String id){
        Posti response = null;
        try {
            response = postiService.findPosti(String.valueOf(id));
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a specific postal area from the database.
     * @param id - The ID of the postal area. Same as the postal code.
     * @return A ResponseEntity with the HTTP status OK regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePosti(@PathVariable(value = "id") String id){
        postiService.removePosti(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT-endpoint for updating a postal area by its ID. Only the area name can be changed.
     * @param id - The ID of the postal area to be updated. Gotten from the URL path.
     * @param posti - The updated Posti object.
     * @return A ResponseEntity with the HTTP status of the operation and the updated Posti object or an error message.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updatePosti(@PathVariable(value = "id") String id, @RequestBody Posti posti){
        Posti toBeModified = null;
        Posti response = null;
        //TODO: Validation
        try{
            toBeModified = postiService.findPosti(id);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        toBeModified.setCity(posti.getCity());
        try {
            response = postiService.savePosti(toBeModified);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
