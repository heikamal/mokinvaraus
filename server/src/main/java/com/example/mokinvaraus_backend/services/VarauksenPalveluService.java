package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.VarauksenPalvelu;
import com.example.mokinvaraus_backend.repositories.VarauksenPalveluRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository for reservation's service entries in the database.
 */
@Service
public class VarauksenPalveluService {

    @Autowired
    private VarauksenPalveluRepository varauksenPalveluRepository;

    /**
     * Returns an iterable collection of all the reservation service entries in the repository.
     * @return All the reservations in an iterable format.
     */
    public Iterable<VarauksenPalvelu> getAll(){
        return varauksenPalveluRepository.findAll();
    }

    /**
     * Adds the given VarauksenPalvelu object to the database with the save-operation.
     * @param  varauksenPalvelu  the VarauksenPalvelu object to be saved
     */
    public VarauksenPalvelu saveVarauksenPalvelu(@NonNull VarauksenPalvelu varauksenPalvelu){
        return varauksenPalveluRepository.save(varauksenPalvelu);
    }

    /**
     * Finds and returns a single reservation's service entry by its ID from the database.
     * @param id The ID of the reservation to find.
     * @return the found reservation data in VarauksenPalvelu object
     * @throws EntityNotFoundException If the reservation is not found.
     */
    public VarauksenPalvelu findVarauksenPalvelu(@NonNull Integer id){
        VarauksenPalvelu varauksenPalvelu = varauksenPalveluRepository.findById(id).orElse(null);
        if(varauksenPalvelu == null){
            throw new EntityNotFoundException("No such reservation!");
        }
        return varauksenPalvelu;
    }

    /**
     * Removes the given reservation's service entry from the database.
     */
    public void removeVarauksenPalvelu(@NonNull Integer id){
        varauksenPalveluRepository.deleteById(id);
    }

}
