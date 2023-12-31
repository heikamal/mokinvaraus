package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Alue;
import com.example.mokinvaraus_backend.repositories.AlueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for communicating with the repository.
 */
@Service
public class AlueService {
    /**
     * Repository for CRUD operations.
     */
    @Autowired
    private AlueRepository alueRepository;

    /**
     * Returns a collection of all the areas in the database.
     * @return All the areas in an iterable format.
     */
    public Iterable<Alue> getAll(){
        return alueRepository.findAll();
    }

    /**
     * Adds the given Alue object to the database with the save-operation.
     * If the given Alue object is missing parameters, an IllegalArgumentException is thrown.
     *
     * @param  alue  the Alue object to be created and saved
     * @return       the created Alue object
     * @throws IllegalArgumentException if the parameter object is missing parameters.
     */
    public Alue saveAlue(Alue alue){
        try {
            return alueRepository.save(alue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }
    }

    /**
     * Returns a single Alue entity.
     * @param id - ID in the database in Integer instance.
     * @return Alue (Area) entity corresponding to the id.
     */
    public Alue findAlue(Integer id){
        Alue alue = alueRepository.findById(id).orElse(null);
        if (alue == null){
            throw new EntityNotFoundException("No such area!");
        }
        return alue;
    }

    /**
     * Removes a single area entry from the database.
     * @param id - Area's ID in the database.
     */
    public void removeAlue(Integer id){
        alueRepository.deleteById(id);
    }

}
