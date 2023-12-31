package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Varaus;
import com.example.mokinvaraus_backend.repositories.VarausRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository for reservation entries in the database.
 */
@Service
public class VarausService {

    /**
     * CRUD repository for interacting with the database.
     */
    @Autowired
    private VarausRepository varausRepository;

    /**
     * Returns an iterable collection of all the reservations in the repository.
     * @return An iterable collection of Varaus objects returned from the database.
     */
    public Iterable<Varaus> getAll(){
        return varausRepository.findAll();
    }

    /**
     * Adds the given Varaus object to the database with the save-operation.
     * @param  varaus  the Varaus object to be saved
     */
    public Varaus saveVaraus(Varaus varaus){
        return varausRepository.save(varaus);
    }

    /**
     * Find and returns a single reservation by its ID from the database.
     * @param id The ID of the reservation to find.
     * @return the found reservation data in Varaus object
     * @throws EntityNotFoundException If the reservation is not found.
     */
    public Varaus findVaraus(Integer id){
        Varaus varaus = varausRepository.findById(id).orElse(null);
        if(varaus == null){
            throw new EntityNotFoundException("No such reservation!");
        }
        return varaus;
    }

    /**
     * Removes the given reservation from the database.
     * @param id The ID of the reservation to remove.
     */
    public void removeVaraus(Integer id){
        varausRepository.deleteById(id);
    }

}
