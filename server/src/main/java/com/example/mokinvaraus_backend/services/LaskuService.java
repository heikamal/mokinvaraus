package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Lasku;
import com.example.mokinvaraus_backend.repositories.LaskuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository for invoice entries.
 */
@Service
public class LaskuService {

    /**
     * The CRUD repository for interacting with the database.
     */
    @Autowired
    private LaskuRepository laskuRepository;

    /**
     * Returns an iterable collection of all the invoices in the repository.
     * @return An iterable collection of Lasku objects returned from the database.
     */
    public Iterable<Lasku> getAll(){
        return laskuRepository.findAll();
    }

    /**
     * Saves the given invoice into the database using the repository's save-method.
     * @param lasku The Lasku object to be saved.
     * @return the saved Lasku object.
     * @throws IllegalArgumentException if the parameter object's fields are missing or invalid
     */
    public Lasku saveLasku(@NonNull Lasku lasku){
        try {
            return laskuRepository.save(lasku);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }
    }

    /**
     * Finds and returns a single invoice by its ID from the database.
     * @param id The ID of the invoice to find.
     * @return the found invoice data in Lasku object.
     */
    public Lasku findLasku(@NonNull Integer id){
        Lasku lasku = laskuRepository.findById(id).orElse(null);
        if(lasku == null){
            throw new EntityNotFoundException("No such invoice!");
        }
        return lasku;
    }

    /**
     * Deletes the given invoice from the database.
     * @param id The ID of the invoice to delete.
     */
    public void removeLasku(@NonNull Integer id){
        laskuRepository.deleteById(id);
    }
}
