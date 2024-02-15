package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Palvelu;
import com.example.mokinvaraus_backend.repositories.PalveluRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository for service entries in the database.
 */
@Service
public class PalveluService {

    /**
     * The CRUD repository for interacting with the database.
     */
    @Autowired
    private PalveluRepository palveluRepository;

    /**
     * Returns an iterable collection of all the services in the repository.
     * @return An iterable collection of Palvelu objects returned from the database.
     */
    public Iterable<Palvelu> getAll(){
        return palveluRepository.findAll();
    }

    /**
     * Saves the given service into the database using the repository's save-method.
     * @param palvelu The Palvelu object to be saved.
     * @return the saved Palvelu object.
     * @throws IllegalArgumentException If the parameter object's fields are missing or invalid
     */
    public Palvelu savePalvelu(@NonNull Palvelu palvelu){
        try {
            return palveluRepository.save(palvelu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }
    }

    /**
     * Finds and returns a single service by its ID from the database.
     * @param id The ID of the service to find.
     * @return The found service data in Palvelu object.
     * @throws EntityNotFoundException If the service is not found
     */
    public Palvelu findPalvelu(@NonNull Integer id){
        Palvelu palvelu = palveluRepository.findById(id).orElse(null);
        if(palvelu == null){
            throw new EntityNotFoundException("No such service!");
        }
        return palvelu;
    }

    /**
     * Removes the given service from the database.
     * @param id The ID of the service to remove.
     */
    public void removePalvelu(@NonNull Integer id){
        palveluRepository.deleteById(id);
    }
}
