package com.example.mokinvaraus_backend.services;

import com.example.mokinvaraus_backend.models.Mokki;
import com.example.mokinvaraus_backend.repositories.MokkiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service class for communicating with the repository.
 */
@Service
public class MokkiService {
    @Autowired
    private MokkiRepository mokkiRepository;

    /**
     * Returns an iterable collection of all the cabins in the repository.
     * @return All the cabins in an iterable format.
     */
    public Iterable<Mokki> getAll(){
        return mokkiRepository.findAll();
    }

    /**
     * Saves a Mokki object in the repository.
     *
     * @param  mokki  the Mokki object to be saved
     * @return        the saved Mokki object
     * @throws IllegalArgumentException if the parameter object's fields are missing or invalid
     */
    public Mokki saveMokki(Mokki mokki){
        try {
            return mokkiRepository.save(mokki);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input parameters!");
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Invalid or missing input parameters!");
        }
    }

    /**
     * Finds a Mokki object by its ID.
     *
     * @param  id the ID of the Mokki to find
     * @return    the found Mokki object
     * @throws EntityNotFoundException if there is no cabin with the given ID
     */
    public Mokki findMokki(Integer id){
        Mokki mokki = mokkiRepository.findById(id).orElse(null);
        if (mokki == null){
            throw new EntityNotFoundException("No such cabin!");
        }
        return mokki;
    }

    /**
     * Removes a mokki from the repository based on its ID.
     *
     * @param  id  the ID of the mokki to be removed
     */
    public void removeMokki(Integer id){
        mokkiRepository.deleteById(id);
    }
}
