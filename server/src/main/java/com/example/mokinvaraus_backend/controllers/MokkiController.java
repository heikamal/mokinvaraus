package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Mokki;
import com.example.mokinvaraus_backend.services.MokkiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for cabin data. Contains endpoint for fetching, creating, updating and deleting.
 */
@RestController
@RequestMapping(path = "/api/cabins")
public class MokkiController {

    /**
     * Service for communicating with the repository.
     */
    @Autowired
    private MokkiService mokkiService;

    /**
     * GET-endpoint for returning all cabins in the database.
     * @return A ResponseEntity containing all the cabins in an iterable format.
     */
    @GetMapping(path = "")
    public @ResponseBody Iterable<Mokki> getAllMokit() {
        return mokkiService.getAll();
    }

    /**
     * POST-endpoint for adding a new cabin to the database.
     * @param mokki - The cabin to be added. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the added entity or an error.
     */
    @PostMapping(path = "")
    public ResponseEntity<?> addNewMokki(@NonNull @RequestBody Mokki mokki) {
        Mokki response = null;
        try {
            response = mokkiService.saveMokki(mokki);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * GET-endpoint for finding a cabin by its ID.
     * @param id - The ID of the cabin to find. Gotten from the URL path.
     * @return A ResponseEntity containing the found cabin or error message if the ID is not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMokki(@PathVariable(value = "id") int id){
        Mokki mokki = null;
        try {
            mokki = mokkiService.findMokki(id);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mokki, HttpStatus.OK);
    }

    /**
     * DELETE-endpoint for deleting a cabin by its ID.
     * @param id - The ID of the cabin to delete. Gotten from the URL path.
     * @return A ResponseEntity with HTTP status OK regardless if the operation was successful or not.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMokki(@PathVariable(value = "id") int id){
        mokkiService.removeMokki(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *  PUT-endpoint for updating a cabin by its ID. Doesn't support partial updates yet.
     * @param id - The ID of the cabin to update. Gotten from the URL path.
     * @param mokki - The cabin to be updated. Gotten from the request body.
     * @return A ResponseEntity with HTTP status of the operation and either the updated entity or an error.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMokki(@PathVariable(value = "id") int id, @RequestBody Mokki mokki) {
        Mokki toBeModified = new Mokki();
        Mokki response = null;
        try{
            toBeModified = mokkiService.findMokki(id);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>("Invalid cabin ID!", HttpStatus.NOT_FOUND);
        }
        try {
            toBeModified.setAreaId(mokki.getAreaId());
            toBeModified.setPostalCode(mokki.getPostalCode());
            toBeModified.setCabinName(mokki.getCabinName());
            toBeModified.setStreetAddress(mokki.getStreetAddress());
            toBeModified.setPrice(mokki.getPrice());
            toBeModified.setDescription(mokki.getDescription());
            toBeModified.setPersonCount(mokki.getPersonCount());
            toBeModified.setEquipment(mokki.getEquipment());
        } catch(NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            response = mokkiService.saveMokki(toBeModified);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
