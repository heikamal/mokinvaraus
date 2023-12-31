package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Asiakas;
import com.example.mokinvaraus_backend.repositories.AsiakasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository.
 */
@Service
public class AsiakasService {

    /**
     * CRUD-repository for interacting with the database.
     */
    @Autowired
    private AsiakasRepository asiakasRepository;

    /**
     * Returns an iterable collection of all the customers in the repository.
     * @return An iterable collection of Asiakas objects returned from the database.
     */
    public Iterable<Asiakas> getAll(){
        return asiakasRepository.findAll();
    }

    /**
     * Saves the given customer into the database using the repository's save-method.
     * @param asiakas - the Asiakas object to be saved.
     * @return the saved Asiakas object.
     * @throws IllegalArgumentException if the parameter object's fields are missing or invalid
     */
    public Asiakas saveAsiakas(Asiakas asiakas) {
        try {
            return asiakasRepository.save(asiakas);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }
    }

    /**
     * Finds and returns a single customer by its ID from the database.
     * @param id - the ID of the customer to find.
     * @return the found customer data in Asiakas object.
     */
    public Asiakas findAsiakas(Integer id){
        Asiakas asiakas = asiakasRepository.findById(id).orElse(null);
        if(asiakas == null){
            throw new EntityNotFoundException("No such customer!");
        }
        return asiakas;
    }

    /**
     * Deletes the given customer from the database.
     * @param id - the ID of the customer to delete.
     */
    public void removeAsiakas(Integer id){
        asiakasRepository.deleteById(id);
    }
}
