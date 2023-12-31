package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Alue;
import com.example.mokinvaraus_backend.services.AlueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for areas. Includes endpoints for fetching, creating, updating and deleting areas.
 */
@RestController
@RequestMapping(path = "/api/locations")
public class AlueController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private AlueService alueService;

    /**
     * GET-endpoint for returning all areas in the database.
     * @return A ResponseEntity containing all the areas in an iterable format.
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getAllAlueet(){
        return new ResponseEntity<>(alueService.getAll(), HttpStatus.OK);
    }


    /**
     * POST-endpoint for adding a new area to the database.
     * @param alue - The area to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error
     * message if the parameters are invalid.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewAlue(@RequestBody Alue alue){
        Alue response = null;
        try {
            response = alueService.saveAlue(alue);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    /**
     * GET-endpoint for finding an area by its ID.
     * @param id - The ID of the area to find. Gotten from the URL path. Is the same as the primary key in the database.
     * @return A ResponseEntity containing the found area or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAlue(@PathVariable(value = "id") int id){
        //Halloo, onko kalja tehtaalla? Antakkee
        Alue response = null;
        try{
            response = alueService.findAlue(id);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>("Invalid area ID!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting an area by its ID.
     * @param id - The ID of the area to delete. Gotten from the URL path. Is the same as the primary key in the database.
     * @return - A ResponseEntity with HTTP status OK.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAlue(@PathVariable(value = "id") int id) {
        Integer passId = id;

        alueService.removeAlue(passId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * PUT-endpoint for updating an area by its ID. Updates only the name of the area.
     *
     * @param  id    the ID of the Alue object to be updated
     * @param  alue  the updated Alue object
     * @return       a ResponseEntity containing the updated Alue object and HttpStatus.OK if the update is successful,
     *               otherwise a ResponseEntity with a message "Invalid area ID!" and HttpStatus.NOT_FOUND if the ID is invalid
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAlue(@PathVariable(value = "id") int id, @RequestBody Alue alue) {
        Alue toBeModified = null;
        Alue response = null;
        // TODO: Make better validation
        try{
            toBeModified = alueService.findAlue(id);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>("Invalid area ID!", HttpStatus.NOT_FOUND);
        }
        toBeModified.setName(alue.getName());
        try {
            response = alueService.saveAlue(toBeModified);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
