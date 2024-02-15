package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Posti;
import com.example.mokinvaraus_backend.repositories.PostiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Service for communicating with the post repository.
 */
@Service
public class PostiService {

    /**
     * Repository for CRUD operations.
     */
    @Autowired
    private PostiRepository postiRepository;

    /**
     * Retrieves all postal areas from the database.
     * @return All postal areas in an iterable format.
     */
    public Iterable<Posti> getAll(){
        return postiRepository.findAll();
    }

    /**
     * Adds the given Posti object to the database with the save-operation.
     *
     * @param  posti  the Posti object to be created or updated
     * @return        the created Posti object
     * @throws IllegalArgumentException if the given object is missing parameters.
     */
    public Posti savePosti(@NonNull Posti posti){
        try {
            return postiRepository.save(posti);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }
    }

    /**
     * Finds a Posti object by its id. The id is the postal/zip code.
     *
     * @param  id  the id of the Posti object to find
     * @return     the found Posti object, or null if not found
     */
    public Posti findPosti(@NonNull String id){
        Posti posti = postiRepository.findById(id).orElse(null);
        if (posti == null){
            throw new EntityNotFoundException("No such postal code!");
        }
        return posti;
    }

    /**
     * Removes a post with the specified ID from the database.
     *
     * @param  id  the ID of the post to be removed.
     */
    public void removePosti(@NonNull String id){
        postiRepository.deleteById(id);
    }


}
